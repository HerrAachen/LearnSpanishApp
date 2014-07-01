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

  @Override
  public boolean isReflexive(String infinitive) {
    return infinitive!=null && infinitive.toLowerCase().endsWith("se");
  }
  
  @Override
  public boolean isCompoundTense(Tense t, Mode m) {
    if (t == null || m == null) {
      return false;
    }
    return (t.equals(Tense.PAST_PERFECT) || t.equals(Tense.PLUSCUAM_PERFECT) || t.equals(Tense.FUTURE_PERFECT) || t.equals(Tense.CONDITIONAL_PERFECT)) && (m.equals(Mode.INDICATIVE) || m.equals(Mode.SUBJUNCTIVE));
  }
}
