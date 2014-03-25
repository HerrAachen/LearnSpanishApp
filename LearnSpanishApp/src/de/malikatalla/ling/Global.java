package de.malikatalla.ling;

import android.content.Context;
import de.malikatalla.ling.ling.ColumnConverter;
import de.malikatalla.ling.ling.ColumnConverterSpanish;
import de.malikatalla.ling.ling.DbDictionarySpanish;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Language;

public class Global {

  public static final String DEBUG = "Test";

  private static Dictionary dictionary;
  private static ColumnConverter columnConverter;

  private static final Language VERB_LANGUAGE = Language.SPANISH;

  public static void init() {
    init(null);
  }

  public static void init(Context context) {
    switch (VERB_LANGUAGE) {
    case SPANISH:
      if (context != null) {
        dictionary = new DbDictionarySpanish(context);
      }
      columnConverter = new ColumnConverterSpanish();
      break;
    case GERMAN:
      break; // not yet implemented
    default:
      return;
    }
    if (dictionary != null) {
      dictionary.loadDictionary();
    }
  }

  public static Dictionary getDictionary() {
    return dictionary;
  }

  public static ColumnConverter getColumnConverter() {
    return columnConverter;
  }

}
