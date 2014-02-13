package de.malikatalla.ling;

import android.content.Context;
import de.malikatalla.ling.ling.DbDictionary;
import de.malikatalla.ling.ling.Dictionary;

public class Global {

	private static Dictionary dictionary;
	
	public static void init(Context context){
//      dictionary = new DummyDictionary();
      dictionary = new DbDictionary(context);
	dictionary.loadDictionary();
	}

	public static Dictionary getDictionary() {
		return dictionary;
	}
}
