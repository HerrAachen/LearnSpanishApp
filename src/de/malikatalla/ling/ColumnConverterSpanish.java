package de.malikatalla.ling;

import java.util.LinkedList;
import java.util.List;

import de.malikatalla.ling.ling.Flection;
import de.malikatalla.ling.ling.Gender;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Number;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;

public class ColumnConverterSpanish implements ColumnConverter {

	private List<Flection> allFlections = null;

	@Override
	public String getDBColumn(Tense t, Person p, Number n, Gender g, Mode m) {
		StringBuilder builder = new StringBuilder();
		builder.append(n.getShortName()).append("_").append(p.getShortName()).append("_").append(m.getShortName())
				.append("_").append(t.getShortName());
		return builder.toString();
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
			allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.SIMPLE_PAST, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, Mode.SUBJUNCTIVE));
			allFlections.add(new Flection(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, Mode.SUBJUNCTIVE));
		}
		return allFlections;
	}
}
