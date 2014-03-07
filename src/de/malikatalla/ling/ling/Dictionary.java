package de.malikatalla.ling.ling;

import java.util.List;

public interface Dictionary {

   void loadDictionary();

   Dictionary getAllInflectedForms(final String infinitive);

   String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g, Mode m);

   String getPersonalPronoun(Tense t, Person p, Number n, Gender g, Mode m);

   List<String> getAllVerbs();

}
