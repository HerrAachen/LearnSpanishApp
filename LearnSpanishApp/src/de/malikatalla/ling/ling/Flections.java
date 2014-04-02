package de.malikatalla.ling.ling;

import java.util.HashMap;
import java.util.Map;

public class Flections {
   /** Only applies for verbs */
   private String                infinitive;
   private Map<Flection, String> inflectedForms = new HashMap<Flection, String>();

   @Override
  public String toString() {
    return "[infinitive=" + infinitive + ", inflectedForms=" + inflectedForms + "]";
  }

  public Flections() {
      this(null);
   }

   public Flections(String infinitive) {
      this.infinitive = infinitive;
   }

   /**
    * @return the number of inflected forms
    */
   public int getSize() {
      return inflectedForms.size();
   }

   public void addInflectedForm(Flection f, String inflectedForm) {
      inflectedForms.put(f, inflectedForm);
   }

   public void addInflectedForm(Tense t, Person p, Number n, Gender g, String inflectedForm) {
      addInflectedForm(t, p, n, g, Mode.INDICATIVE, inflectedForm);
   }

   public void addInflectedForm(Tense t, Person p, Number n, Gender g, Mode m, String inflectedForm) {
      inflectedForms.put(new Flection(t, p, n, g, m), inflectedForm);
   }

   public String getInflectedForm(Tense t, Person p, Number n, Gender g, Mode m) {
      String inflected = null;
      Flection f1 = new Flection(t, p, n, g, m);
      inflected = inflectedForms.get(f1);
      if (inflected != null) {
         return inflected;
      }
      // linear search, should scale, since number of flections per verb should not be too big
      for (Map.Entry<Flection, String> flection2flectedForm : inflectedForms.entrySet()) {
         if (match(f1, flection2flectedForm.getKey())) {
            return flection2flectedForm.getValue();
         }
      }
      return null;
   }

   static boolean match(Flection f1, Flection f2) {
      if (f2.getTense() != null && f1.getTense() != f2.getTense()) {
         return false;
      }
      if (f2.getPerson() != null && f1.getPerson() != f2.getPerson()) {
         return false;
      }
      if (f2.getNumber() != null && f1.getNumber() != f2.getNumber()) {
         return false;
      }
      if (f2.getGender() != null && f1.getGender() != f2.getGender()) {
          return false;
       }
      if (f2.getMode() != null && f1.getMode() != f2.getMode()) {
          return false;
       }
      return true;
   }

   public String getInfinitive() {
      return infinitive;
   }
}
