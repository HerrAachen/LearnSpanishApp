package de.malikatalla.ling.ling;

import org.junit.Test;

import de.malikatalla.ling.ling.Flection;
import de.malikatalla.ling.ling.Flections;
import de.malikatalla.ling.ling.Gender;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;
import de.malikatalla.ling.ling.Number;

import junit.framework.TestCase;

public class FlectionsTest extends TestCase {

  @Test
  public void testMatch(){
    Flection f1 = new Flection(Tense.PRESENT, Person.FIRST, Number.SINGULAR, Gender.MALE, Mode.INDICATIVE);
    Flection f2 = new Flection(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE);
    Flection f3 = new Flection(Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.INDICATIVE);
    assertTrue(Flections.match(f1,f2));
    assertTrue(Flections.match(f1,f1));

    assertFalse(Flections.match(f2,f1));
    assertFalse(Flections.match(f1,f3));
    assertFalse(Flections.match(f2,f3));
  }
}
