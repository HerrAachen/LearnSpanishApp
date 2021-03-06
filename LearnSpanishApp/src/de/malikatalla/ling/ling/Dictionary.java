package de.malikatalla.ling.ling;

import java.util.List;

/** Provides access to the verbs and their conjugations */
public interface Dictionary {

	void loadDictionary();

	Dictionary getAllInflectedForms(final String infinitive);

    String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g, Mode m);

    String getInflectedForm(String infinitive, Flection f);

	String getPersonalPronoun(Tense t, Person p, Number n, Gender g, Mode m);

	String getReflexivePronoun(Tense t, Person p, Number n, Gender g, Mode m);

	List<String> getAllVerbs();

}
