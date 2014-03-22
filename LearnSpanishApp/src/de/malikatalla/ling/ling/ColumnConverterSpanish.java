package de.malikatalla.ling.ling;

import java.util.LinkedList;
import java.util.List;

public class ColumnConverterSpanish implements ColumnConverter {
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
    builder.append(getShortName(n)).append("_").append(getShortName(p)).append("_").append(getShortName(m)).append("_")
        .append(getShortName(t));
    return builder.toString();
  }

  private String getShortName(LingEnum t) {
    return t != null ? t.getShortName() : "NUL";
  }

  public List<Flection> flectionIterator() {
    if (allFlections == null) {
      allFlections = new LinkedList<Flection>();
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
    }
    return allFlections;
  }

}
