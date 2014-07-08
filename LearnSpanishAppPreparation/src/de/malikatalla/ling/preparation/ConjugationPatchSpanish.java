package de.malikatalla.ling.preparation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.malikatalla.ling.Global;
import de.malikatalla.ling.ling.Flection;
import de.malikatalla.ling.ling.Flections;
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
    haber.getIrregularFlections().addInflectedForm(new Flection(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE), "ha");
    correctReflexiveConjugations(conjugations);
    correctIr(conjugations);
  }

  private void correctIr(Map<String, ConjugationDescription> conjugations) {
    conjugations.remove("irse");
    ConjugationDescription ir = conjugations.get("ir");
    ir.setRoot("");
    Flections f = ir.getIrregularFlections();
    f.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, "voy");
    f.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, "vas");
    f.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, "va");
    f.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, "vamos");
    f.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.PLURAL, null, "vais");
    f.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.PLURAL, null, "van");
    
    f.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.SINGULAR, null, "iba");
    f.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, "ibas");
    f.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.SINGULAR, null, "iba");
    f.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, "íbamos");
    f.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.PLURAL, null, "ibais");
    f.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.PLURAL, null, "iban");

    f.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, "fui");
    f.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.SINGULAR, null, "fuiste");
    f.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, "fue");
    f.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.PLURAL, null, "fuimos");
    f.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.PLURAL, null, "fuisteis");
    f.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.PLURAL, null, "fueron");

    f.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, "iré");
    f.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, "irás");
    f.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, "irá");
    f.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, "iremos");
    f.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, "iréis");
    f.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, "irán");
    
    f.addInflectedForm(Tense.CONDITIONAL, Person.FIRST, Number.SINGULAR, null, "iría");
    f.addInflectedForm(Tense.CONDITIONAL, Person.SECOND, Number.SINGULAR, null, "irías");
    f.addInflectedForm(Tense.CONDITIONAL, Person.THIRD, Number.SINGULAR, null, "iría");
    f.addInflectedForm(Tense.CONDITIONAL, Person.FIRST, Number.PLURAL, null, "iríamos");
    f.addInflectedForm(Tense.CONDITIONAL, Person.SECOND, Number.PLURAL, null, "iríais");
    f.addInflectedForm(Tense.CONDITIONAL, Person.THIRD, Number.PLURAL, null, "irían");
    
    f.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "vaya");
    f.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "vayas");
    f.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "vaya");
    f.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE, "vayamos");
    f.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE, "vayáis");
    f.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE, "vayan");
    
    f.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "fuera;fuese");
    f.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "fueras;fueses");
    f.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "fuera;fuese");
    f.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE, "fuéramos;fuésemos");
    f.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE, "fuerais;fueseis");
    f.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE, "fueran;fuesen");

    f.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "fuere");
    f.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "fueres");
    f.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE, "fuere");
    f.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE, "fuéremos");
    f.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE, "fuereis");
    f.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE, "fueren");
    
    f.addInflectedForm(null, Person.SECOND, Number.SINGULAR, null,Mode.IMPERATIVE, "ve");
    f.addInflectedForm(null, Person.THIRD, Number.SINGULAR, null,Mode.IMPERATIVE, "vaya");
    f.addInflectedForm(null, Person.FIRST, Number.PLURAL, null,Mode.IMPERATIVE, "vayamos");
    f.addInflectedForm(null, Person.SECOND, Number.PLURAL, null,Mode.IMPERATIVE, "id");
    f.addInflectedForm(null, Person.THIRD, Number.PLURAL, null,Mode.IMPERATIVE, "vayan");
    
    f.addInflectedForm(null, null, null, null,Mode.PARTICIPLE, "ido");
  }

  /**
   * When there is a reflexive verb with non reflexive regular conjugations, but
   * reflexive regular conjugation exists, prefer the latter.
   */
  private void correctReflexiveConjugations(Map<String, ConjugationDescription> conjugations) {
    //collect all regular conjugation names
    Set<String> regConj = new HashSet<>();
    for (Map.Entry<String, ConjugationDescription> entry : conjugations.entrySet()) {
      regConj.add(entry.getValue().getBasicConjugation());
    }
    LingUtil lingUtil = Global.getLingUtil();
    for (Map.Entry<String, ConjugationDescription> entry : conjugations.entrySet()) {
      String verb = entry.getKey();
      if (lingUtil.isReflexive(verb)) {
        ConjugationDescription conj = entry.getValue();
        if (!conj.getBasicConjugation().endsWith("se")) {
          String reflexiveConjugation = conj.getBasicConjugation() + "se";
          if (regConj.contains(reflexiveConjugation)){
            System.out.println("Changing conjugation of " + verb + " from " + conj.getBasicConjugation() + " to " + reflexiveConjugation);
            conj.setBasicConjugation(reflexiveConjugation);
          }
        }
      }
    }
  }
}
