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
    assertEquals("", HtmlUtils.removeComments("<!-- Incluir la plantilla de conjugaci�n aqu�. Por ejemplo:\n"
        + "{{es.v.conj.ar|borr}}          para verbos regulares de la 1� conjugaci�n, como borrar\n"
        + "{{es.v.conj.er|com}}           para verbos regulares de la 2� conjugaci�n, como comer\n"
        + "{{es.v.conj.ir|part}}          para verbos regulares de la 3� conjugaci�n, como partir\n" + "-->"));
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
    assertEquals("{{es.v.conj.ar|pesquis}}",
        WictionaryConjugationExtractor.extractConjugationFromWikiArticle("==Conjugaci�n==\n\n{{es.v.conj.ar|pesquis}}-->\n"));
    assertEquals(
        "{{es.v.conj.er|hab|irregular=x|notas=Las irregularidades se se�alan en {{resaltar|negrita}}. |i.p.1s=[[he]]|i.p.2s=[[has]]|i.p.2s2=[[has]]|im.2p=''habed''**}}",
        WictionaryConjugationExtractor
            .extractConjugationFromWikiArticle("==Conjugaci�n==\n====Como auxiliar o transitivo====\n"
                + "{{es.v.conj.er|hab|irregular=x|notas=Las irregularidades se se�alan en {{resaltar|negrita}}. |i.p.1s=[[he]]|i.p.2s=[[has]]|i.p.2s2=[[has]]|im.2p=''habed''**}}\n"));
    assertNull(WictionaryConjugationExtractor.extractConjugationFromWikiArticle("==Conjugaci�n==\nComo [[dar]] (incluida la forma [[pronominal]]), + '''por'''"));

  }

  @Test
  public void testParseFlection() {
    assertEquals(new Flection(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE),
        WictionaryConjugationExtractor.parseFlection("i.p.1s"));
    assertEquals(WictionaryConjugationExtractor.parseFlection("i.p.1s2"), WictionaryConjugationExtractor.parseFlection("i.p.1s"));
  }

  @Test
  public void testRemoveOuterBrackets() {
    assertEquals("abc", WictionaryConjugationExtractor.removeOuterBrackets("{{abc}}", new String[] { "{", "}" }));
    assertEquals("habed", WictionaryConjugationExtractor.removeOuterBrackets("''habed''**", new String[] { "'", "*" }));
    assertEquals("asgo",
        WictionaryConjugationExtractor.removeOuterBrackets("'''[[asgo]]'''", new String[] { "[", "]", "*", "'" }));
  }

  @Test
  public void testParseInflectedForm() {
    assertEquals("abc", WictionaryConjugationExtractor.parseInflectedForm("{{abc}}","@"));
    assertEquals("habed", WictionaryConjugationExtractor.parseInflectedForm("''habed''**","@"));
    assertEquals("asgo", WictionaryConjugationExtractor.parseInflectedForm("'''[[asgo]]'''","@"));
  }
  
  @Test
  public void testReplaceInnerSplitters() {
	  assertEquals("es.v.conj.ar|est|irregular=x|i.p.1s={{resaltar@[[estoy]]}}|i.p.2s={{resaltar@[[est�s]]}}", 
			  WictionaryConjugationExtractor.replaceInnerSplitters("es.v.conj.ar|est|irregular=x|i.p.1s={{resaltar|[[estoy]]}}|i.p.2s={{resaltar|[[est�s]]}}", "@"));
  }

}
