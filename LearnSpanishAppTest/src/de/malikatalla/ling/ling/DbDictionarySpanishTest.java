package de.malikatalla.ling.ling;

import org.junit.Before;
import org.junit.Test;

import de.malikatalla.ling.gui.MainActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

public class DbDictionarySpanishTest extends ActivityUnitTestCase<MainActivity> {

  public DbDictionarySpanishTest() {
    super(MainActivity.class);
  }

  public DbDictionarySpanishTest(Class<MainActivity> activityClass) {
    super(activityClass);
  }

  private DbDictionary d;

  @Before
  public void setUp() throws Exception {
    super.setUp();
    Intent mLaunchIntent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);

    startActivity(mLaunchIntent, null, null);
    d = new DbDictionarySpanish(getActivity());
  }

  @Test
  public void testIndicative() {
    assertNull(d.getInflectedForm("advertir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, null));
    assertEquals("advierto", d.getInflectedForm("advertir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("advert�", d.getInflectedForm("advertir", Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("advert�as", d.getInflectedForm("advertir", Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("caminar�is", d.getInflectedForm("caminar", Tense.FUTURE, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
    assertEquals("intentar�an", d.getInflectedForm("intentar", Tense.CONDITIONAL, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
  }

  @Test
  public void testSubjunctive() {
    assertEquals("falle", d.getInflectedForm("fallar", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
    assertEquals("alz�ramos/alz�semos", d.getInflectedForm("alzar", Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
    assertEquals("alz�remos", d.getInflectedForm("alzar", Tense.FUTURE, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
    assertEquals("coma", d.getInflectedForm("comer", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
  }
  
  @Test
  public void testImperative() {
    assertEquals("administra", d.getInflectedForm("administrar", null, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
  }

  @Test
  public void testIndicativeReflexive() {
    assertEquals("te apasionas", d.getInflectedForm("apasionarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("se apasion�", d.getInflectedForm("apasionarse", Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
  }
  
  @Test
  public void testImperativeReflexive() {
    assertEquals("acalora", d.getInflectedForm("acalorar", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
    assertEquals("acal�rate", d.getInflectedForm("acalorarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
    assertEquals("acaloremos", d.getInflectedForm("acalorar", Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("acalor�monos", d.getInflectedForm("acalorarse", Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("acalorad", d.getInflectedForm("acalorar", Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("acaloraos", d.getInflectedForm("acalorarse", Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
  }
  

  @Test
  public void testIndicativePastPerfect() {
    assertEquals("", d.getInflectedForm("esperar", Tense.PAST_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
  }
}
