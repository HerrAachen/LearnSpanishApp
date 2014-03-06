package de.malikatalla.ling.ling;

import java.util.Arrays;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.DBContract.ConjugationTable;
import de.malikatalla.ling.DBContract.VerbTable;

public class DbDictionarySpanish extends DbDictionary {

	public DbDictionarySpanish(Context context) {
		super(context);
	}

	@Override
	public String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g, Mode m) {
		String conjugationColumn = Global.getColumnConverter().getDBColumn(t, p, n, g, m);
		SQLiteDatabase db = con.getReadableDatabase();
		Cursor res = db.query(VerbTable.TABLE_NAME + " INNER JOIN " + ConjugationTable.TABLE_NAME + " ON "
				+ VerbTable.TABLE_NAME + "." + VerbTable.COLUMN_CONJUGATION + "=" + ConjugationTable.TABLE_NAME + "."
				+ ConjugationTable.COLUMN_CONJ_ID, new String[] { VerbTable.COLUMN_ROOT,
				ConjugationTable.TABLE_NAME + "." + conjugationColumn, VerbTable.COLUMN_INFINITIVE, },
				VerbTable.TABLE_NAME + "." + VerbTable.COLUMN_INFINITIVE + "='" + infinitive + "'", null, null, null,
				null);
		if (res.moveToNext()) {
			int rootIndex = 0;
			String[] roots = res.getString(0).split(";");
			String ending = res.getString(1);
			if (ending.matches(".*[0-9]")){
				rootIndex = Integer.parseInt(ending.substring(ending.length()-1, ending.length()))-1;
				ending = ending.substring(0, ending.length()-1);
			}
			Log.i("Test", Arrays.toString(roots) + "; " + ending);
			return roots[rootIndex] + ending;
		}
		return null;
	}
}
