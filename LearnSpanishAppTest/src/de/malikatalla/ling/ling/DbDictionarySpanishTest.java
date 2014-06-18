package de.malikatalla.ling.ling;

import java.util.List;

import org.junit.Before;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import de.malikatalla.ling.gui.MainActivity;

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

  public void testVerbs() {
    List<String> verbs = d.getAllVerbs();
    assertTrue(verbs.size() > 3000);
    for (String verb : verbs) {
      assertFalse(verb.matches("\\p{Punct}"));
    }
  }

  public void testIndicative() {
    assertNull(d.getInflectedForm("advertir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, null));
    assertEquals("advierto", d.getInflectedForm("advertir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("advert�", d.getInflectedForm("advertir", Tense.SIMPLE_PAST, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("advert�as", d.getInflectedForm("advertir", Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("caminar�is", d.getInflectedForm("caminar", Tense.FUTURE, Person.SECOND, Number.PLURAL, null, Mode.INDICATIVE));
    assertEquals("intentar�an", d.getInflectedForm("intentar", Tense.CONDITIONAL, Person.THIRD, Number.PLURAL, null, Mode.INDICATIVE));
  }

  public void testSubjunctive() {
    assertEquals("falle", d.getInflectedForm("fallar", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
    assertEquals("alz�ramos/alz�semos", d.getInflectedForm("alzar", Tense.IMPERFECT, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
    assertEquals("alz�remos", d.getInflectedForm("alzar", Tense.FUTURE, Person.FIRST, Number.PLURAL, null, Mode.SUBJUNCTIVE));
    assertEquals("coma", d.getInflectedForm("comer", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
  }

  public void testImperative() {
    assertEquals("administra", d.getInflectedForm("administrar", null, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
  }

  public void testIndicativeReflexive() {
    assertEquals("te apasionas", d.getInflectedForm("apasionarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("se apasion�", d.getInflectedForm("apasionarse", Tense.SIMPLE_PAST, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
  }

  public void testImperativeReflexive() {
    assertEquals("acalora", d.getInflectedForm("acalorar", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
    assertEquals("acal�rate", d.getInflectedForm("acalorarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
    assertEquals("acaloremos", d.getInflectedForm("acalorar", Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("acalor�monos", d.getInflectedForm("acalorarse", Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("acalorad", d.getInflectedForm("acalorar", Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("acaloraos", d.getInflectedForm("acalorarse", Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("abarrag�nate", d.getInflectedForm("abarraganarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
    assertEquals("abarragan�monos", d.getInflectedForm("abarraganarse", Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.IMPERATIVE));
  }
  

  public void testImperativeReflexiveApoyarse() {
    assertEquals("ap�yate", d.getInflectedForm("apoyarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
  }
  
  public void testImperativeReflexiveAproximarse() {
    assertEquals("aprox�mate", d.getInflectedForm("aproximarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
    assertEquals("aproximaos", d.getInflectedForm("aproximarse", Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("aprox�mense", d.getInflectedForm("aproximarse", Tense.PRESENT, Person.THIRD, Number.PLURAL, null, Mode.IMPERATIVE));
  }
  
  public void testImperativeReflexiveRepanchingarse() {
    assertEquals("repanch�ngate", d.getInflectedForm("repanchingarse", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.IMPERATIVE));
    assertEquals("repanchingu�monos", d.getInflectedForm("repanchingarse", Tense.PRESENT, Person.FIRST, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("repanch�nguense", d.getInflectedForm("repanchingarse", Tense.PRESENT, Person.THIRD, Number.PLURAL, null, Mode.IMPERATIVE));
  }

  public void testIndicativePastPerfect() {
    assertEquals("he esperado", d.getInflectedForm("esperar", Tense.PAST_PERFECT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
  }

  public void testIrregular() {
    assertEquals("asgo", d.getInflectedForm("asir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("has", d.getInflectedForm("haber", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("estoy", d.getInflectedForm("estar", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("est�s", d.getInflectedForm("estar", Tense.PRESENT, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("ha", d.getInflectedForm("haber", Tense.PRESENT, Person.THIRD, Number.SINGULAR, null, Mode.INDICATIVE));
  }
  
  public void testIrregularIr() {
    assertEquals("voy", d.getInflectedForm("ir", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
  }
  
  public void testNexoCocer() {
    assertEquals("cuezo", d.getInflectedForm("cocer", Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("cocer�s", d.getInflectedForm("cocer", Tense.FUTURE, Person.SECOND, Number.SINGULAR, null, Mode.INDICATIVE));
  }

  public void testAprenderse() {
    Dictionary allInflectedForms = d.getAllInflectedForms("aprenderse");
    assertEquals("aprendaos", d.getInflectedForm("aprenderse", Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
    assertEquals("aprendaos",
        allInflectedForms.getInflectedForm("aprenderse", Tense.PRESENT, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
  }

  // public void testCoverage() throws Exception {
  // int total = 0;
  // int covered = 0; strom.berlin@vattenv.de
  // int verbCount = 0;
  // List<String> allVerbs = d.getAllVerbs();
  // for (String verb : allVerbs) {
  // try {
  // Dictionary allInflectedForms = d.getAllInflectedForms(verb);
  // Log.i(Global.DEBUG, ++verbCount + "/" + allVerbs.size());
  // for (Flection f : Global.getColumnConverter().flectionIterator()) {
  // total++;
  // String inflectedForm = allInflectedForms.getInflectedForm(verb,
  // f.getTense(), f.getPerson(), f.getNumber(), f.getGender(), f.getMode());
  // if (inflectedForm != null) {
  // covered++;
  // }
  // }
  // } catch (Exception e){
  // Log.i(Global.DEBUG, "test coverage: " + verb);
  // throw e;
  // }
  // }
  // String message = "Coverage of inflected forms: " + covered + "/" + total +
  // " (" + (covered * 100.0) / total + "%)";
  // Log.i(Global.DEBUG, message);
  // if (total != covered) {
  // fail(message);
  // }
  // }
}
