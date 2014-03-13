package de.malikatalla.ling.ling;

public class DummyDictionary extends InMemDictionary implements Dictionary {

  @Override
  public void loadDictionary() {
    {
      personalPronouns.addInflectedForm(null, Person.FIRST, Number.SINGULAR, null, "Yo");
      personalPronouns.addInflectedForm(null, Person.SECOND, Number.SINGULAR, null, "Tú");
      personalPronouns.addInflectedForm(null, Person.THIRD, Number.SINGULAR, Gender.MALE, "Él");
      personalPronouns.addInflectedForm(null, Person.THIRD, Number.SINGULAR, Gender.FEMALE, "Ella");
      personalPronouns.addInflectedForm(null, Person.FIRST, Number.PLURAL, Gender.MALE, "Nosotros");
      personalPronouns.addInflectedForm(null, Person.FIRST, Number.PLURAL, Gender.FEMALE, "Nosotras");
      personalPronouns.addInflectedForm(null, Person.SECOND, Number.PLURAL, Gender.MALE, "Vosotros");
      personalPronouns.addInflectedForm(null, Person.SECOND, Number.PLURAL, Gender.FEMALE, "Vosotras");
      personalPronouns.addInflectedForm(null, Person.THIRD, Number.PLURAL, Gender.MALE, "Ellos");
      personalPronouns.addInflectedForm(null, Person.THIRD, Number.PLURAL, Gender.FEMALE, "Ellas");
    }
    {
      Flections verb = new Flections("hacer");
      verb.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, "hago");
      verb.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, "haces");
      verb.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, "hace");
      verb.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, "hacemos");
      verb.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.PLURAL, null, "hacéis");
      verb.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.PLURAL, null, "hacen");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, "hice");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.SINGULAR, null, "hiciste");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, "hizo");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.PLURAL, null, "hicimos");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.PLURAL, null, "hicisteis");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.PLURAL, null, "hicieron");
      verb.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.SINGULAR, null, "hacía");
      verb.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, "hacías");
      verb.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.SINGULAR, null, "hacía");
      verb.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, "hacíamos");
      verb.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.PLURAL, null, "hacíais");
      verb.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.PLURAL, null, "hacían");
      verb.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, "haré");
      verb.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, "harás");
      verb.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, "hará");
      verb.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, "haremos");
      verb.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, "haréis");
      verb.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, "harán");
      infinitive2flections.put(verb.getInfinitive(), verb);
    }
    {
      Flections verb = new Flections("hacer");
      verb.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, "como");
      verb.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, "comes");
      verb.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, "come");
      verb.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, "comemos");
      verb.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.PLURAL, null, "coméis");
      verb.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.PLURAL, null, "comen");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, "comí");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.SINGULAR, null, "comiste");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, "comió");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.PLURAL, null, "comimos");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.PLURAL, null, "comisteis");
      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.PLURAL, null, "comieron");
      verb.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.SINGULAR, null, "comía");
      verb.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, "comías");
      verb.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.SINGULAR, null, "comía");
      verb.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, "comíamos");
      verb.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.PLURAL, null, "comíais");
      verb.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.PLURAL, null, "comían");
      verb.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, "comeré");
      verb.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, "comerás");
      verb.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, "comerá");
      verb.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, "comeremos");
      verb.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, "comeréis");
      verb.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, "comerán");
      infinitive2flections.put(verb.getInfinitive(), verb);
    }
    {
//      Flections verb = new Flections("tener");
//      verb.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, "tengo");
//      verb.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, "tienes");
//      verb.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, "tiene");
//      verb.addInflectedForm(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, "tenemos");
//      verb.addInflectedForm(Tense.PRESENT, Person.SECOND, Number.PLURAL, null, "tenéis");
//      verb.addInflectedForm(Tense.PRESENT, Person.THIRD, Number.PLURAL, null, "tienen");
//      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, "tuve");
//      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.SINGULAR, null, "tuviste");
//      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, "tuvo");
//      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.FIRST, Number.PLURAL, null, "tuvimos");
//      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.SECOND, Number.PLURAL, null, "tuvisteis");
//      verb.addInflectedForm(Tense.SIMPLE_PAST, Person.THIRD, Number.PLURAL, null, "tuvieron");
//      verb.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.SINGULAR, null, "tenía");
//      verb.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, "tenías");
//      verb.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.SINGULAR, null, "tenía");
//      verb.addInflectedForm(Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, "teníamos");
//      verb.addInflectedForm(Tense.IMPERFECT, Person.SECOND, Number.PLURAL, null, "teníais");
//      verb.addInflectedForm(Tense.IMPERFECT, Person.THIRD, Number.PLURAL, null, "tenían");
//      verb.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.SINGULAR, null, "tendré");
//      verb.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, "tendrás");
//      verb.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.SINGULAR, null, "tendrá");
//      verb.addInflectedForm(Tense.FUTURE, Person.FIRST, Number.PLURAL, null, "tendremos");
//      verb.addInflectedForm(Tense.FUTURE, Person.SECOND, Number.PLURAL, null, "tendréis");
//      verb.addInflectedForm(Tense.FUTURE, Person.THIRD, Number.PLURAL, null, "tendrán");
//      infinitive2flections.put(verb.getInfinitive(), verb);
    }
  }

@Override
public String getReflexivePronoun(Tense t, Person p, Number n, Gender g, Mode m) {
	// TODO Auto-generated method stub
	return null;
}
}
