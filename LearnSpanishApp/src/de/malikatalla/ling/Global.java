package de.malikatalla.ling;

import android.content.Context;
import de.malikatalla.ling.ling.ColumnConverter;
import de.malikatalla.ling.ling.ColumnConverterSpanish;
import de.malikatalla.ling.ling.DbDictionarySpanish;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Language;
import de.malikatalla.ling.ling.LingUtil;
import de.malikatalla.ling.ling.LingUtilSpanish;

public class Global {

  public static final String DEBUG = "Test";
  public static final String STATISTICS = "STATS";

  private static Dictionary dictionary;
  private static ColumnConverter columnConverter;
  private static LingUtil lingUtil;

  private static final Language VERB_LANGUAGE = Language.SPANISH;
  private static boolean isInitiated = false;
  private static OverallStatistics stats = new OverallStatistics();

  public static void init() {
    init(null);
  }

  public static OverallStatistics getStats() {
    return stats;
  }

  public static void init(Context context) {
    if (!isInitiated) {
      isInitiated = true;
      switch (VERB_LANGUAGE) {
      case SPANISH:
        if (context != null) {
          dictionary = new DbDictionarySpanish(context);
        }
        columnConverter = new ColumnConverterSpanish();
        lingUtil = new LingUtilSpanish();
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
  }

  public static Dictionary getDictionary() {
    if (!isInitiated) {
      init();
    }
    return dictionary;
  }

  public static ColumnConverter getColumnConverter() {
    if (!isInitiated) {
      init();
    }
    return columnConverter;
  }
  
  public static LingUtil getLingUtil(){
    if (!isInitiated){
      init();
    }
    return lingUtil;
  }
  
  public static Language getVerbLanguage(){
	  return VERB_LANGUAGE;
  }

}
