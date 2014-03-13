package de.malikatalla.ling.ling;

public enum Mode implements LingEnum {
   INDICATIVE("IND"), SUBJUNCTIVE("SUB"), IMPERATIVE("IMP");
   String abbreviation;

   private Mode(String abb) {
      abbreviation = abb;
   }

   @Override
   public String getShortName() {
      return abbreviation;
   }
}
