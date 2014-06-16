package de.malikatalla.ling.ling;

public class LingUtilSpanish implements LingUtil {

  @Override
  public boolean isVowel(char ch) {
    return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == '�' || ch == '�' || ch == '�' || ch == '�'
        || ch == '�' || ch=='�';
  }

  @Override
  public boolean hasTilde(char ch) {
    return ch == '�' || ch == '�' || ch == '�' || ch == '�' || ch == '�';
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
}
