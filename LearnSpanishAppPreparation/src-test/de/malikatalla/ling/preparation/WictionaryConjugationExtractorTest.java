package de.malikatalla.ling.preparation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

}
