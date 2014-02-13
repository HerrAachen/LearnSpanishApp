package de.malikatalla.ling.ling;

import java.util.List;

public interface Dictionary {

  void loadDictionary();
  
  String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g);
  
  String getPersonalPronoun(Tense t, Person p, Number n, Gender g);
  
  List<String> getAllVerbs();
}
