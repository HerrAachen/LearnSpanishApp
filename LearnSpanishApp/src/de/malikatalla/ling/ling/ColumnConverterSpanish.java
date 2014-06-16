package de.malikatalla.ling.ling;

import java.util.ArrayList;
import java.util.List;

/**
 * The method getDBColumn produces the same strings as used in Wiktionary in the
 * descriptions for the conjugations
 * 
 * @author Malik
 * 
 */
public class ColumnConverterSpanish extends ColumnConverter {
  static final String SEPARATOR_DB = "_";
  static final String SEPARATOR_PARSER = ".";
  private List<Flection> allFlections = null;

  @Override
  public String getDBColumn(Tense t, Person p, Number n, Gender g, Mode m) {
    if (m == null) {
      return null;
    }
    if (m.equals(Mode.IMPERATIVE)) {
      t = null;
    }
    if (m.equals(Mode.PARTICIPLE)) {
      t = null;
      p = null;
      n = null;
      g = null;
    }
    StringBuilder builder = new StringBuilder();
    builder.append(getShortName(m)).append(SEPARATOR_DB);
    if (!m.equals(Mode.IMPERATIVE) && t != null) {
      builder.append(getShortName(t)).append(SEPARATOR_DB);
    }
    if (p != null && n != null) {
      builder.append(getShortName(p)).append(getShortName(n));
    }
    return builder.toString();
  }

  @Override
  public Flection parseDBColumn(String input) {
    if (input == null) {
      throw new NullPointerException("ColumnConverterSpanish.parseColumn: input must not be null");
    }
    String[] parts = input.split("\\.");
    Mode mode = getMode(parts[0], parts);
    if (mode==null){
      return null;
    }
    if (mode==Mode.PARTICIPLE){
    	return new Flection(null, null, null, null, Mode.PARTICIPLE);
    }
    if (parts.length < 2) {
    	return null;
    }
    Tense tense = null;
    Person person = null;
    Number number = null;
    int personIndex = 2;
    if (mode.equals(Mode.IMPERATIVE)) {
      personIndex = 1;
    } else {
      tense = getTense(parts[1]);
    }
    if (parts.length < personIndex + 1) {
      throw new RuntimeException("ColumnConverterSpanish.parseDBColumn: Could not parse Person and Number from " + input);
    }
    person = getPerson(parts[personIndex].substring(0, 1));
    number = getNumber(parts[personIndex].substring(1, 2));
    return new Flection(tense, person, number, null, mode);
  }

  private Mode getMode(String modeString, String[] allParts) {
    if (modeString.equals("i")) {
      if (allParts.length==2){//spelling error
        return Mode.IMPERATIVE;
      }
      return Mode.INDICATIVE;
    } else if (modeString.equals("im") || modeString.equals("imppron")) {
      return Mode.IMPERATIVE;
    } else if (modeString.equals("p")) {
      return Mode.PARTICIPLE;
    } else if (modeString.equals("s")) {
      return Mode.SUBJUNCTIVE;
    }
    return null;
  }

  private Tense getTense(String modeString) {
	  if (modeString==null){
		  return null;
	  }
    if (modeString.equals("p")) {
      return Tense.PRESENT;
    } else if (modeString.equals("f")) {
      return Tense.FUTURE;
    } else if (modeString.equals("c")) {
      return Tense.CONDITIONAL;
    } else if (modeString.equals("pp")) {
      return Tense.SIMPLE_PAST;
    } else if (modeString.equals("pi")) {
      return Tense.IMPERFECT;
    } else if (modeString.equals("pcp")){
    	return Tense.PLUSCUAM_PERFECT;
    } else if (modeString.equals("cp")){
    	return Tense.CONDITIONAL_PERFECT;
    } else if (modeString.equals("fp")){
    	return Tense.FUTURE_PERFECT;
    } else if (modeString.equals("pap")){
    	return Tense.PAST_PERFECT;
    }
    return null;
  }

  private Person getPerson(String personString){
    if (personString.equals("1")){
      return Person.FIRST;
    } else if (personString.equals("2")){
      return Person.SECOND;
    } else if (personString.equals("3")){
      return Person.THIRD;
    }
    return null;
  }
  
  private Number getNumber(String personString){
    if (personString.equals("s")){
      return Number.SINGULAR;
    } else if (personString.equals("p")){
      return Number.PLURAL;
    }
    return null;
  }

  private String getShortName(Tense t) {
    if (t == null)
      return null;
    switch (t) {
    case PRESENT:
      return "p";
    case FUTURE:
      return "f";
    case CONDITIONAL:
      return "c";
    case SIMPLE_PAST:
      return "pp";
    case IMPERFECT:
      return "pi";
    case PLUSCUAM_PERFECT:
    	return "pcp";
    case CONDITIONAL_PERFECT:
    	return "cp";
    case FUTURE_PERFECT:
    	return "fp";
    case PAST_PERFECT:
    	return "pap";
    default:
      return null;
    }
  }

  private String getShortName(Mode m) {
    switch (m) {
    case INDICATIVE:
      return "i";
    case SUBJUNCTIVE:
      return "s";
    case IMPERATIVE:
      return "im";
    case PARTICIPLE:
      return "p"; // this one is not in Wikipedia
    default:
      return null;
    }
  }

  private String getShortName(Number n) {
    switch (n) {
    case SINGULAR:
      return "s";
    case PLURAL:
      return "p";
    default:
      return null;
    }
  }

  private String getShortName(Person p) {
    switch (p) {
    case FIRST:
      return "1";
    case SECOND:
      return "2";
    case THIRD:
      return "3";
    default:
      return null;
    }
  }

  /** Careful when editing: order matters! */
  public List<Flection> flectionIterator() {
    if (allFlections == null) {
      allFlections = new ArrayList<Flection>();
      allFlections.add(new Flection(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PRESENT, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.IMPERFECT, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(null, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
      allFlections.add(new Flection(null, Person.THIRD, Number.SINGULAR, null, Mode.IMPERATIVE));
      allFlections.add(new Flection(null, Person.FIRST, Number.PLURAL, null, Mode.IMPERATIVE));
      allFlections.add(new Flection(null, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
      allFlections.add(new Flection(null, Person.THIRD, Number.PLURAL, null, Mode.IMPERATIVE));
      allFlections.add(new Flection(null, null, null, null, Mode.PARTICIPLE));
      //compound flections should be last
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL_PERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL_PERFECT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL_PERFECT, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL_PERFECT, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.CONDITIONAL_PERFECT, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE_PERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE_PERFECT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE_PERFECT, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE_PERFECT, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.FUTURE_PERFECT, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PAST_PERFECT, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE));
      allFlections.add(new Flection(Tense.PLUSCUAM_PERFECT, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE));
    }
    return allFlections;
  }

  @Override
  public String flectionToString(Flection f) {
    return f.getMode() + ", " + f.getTense() + ", " + f.getNumber() + ", " + f.getPerson() + " Person";
  }

}
