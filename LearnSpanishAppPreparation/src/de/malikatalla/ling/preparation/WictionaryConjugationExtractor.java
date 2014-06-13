package de.malikatalla.ling.preparation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.malikatalla.ling.Global;
import de.malikatalla.ling.ling.Flection;

public class WictionaryConjugationExtractor {

  private static final String conjugationTitle = "==Conjugación==";
  private static final String conjugationES = "es.v.conj";
  private static final String PLANTILLA = "Plantilla:";
  private static final Pattern LINE_BREAK = Pattern.compile("[\\n\\r]");
  private static String illegalConjugations;
  
  static {
	  switch(Global.getVerbLanguage()){
	  case SPANISH:illegalConjugations = "resaltar";
	  default:
	  }
  }

  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
    extractConjugations(FileUtils.findWikiDump());
  }

  public static Map<String, ConjugationDescription> extractConjugations(File wictionaryDump)
      throws ParserConfigurationException, SAXException, IOException {
    Map<String, String> title2article = WictionaryVerbParser.extractVerbs(wictionaryDump);
    System.out.println(title2article.size() + " articles");
    Map<String, ConjugationDescription> inf2conj = new HashMap<String, ConjugationDescription>();
    FrequencyMap<String> basicConjugs = new FrequencyMap<>();
    for (Map.Entry<String, String> entry : title2article.entrySet()) {
      System.out.print(entry.getKey() + " -> ");
      String conjugationDescriptionString = extractConjugationFromWikiArticle(entry.getValue());
      if (conjugationDescriptionString != null) {
        ConjugationDescription desc = parseConjugationDescription(conjugationDescriptionString);
        if (desc != null && desc.getBasicConjugation().contains(conjugationES)) {
          System.out.println(conjugationDescriptionString + " -- " + desc);
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
    	System.out.println("conjugation description: " + description);
      description = removeOuterBrackets(description, new String[] { "{", "}" });
      final String innerSplitter = "@";
      description = replaceInnerSplitters(description, innerSplitter);
      String[] parts = description.split("\\|");
      // first element describes the regular conjugation
      String basicConjugation = parts[0];
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
      // parse irregular conjugations
      for (int i = 1; i < parts.length; i++) {
        String part = parts[i];
        String[] leftAndRight = part.split("=");
        if (leftAndRight != null && leftAndRight.length == 2) {
          String left = leftAndRight[0];
          String right = leftAndRight[1];
          Flection flection = parseFlection(left);
          if (flection != null) {
            String inflectedForm = parseInflectedForm(right, innerSplitter);
            desc.getIrregularFlections().addInflectedForm(flection, inflectedForm);
          }
        }
      }
      return desc;
    }
    return null;
  }

  static String replaceInnerSplitters(String description, String replacement) {
	  int bracketLevel = 0;
	  for(int i=0;i<description.length();i++){
		  char ch = description.charAt(i);
		if (ch=='{'){
			  bracketLevel++;
		  }
		  if (ch=='}'){
			  bracketLevel--;
		  }
		  if (ch=='|' && bracketLevel>0){
			  description = description.substring(0, i) + replacement + description.substring(i+1);
		  }
	  }
	  return description;
}

static String parseInflectedForm(String right, String splitter) {
    String inner = removeOuterBrackets(right, new String[]{"{","}","[","]","*","'"});
    String[] innerParts = inner.split(splitter);
    try {
    if (innerParts.length>=1){
    	if (innerParts[0].equals(illegalConjugations)){
    		return removeOuterBrackets(innerParts[1],new String[]{"{","}","[","]","*","'"});
    	}
      return innerParts[0];
    }
    }
    catch(Exception e){
    	System.out.println("parse inflected form: " + right);
    	e.printStackTrace();
    }
    throw new RuntimeException(WictionaryConjugationExtractor.class.getSimpleName() + ": Something bad happened");
  }

  static Flection parseFlection(String flectionString) {
    Flection flection = Global.getColumnConverter().parseDBColumn(flectionString);
    return flection;
  }

  static String removeOuterBrackets(String description, String[] brackets) {
    String res = description;
    res = res.trim();
    boolean found = true;
    while (found) {
      found = false;
      for (String bracket : brackets) {
        while (res.startsWith(bracket)) {
          res = res.substring(bracket.length());
          found = true;
        }
        while (res.endsWith(bracket)) {
          res = res.substring(0, res.length() - bracket.length());
          found = true;
        }
      }
    };
    return res;
  }

  public static String extractConjugationFromWikiArticle(String article) {
    String preParsed = HtmlUtils.removeComments(article);
    Pattern p = Pattern.compile(conjugationTitle);
    Matcher matcher = p.matcher(preParsed);
    boolean found = matcher.find();
    if (found) {
      String conjugationString = preParsed.substring(matcher.end(), preParsed.length());
      int startIndex = conjugationString.indexOf("{{");
      int endIndex = findEndIndex(conjugationString,startIndex);
      endIndex = endIndex < 0 ? conjugationString.length() : endIndex + 2;
      if (startIndex >= 0) {
        conjugationString = conjugationString.substring(startIndex, endIndex);
        return conjugationString.trim();
      }
    }
    return null;
  }

  private static int findEndIndex(String conjugationString, int startIndex) {
    int openedBrackets = 0;
    int i = startIndex+1;
    try {
    while(openedBrackets>=0 && i<conjugationString.length()-1){
      i++;
      char charAt = conjugationString.charAt(i);
      if (charAt=='{'){
        openedBrackets++;
      }
      if (charAt=='}'){
        openedBrackets--;
      }
    }
    } catch(IndexOutOfBoundsException e){
      System.out.println(conjugationString);
      throw e;
    }
    return i;
  }
}
