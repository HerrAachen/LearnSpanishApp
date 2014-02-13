package de.malikatalla.ling;

import android.provider.BaseColumns;

public class DBContract {

  private DBContract(){};
  
  public static class VerbTable implements BaseColumns {
    public static final String TABLE_NAME = "VERBS";
    public static final String COLUMN_INFINITIVE = "VERB";
    public static final String COLUMN_CONJUGATION = "CONJUGATION";
    public static final String COLUMN_ROOT = "ROOT";
  }
  
  public static class ConjugationTable implements BaseColumns {
    public static final String TABLE_NAME = "REGULAR_CONJUGATIONS";
    public static final String COLUMN_CONJ_ID = "CONJUGATION_ID";
    public static final String COLUMN_CONJ_DESC = "CONJUGATION_DESCRIPTION";
  }
}
