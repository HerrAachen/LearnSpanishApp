package de.malikatalla.ling.ling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class InMemDictionary implements Dictionary {
  protected Map<String, Flections> infinitive2flections = new HashMap<String, Flections>();
  protected Flections personalPronouns = new Flections();

  /** Loads all verb flections and personal pronouns */
  public abstract void loadDictionary();

  @Override
  public Dictionary getAllInflectedForms(final String infinitive) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getInflectedForm(String infinitive, Flection f) {
    Tense t = f.getTense();
    Person p = f.getPerson();
    Number n = f.getNumber();
    Gender g = f.getGender();
    Mode m = f.getMode();
    return getInflectedForm(infinitive, t, p, n, g, m);
  }

  @Override
  public String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g, Mode m) {
    Flections verbFlections = infinitive2flections.get(infinitive);
    if (verbFlections != null) {
      return verbFlections.getInflectedForm(t, p, n, g, m);
    }
    return null;
  }

  @Override
  public String getPersonalPronoun(Tense t, Person p, Number n, Gender g, Mode m) {
    return personalPronouns.getInflectedForm(t, p, n, g, m);
  }

  @Override
  public String getReflexivePronoun(Tense t, Person p, Number n, Gender g, Mode m) {
    throw new UnsupportedOperationException();
  }

  public List<String> getAllVerbs() {
    List<String> l = new ArrayList<String>();
    l.addAll(infinitive2flections.keySet());
    return l;
  }
}
