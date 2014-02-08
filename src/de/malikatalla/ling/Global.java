package de.malikatalla.ling;

import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.DummyDictionary;

public class Global {

	private static Dictionary dictionary;
	
	static {
		dictionary = new DummyDictionary();
		dictionary.loadDictionary();
	}

	public static Dictionary getDictionary() {
		return dictionary;
	}
}
