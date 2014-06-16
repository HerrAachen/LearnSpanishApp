package de.malikatalla.ling.preparation;

import java.util.Map;

import de.malikatalla.ling.Global;
import de.malikatalla.ling.ling.Flection;
import de.malikatalla.ling.ling.LingUtil;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;
import de.malikatalla.ling.ling.Number;

/**
 * Contains corrections of the conjugation that cannot be read properly from the
 * wiki xml dump
 */

public class ConjugationPatchSpanish implements ConjugationPatch {

  public void applyPatch(Map<String, ConjugationDescription> conjugations) {
    ConjugationDescription haber = conjugations.get("haber");
    haber.getIrregularFlections().addInflectedForm(
        new Flection(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE), "ha");
    correctReflexiveConjugations(conjugations);
  }

  /**
   * When there is a reflexive verb with non reflexive regular conjugations,
   * but reflexive regular conjugation exists, prefer the latter.
   */
  private void correctReflexiveConjugations(Map<String, ConjugationDescription> conjugations) {
    LingUtil lingUtil = Global.getLingUtil();
    for(Map.Entry<String,ConjugationDescription> entry: conjugations.entrySet()){
      String verb = entry.getKey();
      if (lingUtil.isReflexive(verb)){
        ConjugationDescription conj = entry.getValue();
        if (!conj.getBasicConjugation().endsWith("se")){
        //TODO
        }
      }
    }
  }
}
