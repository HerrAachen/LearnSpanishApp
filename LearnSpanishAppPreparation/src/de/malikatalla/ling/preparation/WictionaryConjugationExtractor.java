package de.malikatalla.ling.preparation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class WictionaryConjugationExtractor {

   private static final String  conjugationTitle = "==Conjugación==";
   private static final String  conjugationES    = "es.v.conj";
   private static final String  PLANTILLA        = "Plantilla:";
   private static final Pattern LINE_BREAK       = Pattern.compile("[\\n\\r]");

   public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
      extractConjugations("/home/gabi/Documents/Maliks/eswiktionary-20140130-pages-meta-current.xml");
   }

   public static Map<String, ConjugationDescription> extractConjugations(String wictionaryDump) throws ParserConfigurationException, SAXException, IOException {
      Map<String, String> title2article = WictionaryVerbParser.extractVerbs(wictionaryDump);
      System.out.println(title2article.size() + " articles");
      Map<String, ConjugationDescription> inf2conj = new HashMap<String, ConjugationDescription>();
      FrequencyMap<String> basicConjugs = new FrequencyMap<>();
      for (Map.Entry<String, String> entry : title2article.entrySet()) {
        System.out.print(entry.getKey() + " -> ");
         String conjugationDescriptionString = extractConjugationFromWikiArticle(entry.getValue());
         if (conjugationDescriptionString != null) {
            ConjugationDescription desc = parseConjugationDescription(conjugationDescriptionString);
            System.out.println(conjugationDescriptionString + " " + desc.getBasicConjugation() + " " + desc.getRoot());
            if (desc != null && desc.getBasicConjugation().contains(conjugationES)) {
               inf2conj.put(entry.getKey(), desc);
               basicConjugs.increment(desc.getBasicConjugation());
            }
         } else {
           System.out.println("NULL");
         }
      }
      System.out.println("Number of basic conjugations:" + basicConjugs.size());
      System.out.println("Number of verbs:" + basicConjugs.getFrequencySum());
      for (Entry<String, AtomicInteger> entry : basicConjugs.getSortedByFrequency()) {
         System.out.println(entry);
      }
      return inf2conj;
   }

   private static ConjugationDescription parseConjugationDescription(String description) {
      if (description != null) {
         description = removeOuterBrackets(description);
         String[] parts = description.split("\\|");
         // first element describes the regular conjugation
         String basicConjugation = parts[0];
         // String basicConjugation =
         // description.substring(description.indexOf("{{") + 2, indexOfPipe
         // >= 0 ?
         // indexOfPipe
         // : description.length() - 2);
         if (basicConjugation.startsWith(PLANTILLA)) {
            basicConjugation = basicConjugation.substring(PLANTILLA.length());
         }
         // next search the root, usually second element, sometimes there is
         // more
         // than one root.
         String root = "";
         if (parts.length > 1) {
            int i = 1;
            while (i < parts.length && parts[i].contains("=")) {
               i++;
            }
            while (i < parts.length && !parts[i].contains("=")) {
               root += ";" + parts[i++];
            }
            if (root.length() > 0) {
               root = root.substring(1);
            }
         }
         ConjugationDescription desc = new ConjugationDescription();
         desc.setBasicConjugation(basicConjugation.trim());
         desc.setRoot(root);
         return desc;
      }
      return null;
   }

   private static String removeOuterBrackets(String description) {
      String res = description;
      res = res.trim();
      while (res.startsWith("{")) {
         res = res.substring(1);
      }
      while (res.endsWith("}")) {
         res = res.substring(0, res.length() - 1);
      }
      return res;
   }

   public static String extractConjugationFromWikiArticle(String article) {
      Pattern p = Pattern.compile(conjugationTitle);
      Matcher matcher = p.matcher(article);
      boolean found = matcher.find();
      if (found) {
         String conjugationString = article.substring(matcher.end(), article.length());
         Matcher lineBreakMatcher = LINE_BREAK.matcher(conjugationString);
         int startIndex = conjugationString.indexOf("{{");
         if (startIndex >= 0) {
            boolean foundLineBreak = lineBreakMatcher.find(startIndex);
            conjugationString = conjugationString.substring(startIndex, foundLineBreak ? lineBreakMatcher.end() : conjugationString.length());
         }
         return conjugationString.trim();
      }
      return null;
   }

}
