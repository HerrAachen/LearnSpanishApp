package de.malikatalla.ling.ling;

public enum Number implements LingEnum {
   SINGULAR("SI"), PLURAL("PL");
   String abbreviation;

   private Number(String abb) {
      abbreviation = abb;
   }

   @Override
   public String getShortName() {
      return abbreviation;
   }
}
