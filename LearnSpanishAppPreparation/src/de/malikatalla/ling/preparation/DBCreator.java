package de.malikatalla.ling.preparation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.malikatalla.ling.DBConnector;
import de.malikatalla.ling.DBContract.ConjugationTable;
import de.malikatalla.ling.DBContract.VerbTable;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.ling.ColumnConverter;
import de.malikatalla.ling.ling.Flection;

/** Takes a wiktionary dump as input and creates the database for the android app from it */
public class DBCreator {

  static final String WIKI_DUMP = "InputData/eswiktionary-20140305-pages-meta-current.xml";
  private static final File ENDINGS_FILE = new File("InputData/conjugations.txt");
  private static final String COMMA = ",";

  public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, SAXException,
      IOException {
    Global.init();
    Map<String, ConjugationDescription> conjugations = WictionaryConjugationExtractor
        .extractConjugations(WIKI_DUMP);
    createDatabase(conjugations);
  }

  private static void createDatabase(Map<String, ConjugationDescription> inf2conj) throws ClassNotFoundException, SQLException {
    Class.forName("org.sqlite.JDBC");
    Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DBConnector.DATABASE_NAME);
    conn.setAutoCommit(false);
    Statement stat = conn.createStatement();
    // create tables (fair assumption that Database dictionary is used)
    ColumnConverter cc = Global.getColumnConverter();
    stat.executeUpdate("drop table if exists " + ConjugationTable.TABLE_NAME);

    // Order of the columns matters! Same as in conjugations text file

    StringBuilder sql = new StringBuilder();
    sql.append("create table " + ConjugationTable.TABLE_NAME + " (" + ConjugationTable.COLUMN_CONJ_ID + " INTEGER PRIMARY KEY"
        + COMMA + ConjugationTable.COLUMN_CONJ_DESC + " TEXT");
    for (Flection f : cc.flectionIterator()) {
      addColumn(cc.getDBColumn(f.getTense(), f.getPerson(), f.getNumber(), f.getGender(), f.getMode()), sql);
    }
    sql.append(")");
    stat.executeUpdate(sql.toString());
    stat.executeUpdate("drop table if exists " + VerbTable.TABLE_NAME);
    stat.executeUpdate("create table " + VerbTable.TABLE_NAME + " (" + VerbTable.COLUMN_INFINITIVE + " TEXT" + COMMA
        + VerbTable.COLUMN_ROOT + " TEXT" + COMMA + VerbTable.COLUMN_CONJUGATION + " INTEGER" + ")");
    // sort conjugations alphabetically and map to ID
    Set<String> conjugations = new TreeSet<>();
    for (ConjugationDescription d : inf2conj.values()) {
      conjugations.add(d.getBasicConjugation());
    }

    Map<String, Integer> conj2ID = new HashMap<String, Integer>();
    int i = 0;
    for (String desc : conjugations) {
      conj2ID.put(desc, i++);
    }

    // read conjugations from file and put them to db table
    Map<String, List<String>> conj2endings = readConjugationsFile(ENDINGS_FILE);
    System.out.println("Creating conjugation table");
    int ENDINGS_COUNT = cc.flectionIterator().size();
    StringBuilder insertStatement = new StringBuilder();
    insertStatement.append("insert into " + ConjugationTable.TABLE_NAME + " values (");
    for (int j = 0; j < ENDINGS_COUNT + 2; j++) {
      insertStatement.append("?,");
    }
    PreparedStatement prep = conn.prepareStatement(insertStatement.substring(0, insertStatement.length() - 1) + ")");
    int count = 0;
    for (Map.Entry<String, Integer> entry : conj2ID.entrySet()) {
      prep.setInt(1, entry.getValue());
      String conjugationName = entry.getKey();
      prep.setString(2, conjugationName != null ? conjugationName : null);
      List<String> endings = conj2endings.get(conjugationName);
      if (endings == null) {
        System.out.println("No endings found for " + conjugationName);
      }
      for (int index = 0; index < ENDINGS_COUNT; index++) {
        prep.setString(index + 3, endings != null && endings.size() > index ? endings.get(index) : null);
      }
      prep.execute();
      System.out.println(count++ + "/" + conj2ID.size());
    }
    System.out.println("commit");
    conn.commit();

    // put verbs to table
    count = 0;
    System.out.println("Creating verb table");
    PreparedStatement prep2 = conn.prepareStatement("insert into " + VerbTable.TABLE_NAME + " values (?, ?, ?)");
    for (Map.Entry<String, ConjugationDescription> entry : inf2conj.entrySet()) {
      prep2.setString(1, entry.getKey());
      prep2.setString(2, entry.getValue().getRoot());
      System.out.println(count++ + "/" + inf2conj.size() + " " + entry.getKey() + " " + entry.getValue());
      prep2.setInt(3, conj2ID.get(entry.getValue().getBasicConjugation()));
      prep2.addBatch();
    }
    System.out.println("batch execute");
    prep2.executeBatch();
    System.out.println("commit");
    conn.commit();
    conn.close();

  }

  private static void addColumn(String dbColumn, StringBuilder sql) {
    sql.append(COMMA).append("\"").append(dbColumn).append("\" TEXT");
  }

  private static Map<String, List<String>> readConjugationsFile(File endingsFile) {
    try {
      Map<String, List<String>> conjugationName2endings = new HashMap<>();
      BufferedReader reader = new BufferedReader(new FileReader(endingsFile));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("\t");
        String conjugationName = parts[0];
        List<String> endings = new ArrayList<>();
        endings.addAll(Arrays.asList(parts));
        // first element is conjugation name, not an ending
        endings.remove(0);
        conjugationName2endings.put(conjugationName, endings);
      }
      reader.close();
      return conjugationName2endings;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
