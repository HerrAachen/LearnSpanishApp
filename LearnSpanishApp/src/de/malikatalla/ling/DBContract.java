package de.malikatalla.ling;

import android.provider.BaseColumns;

public class DBContract {

   private DBContract() {
   };

   public static class VerbTable implements BaseColumns {
      public static final String TABLE_NAME         = "VERBS";
      public static final String COLUMN_INFINITIVE  = "VERB";
      public static final String COLUMN_CONJUGATION = "CONJUGATION";
      public static final String COLUMN_ROOT        = "ROOT";
   }

   public static class ConjugationTable implements BaseColumns {
      public static final String TABLE_NAME              = "REGULAR_CONJUGATIONS";
      public static final String COLUMN_CONJ_ID          = "CONJUGATION_ID";
      public static final String COLUMN_CONJ_DESC        = "CONJUGATION_DESCRIPTION";
      public static final String COLUMN_1P_SING_IND_PRES = "SING_1P_IND_PRES";
      public static final String COLUMN_2P_SING_IND_PRES = "SING_2P_IND_PRES";
      public static final String COLUMN_3P_SING_IND_PRES = "SING_3P_IND_PRES";
      public static final String COLUMN_1P_PLUR_IND_PRES = "PLUR_1P_IND_PRES";
      public static final String COLUMN_2P_PLUR_IND_PRES = "PLUR_2P_IND_PRES";
      public static final String COLUMN_3P_PLUR_IND_PRES = "PLUR_3P_IND_PRES";
   }
}
