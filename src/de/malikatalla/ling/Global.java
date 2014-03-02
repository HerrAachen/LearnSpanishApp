package de.malikatalla.ling;

import android.content.Context;
import de.malikatalla.ling.ling.DbDictionary;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Language;

public class Global {

   private static Dictionary            dictionary;

   private static final Language        VERB_LANGUAGE   = Language.SPANISH;
   private static final ColumnConverter columnConverter = determineColumnConverter();

   public static void init(Context context) {
      // dictionary = new DummyDictionary();
      dictionary = new DbDictionary(context);
      dictionary.loadDictionary();
   }

   public static Dictionary getDictionary() {
      return dictionary;
   }

   private static ColumnConverter determineColumnConverter() {
      switch (VERB_LANGUAGE) {
      case SPANISH:
         return new ColumnConverterSpanish();
      default:
         return null;
      }
   }

   public static ColumnConverter getColumnConverter() {
      return columnConverter;
   }
}
