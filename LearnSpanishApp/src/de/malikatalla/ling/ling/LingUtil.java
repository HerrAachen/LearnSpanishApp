package de.malikatalla.ling.ling;

public interface LingUtil {

  boolean isVowel(char ch);

  /** @return True iff the character has a tilde */
  boolean hasTilde(char ch);

  /**
   * @return the index of the first character with a tilde, or -1 if the string
   *         is null or if there is no such character.
   */
  int firstTilde(String word);
  
  boolean isReflexive(String infinitive);

  boolean isCompoundTense(Tense t, Mode m);
}
