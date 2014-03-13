package de.malikatalla.ling.ling;

public enum Gender implements LingEnum {
   FEMALE("F"), MALE("M");
   String abbreviation;

   private Gender(String abb) {
      abbreviation = abb;
   }

   @Override
   public String getShortName() {
      return abbreviation;
   }

}
