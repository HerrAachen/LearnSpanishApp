package de.malikatalla.ling.ling;

import java.util.Arrays;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import de.malikatalla.ling.DBContract.ConjugationTable;
import de.malikatalla.ling.DBContract.VerbTable;
import de.malikatalla.ling.Global;

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
				Cursor res = db.query(VerbTable.TABLE_NAME + " INNER JOIN " + ConjugationTable.TABLE_NAME + " ON "
						+ VerbTable.TABLE_NAME + "." + VerbTable.COLUMN_CONJUGATION + "=" + ConjugationTable.TABLE_NAME
						+ "." + ConjugationTable.COLUMN_CONJ_ID, null, VerbTable.TABLE_NAME + "."
						+ VerbTable.COLUMN_INFINITIVE + "='" + infinitive + "'", null, null, null, null);

				if (res.moveToNext()) {
					String roots = res.getString(res.getColumnIndex(VerbTable.COLUMN_ROOT));
					Flections flections = new Flections(infinitive);
					for (Flection flection : Global.getColumnConverter().flectionIterator()) {
						String inflection = getInflectedForm(roots, flection, res);
						if (inflection != null) {
							flections.addInflectedForm(flection, inflection);
						}
					}
					infinitive2flections.put(infinitive, flections);
					Log.i("Test", "putting " + infinitive + " in mem dict");
				}
			}

			private String getInflectedForm(String roots, Flection flection, Cursor res) {
				String conjugationColumn = Global.getColumnConverter().getDBColumn(flection.getTense(),
						flection.getPerson(), flection.getNumber(), flection.getGender(), flection.getMode());
				int columnIndex = res.getColumnIndex(conjugationColumn);
				if (columnIndex >= 0) {
					return extractConjugation(roots, res.getString(columnIndex));
				}
				return null;
			}
		};
		d.loadDictionary();
		return d;
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
			String roots = res.getString(0);
			String ending = res.getString(1);
			return extractConjugation(roots, ending);
		}
		return null;
	}

	private String extractConjugation(String rootsString, String ending) {
		String[] roots = rootsString.split(";");
		int rootIndex = 0;
		if (ending.matches(".*[0-9]")) {
			rootIndex = Integer.parseInt(ending.substring(ending.length() - 1, ending.length())) - 1;
			ending = ending.substring(0, ending.length() - 1);
		}
		Log.i("Test", Arrays.toString(roots) + "; " + ending);
		return roots[rootIndex] + ending;
	}

	@Override
	public String getPersonalPronoun(Tense t, Person p, Number n, Gender g, Mode m) {
		if (n==null || p == null){
			throw new NullPointerException("getPersonalPronoun: Person and Number must not be null");
		}
		if (n.equals(Number.SINGULAR)){
			switch (p){
			case FIRST:return "yo";
			case SECOND: return "tú"; 
			case THIRD:
				if (g==null){
					return "él/ella";
				}
				if (g.equals(Gender.MALE)){
					return "él";
				} else if (g.equals(Gender.FEMALE)){
					return "ella";
				}
			}
		}
		if (n.equals(Number.PLURAL)){
			switch (p){
			case FIRST:return "nosotros";
			case SECOND: return "vosotros"; 
			case THIRD:
				if (g==null){
					return "ellos/ellas";
				}
				if (g.equals(Gender.MALE)){
					return "ellos";
				} else if (g.equals(Gender.FEMALE)){
					return "ellas";
				}
			}
		}
		return null;
	}
}
