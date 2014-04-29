package de.malikatalla.ling.ling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import de.malikatalla.ling.DBContract.ConjugationTable;
import de.malikatalla.ling.DBContract.VerbTable;
import de.malikatalla.ling.Global;

/**
 * This class contains all language dependent implementations for the dictionary
 * access. Language independent parts should be move to the super class
 */
public class DbDictionarySpanish extends DbDictionary {

  public DbDictionarySpanish(Context context) {
    super(context);
  }

  /** Returns all inflections for the specified verb */
  public Dictionary getAllInflectedForms(final String infinitive) {
    InMemDictionary d = new InMemDictionary() {

      @Override
      public void loadDictionary() {
        SQLiteDatabase db = con.getReadableDatabase();
        Cursor res = db.query(VerbTable.TABLE_NAME + " INNER JOIN " + ConjugationTable.TABLE_NAME + " ON " + VerbTable.TABLE_NAME
            + "." + VerbTable.COLUMN_CONJUGATION + "=" + ConjugationTable.TABLE_NAME + "." + ConjugationTable.COLUMN_CONJ_ID,
            null, VerbTable.TABLE_NAME + "." + VerbTable.COLUMN_INFINITIVE + "='" + infinitive + "'", null, null, null, null);

        if (res.moveToNext()) {
          String roots = res.getString(res.getColumnIndex(VerbTable.COLUMN_ROOT));
          String infinitive = res.getString(res.getColumnIndex(VerbTable.COLUMN_INFINITIVE));
          Flections flections = new Flections(infinitive);
          for (Flection flection : Global.getColumnConverter().flectionIterator()) {
            String inflection = getInflectedForm(roots, flection, res, infinitive);
            if (inflection != null) {
              flections.addInflectedForm(flection, inflection);
            }
          }
          infinitive2flections.put(infinitive, flections);
          Log.i(Global.DEBUG, "putting " + infinitive + " in mem dict");
        }
      }

      private String getInflectedForm(String roots, Flection flection, Cursor res, String infinitive) {
        String compound = handleCompoundTenses(infinitive, flection.getTense(), flection.getPerson(), flection.getNumber(), flection.getGender(),flection.getMode());
        if (compound!=null){
          return compound;
        }
        String conjugationColumn = Global.getColumnConverter().getDBColumn(flection.getTense(), flection.getPerson(),
            flection.getNumber(), flection.getGender(), flection.getMode());
        if (conjugationColumn == null) {
          return null;
        }
        int columnIndex = res.getColumnIndex(conjugationColumn);
        if (columnIndex >= 0) {
          return extractConjugation(roots, res.getString(columnIndex), flection, infinitive);
        }
        return null;
      }
    };
    d.loadDictionary();
    return d;
  }

  @Override
  public String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g, Mode m) {
    String compound = handleCompoundTenses(infinitive, t, p, n, g, m);
    if (compound!=null){
      return compound;
    }
    String conjugationColumn = Global.getColumnConverter().getDBColumn(t, p, n, g, m);
    // Log.i(Global.DEBUG, "column: " + conjugationColumn);
    if (conjugationColumn == null) {
      return null;
    }
    SQLiteDatabase db = con.getReadableDatabase();
    Cursor curIrr = db.query(VerbTable.TABLE_NAME, new String[] { conjugationColumn }, VerbTable.COLUMN_INFINITIVE + "='"
        + infinitive + "'", null, null, null, null);
    if (curIrr != null && curIrr.moveToNext()) {
      String irregularConjugation = curIrr.getString(curIrr.getColumnIndex(conjugationColumn));
      if (irregularConjugation != null && irregularConjugation.length()>0) {
        return irregularConjugation;
      }
    }
    Cursor res = db
        .query(VerbTable.TABLE_NAME + " INNER JOIN " + ConjugationTable.TABLE_NAME + " ON " + VerbTable.TABLE_NAME + "."
            + VerbTable.COLUMN_CONJUGATION + "=" + ConjugationTable.TABLE_NAME + "." + ConjugationTable.COLUMN_CONJ_ID,
            new String[] { VerbTable.COLUMN_ROOT, ConjugationTable.TABLE_NAME + "." + conjugationColumn,
                VerbTable.COLUMN_INFINITIVE, }, VerbTable.TABLE_NAME + "." + VerbTable.COLUMN_INFINITIVE + "='" + infinitive
                + "'", null, null, null, null);
    if (res.moveToNext()) {
      String roots = res.getString(0);
      String ending = res.getString(1);
      // Log.i(Global.DEBUG, roots + " " + ending);
      return extractConjugation(roots, ending, new Flection(t, p, n, g, m), infinitive);
    }
    return null;
  }

  private String handleCompoundTenses(String infinitive, Tense t, Person p, Number n, Gender g, Mode m) {
    Log.i(Global.DEBUG, "handleCompound " + t + " " + m);
    if (isCompoundTense(t, m)) {
      Log.i(Global.DEBUG, "is Compound: ");
      String auxiliary = getInflectedForm("haber", mapAuxiliaryTense(t), p, n, g, m);
      String participle = getInflectedForm(infinitive, t, p, n, g, Mode.PARTICIPLE);
      return auxiliary + " " + participle;
    }
    return null;
  }

  /**
   * @param t
   *          a compound tense
   * @return the tense of the auxiliary verb in this compound tense
   */
  private Tense mapAuxiliaryTense(Tense t) {
    switch (t) {
    case PAST_PERFECT:
      return Tense.PRESENT;
    case PLUSCUAM_PERFECT:
      return Tense.IMPERFECT;
    case FUTURE_PERFECT:
      return Tense.FUTURE;
    default:
      return null;
    }
  }

  private boolean isCompoundTense(Tense t, Mode m) {
    if (t == null || m == null) {
      return false;
    }
    return (t.equals(Tense.PAST_PERFECT) || t.equals(Tense.PLUSCUAM_PERFECT) || t.equals(Tense.FUTURE_PERFECT)) && m.equals(Mode.INDICATIVE);
  }

  private String extractConjugation(String rootsString, String ending, Flection flection, String infinitive) {
    String[] roots = rootsString.split(";");
    if (ending == null) {
      return null;
    }
    // endings can be null, because db is not complete. After completion
    // should always be non-null
    boolean isImperative = flection.getMode().equals(Mode.IMPERATIVE);
    boolean isReflexive = infinitive.endsWith("se");
    List<String> endings = new ArrayList<String>();
    endings.addAll(Arrays.asList(ending.split(";")));
    // Log.i(Global.DEBUG, Arrays.toString(roots));
    // Log.i(Global.DEBUG, endings.toString());
    StringBuilder resultBuilder = new StringBuilder();

    Iterator<String> iterator = endings.iterator();
    while (iterator.hasNext()) {
      String currentEnding = iterator.next();
      // found an ending only valid for reflexive verbs
      if (currentEnding.startsWith("r:")) {
        if (isReflexive) {
          // leave only the current ending in the list
          endings = new LinkedList<String>();
          endings.add(currentEnding.substring(2));
          break;
        } else {
          iterator.remove();
        }
      }
    }
    // Log.i(Global.DEBUG, endings.toString());
    for (int endingsIndex = 0; endingsIndex < endings.size(); endingsIndex++) {
      String currentEnding = endings.get(endingsIndex);
      LinkedList<Integer> rootIndices = new LinkedList<Integer>();
      while (currentEnding.matches(".*[0-9]")) {
        rootIndices.addFirst(Integer.parseInt(currentEnding.substring(currentEnding.length() - 1, currentEnding.length())) - 1);
        currentEnding = currentEnding.substring(0, currentEnding.length() - 1);
      }
      int rootIndex = 0;
      if (!rootIndices.isEmpty()) {
        if (isReflexive && rootIndices.size() >= 2) {
          rootIndex = rootIndices.get(1);
        } else {
          rootIndex = rootIndices.get(0);
        }
      }
      if (isImperative && isReflexive && flection.getNumber().equals(Number.PLURAL)
          && (flection.getPerson() == Person.FIRST || flection.getPerson() == Person.SECOND)) {
        currentEnding = currentEnding.substring(0, currentEnding.length() - 1);
      }
      String currentConjugation = roots[rootIndex] + currentEnding;
      if (isReflexive) {
        if (isImperative) {
          currentConjugation = currentConjugation
              + getReflexivePronoun(flection.getTense(), flection.getPerson(), flection.getNumber(), flection.getGender(),
                  flection.getMode());

        } else {
          currentConjugation = getReflexivePronoun(flection.getTense(), flection.getPerson(), flection.getNumber(),
              flection.getGender(), flection.getMode())
              + " " + currentConjugation;
        }
      }
      resultBuilder.append(currentConjugation);
      if (endingsIndex < endings.size() - 1) {
        resultBuilder.append("/");
      }
    }
    return resultBuilder.toString();
  }

  @Override
  public String getPersonalPronoun(Tense t, Person p, Number n, Gender g, Mode m) {
    if (n == null || p == null) {
      throw new NullPointerException("getPersonalPronoun: Person and Number must not be null");
    }
    if (n.equals(Number.SINGULAR)) {
      switch (p) {
      case FIRST:
        return "yo";
      case SECOND:
        return "tú";
      case THIRD:
        if (g == null) {
          return "él/ella";
        }
        if (g.equals(Gender.MALE)) {
          return "él";
        } else if (g.equals(Gender.FEMALE)) {
          return "ella";
        }
      }
    }
    if (n.equals(Number.PLURAL)) {
      switch (p) {
      case FIRST:
        return "nosotros";
      case SECOND:
        return "vosotros";
      case THIRD:
        if (g == null) {
          return "ellos/ellas";
        }
        if (g.equals(Gender.MALE)) {
          return "ellos";
        } else if (g.equals(Gender.FEMALE)) {
          return "ellas";
        }
      }
    }
    return null;
  }

  @Override
  public String getReflexivePronoun(Tense t, Person p, Number n, Gender g, Mode m) {
    if (m.equals(Mode.PARTICIPLE)) {
      return "";
    }
    if (n == null || p == null) {
      throw new NullPointerException("getReflexivePronoun: Person and Number must not be null:"
          + new Flection(t, p, n, g, m).toString());
    }
    if (n.equals(Number.SINGULAR)) {
      switch (p) {
      case FIRST:
        return "me";
      case SECOND:
        return "te";
      case THIRD:
        return "se";
      }
    }
    if (n.equals(Number.PLURAL)) {
      switch (p) {
      case FIRST:
        return "nos";
      case SECOND:
        return "os";
      case THIRD:
        return "se";
      }
    }
    return null;
  }
}
