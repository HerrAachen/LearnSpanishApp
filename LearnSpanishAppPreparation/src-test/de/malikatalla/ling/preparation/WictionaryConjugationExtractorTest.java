package de.malikatalla.ling.preparation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.malikatalla.ling.ling.Flection;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;
import de.malikatalla.ling.ling.Number;

public class WictionaryConjugationExtractorTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testRemoveComments() {
    assertEquals("", HtmlUtils.removeComments("<!-- -->"));
    assertEquals("", HtmlUtils.removeComments("<!-- Incluir la plantilla de conjugación aquí. Por ejemplo:\n"
        + "{{es.v.conj.ar|borr}}          para verbos regulares de la 1ª conjugación, como borrar\n"
        + "{{es.v.conj.er|com}}           para verbos regulares de la 2ª conjugación, como comer\n"
        + "{{es.v.conj.ir|part}}          para verbos regulares de la 3ª conjugación, como partir\n" + "-->"));
    assertEquals("es.v.conj.ar ", HtmlUtils.removeComments("es.v.conj.ar <!-- supplementary information -->"));
  }

  @Test
  public void testRemoveSubstring() {
    assertEquals("", HtmlUtils.removeSubstring("abcdef", 0, 6));
    assertEquals("abdef", HtmlUtils.removeSubstring("abcdef", 2, 3));
    assertEquals("abcf", HtmlUtils.removeSubstring("abcdef", 3, 5));
  }

  @Test
  public void testExtractConjugation() {
    assertEquals("{{es.v.conj.ar|pesquis}}",WictionaryConjugationExtractor.extractConjugationFromWikiArticle("==Conjugación==\n\n{{es.v.conj.ar|pesquis}}-->\n"));
  }
  
  @Test
  public void testParseFlection(){
    assertEquals(new Flection(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE),WictionaryConjugationExtractor.parseFlection("i.p.1s"));
    assertEquals(WictionaryConjugationExtractor.parseFlection("i.p.1s2"),WictionaryConjugationExtractor.parseFlection("i.p.1s"));
  }
  
  @Test
  public void testRemoveOuterBrackets(){
    assertEquals("abc",WictionaryConjugationExtractor.removeOuterBrackets("{{abc}}", new String[]{"{","}"}));
    assertEquals("habed",WictionaryConjugationExtractor.removeOuterBrackets("''habed''**", new String[]{"'","*"}));
    assertEquals("asgo",WictionaryConjugationExtractor.removeOuterBrackets("'''[[asgo]]'''", new String[]{"[","]","*","'"}));
  }
  
  @Test
  public void testParseInflectedForm(){
    assertEquals("abc",WictionaryConjugationExtractor.parseInflectedForm("{{abc}}"));
    assertEquals("habed",WictionaryConjugationExtractor.parseInflectedForm("''habed''**"));
    assertEquals("asgo",WictionaryConjugationExtractor.parseInflectedForm("'''[[asgo]]'''"));
  }

}
