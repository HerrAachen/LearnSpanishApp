package de.malikatalla.ling.ling;

public enum Person implements LingEnum {
   FIRST("1P"), SECOND("2P"), THIRD("3P");
   String abbreviation;

   private Person(String abb) {
      abbreviation = abb;
   }

   @Override
   public String getShortName() {
      return abbreviation;
   }
}
