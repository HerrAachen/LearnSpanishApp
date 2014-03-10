package de.malikatalla.ling.ling;

public enum Tense implements LingEnum {
   PRESENT("PR"), SIMPLE_PAST("SP"), PAST_PERFECT("PP"), IMPERFECT("IM"), FUTURE("FU"), CONDITIONAL("CO");
   String abbreviation;

   private Tense(String abb) {
      abbreviation = abb;
   }

   @Override
   public String getShortName() {
      return abbreviation;
   }
}
