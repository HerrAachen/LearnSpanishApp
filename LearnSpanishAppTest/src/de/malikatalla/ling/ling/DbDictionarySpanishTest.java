package de.malikatalla.ling.ling;

import org.junit.Before;
import org.junit.Test;

import de.malikatalla.ling.MainActivity;

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
  public void testInflectionsBasicIndicative() {
    assertNull(d.getInflectedForm("advertir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, null));
    assertEquals("advierto", d.getInflectedForm("advertir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("advertí", d.getInflectedForm("advertir", Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("advertías", d.getInflectedForm("advertir", Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("caminaréis", d.getInflectedForm("caminar", Tense.FUTURE, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
    assertEquals("intentarían", d.getInflectedForm("intentar", Tense.CONDITIONAL, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
  }
  
  @Test
  public void testInflectionsBasicSubjunctuve() {
    assertEquals("falle", d.getInflectedForm("fallar", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
//    assertEquals("alzáramos", d.getInflectedForm("alzar", Tense.FUTURE, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
//    assertEquals("coma", d.getInflectedForm("comer", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
  }
}
