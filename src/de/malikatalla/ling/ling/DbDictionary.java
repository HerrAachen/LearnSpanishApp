package de.malikatalla.ling.ling;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import de.malikatalla.ling.DBConnector;
import de.malikatalla.ling.DBContract.ConjugationTable;
import de.malikatalla.ling.DBContract.VerbTable;
import de.malikatalla.ling.Global;

public class DbDictionary implements Dictionary {

   private DBConnector con;

   public DbDictionary(Context context) {
      con = new DBConnector(context);
   }

   @Override
   public void loadDictionary() {

   }

   @Override
   public String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g, Mode m) {
      String conjugationColumn = Global.getColumnConverter().getDBColumn(t, p, n, g, m);
      SQLiteDatabase db = con.getReadableDatabase();
      Cursor res = db.query(VerbTable.TABLE_NAME + " INNER JOIN " + ConjugationTable.TABLE_NAME + " ON "
            + VerbTable.TABLE_NAME + "." + VerbTable.COLUMN_CONJUGATION + "=" + ConjugationTable.TABLE_NAME + "."
            + ConjugationTable.COLUMN_CONJ_ID, new String[] { VerbTable.COLUMN_ROOT,
            ConjugationTable.TABLE_NAME + "." + conjugationColumn, VerbTable.COLUMN_INFINITIVE, }, VerbTable.TABLE_NAME
            + "." + VerbTable.COLUMN_INFINITIVE + "='" + infinitive + "'", null, null, null, null);
      if (res.moveToNext()) {
         String root = res.getString(0);
         String ending = res.getString(1);
         Log.i("Test", root + "; " + ending);
         return root + ending;
      }
      return null;
   }

   @Override
   public String getPersonalPronoun(Tense t, Person p, Number n, Gender g, Mode m) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<String> getAllVerbs() {
      Log.i("TEST", "getAllVerbs");
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
}
