package de.malikatalla.ling.ling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class InMemDictionary implements Dictionary {
   protected Map<String, Flections> infinitive2flections = new HashMap<String, Flections>();
   protected Flections              personalPronouns     = new Flections();

   /** Loads all verb flections and personal pronouns */
   public abstract void loadDictionary();

   @Override
   public String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g, Mode m) {
      Flections verbFlections = infinitive2flections.get(infinitive);
      return verbFlections.getInflectedForm(t, p, n, g, m);
   }

   public String getPersonalPronoun(Tense t, Person p, Number n, Gender g, Mode m) {
      return personalPronouns.getInflectedForm(t, p, n, g, m);
   }

   public List<String> getAllVerbs() {
      List<String> l = new ArrayList<String>();
      l.addAll(infinitive2flections.keySet());
      return l;
   }
}
