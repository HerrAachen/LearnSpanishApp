package de.malikatalla.ling.preparation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtils {

  public static String removeComments(String article) {
    String preParsedArticle = article;
    boolean closeBracketFound;
    do {
      Matcher openCommentMatcher = Pattern.compile("<!--").matcher(preParsedArticle);
      Matcher closeCommentMatcher = Pattern.compile("-->").matcher(preParsedArticle);
      boolean openBracketFound = openCommentMatcher.find();
      closeBracketFound = false;
      if (openBracketFound) {
        int openBracketPos = openCommentMatcher.start();
        int closeCommentPos = preParsedArticle.length();
        closeBracketFound = closeCommentMatcher.find(openBracketPos);
        if (closeBracketFound){
          closeCommentPos = closeCommentMatcher.end();
        }
        preParsedArticle = removeSubstring(preParsedArticle, openBracketPos, closeCommentPos);
      }
    } while(closeBracketFound);
    return preParsedArticle;
  }

  public static String removeSubstring(String string, int start, int end) {
    if (string==null){
      throw new NullPointerException();
    }
    if (start<0){
      throw new IndexOutOfBoundsException("HtmlUtils.removeSubstring: start index is < 0: " + start);
    }
    if (end>string.length()){
      throw new IndexOutOfBoundsException("HtmlUtils.removeSubstring: end index is > than string length: " + end);
    }
    
    return string.subSequence(0, start) + string.substring(end);
  }
}
