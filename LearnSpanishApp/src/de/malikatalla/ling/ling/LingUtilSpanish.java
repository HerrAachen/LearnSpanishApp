package de.malikatalla.ling.ling;

public class LingUtilSpanish implements LingUtil {

  @Override
  public boolean isVowel(char ch) {
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'á' || ch == 'é' || ch == 'í' || ch == 'ó'
        || ch == 'ú' || ch=='ü';
  }

  @Override
  public boolean hasTilde(char ch) {
    return ch == 'á' || ch == 'é' || ch == 'í' || ch == 'ó' || ch == 'ú';
  }

  @Override
  public int firstTilde(String word) {
    if (word==null){
      return -1;
    }
    for(int i=0;i<word.length();i++){
      if (hasTilde(word.charAt(i))){
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean isReflexive(String infinitive) {
    return infinitive!=null && infinitive.toLowerCase().endsWith("se");
  }
}
