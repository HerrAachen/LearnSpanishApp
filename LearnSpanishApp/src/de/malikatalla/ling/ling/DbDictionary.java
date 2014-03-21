package de.malikatalla.ling.ling;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import de.malikatalla.ling.DBConnector;
import de.malikatalla.ling.DBContract.VerbTable;

public abstract class DbDictionary implements Dictionary {

  protected DBConnector con;

  public DbDictionary(Context context) {
    con = new DBConnector(context);
  }

  @Override
  public void loadDictionary() {

  }

  @Override
  public List<String> getAllVerbs() {
    SQLiteDatabase db = con.getReadableDatabase();
    Cursor res = db.query(VerbTable.TABLE_NAME, new String[] { VerbTable.COLUMN_INFINITIVE }, null, null, null, null,
        VerbTable.COLUMN_INFINITIVE);
    List<String> verbs = new ArrayList<String>();
    while (res.moveToNext()) {
      String verb = res.getString(0);
      verbs.add(verb);
    }
    return verbs;
  }
  
  /**
   * 
   * @param t
   * @param p
   * @param n
   * @param g
   * @param m
   * @return the string denoting the database column where the conjugation for this combination is stored
   */
  public abstract String getDBColumn(Tense t, Person p, Number n, Gender g, Mode m);
  
  public abstract List<Flection> flectionIterator();
}
