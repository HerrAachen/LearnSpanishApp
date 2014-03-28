package de.malikatalla.ling.ling;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class ColumnConverterSpanishTest extends TestCase {
  private ColumnConverter cc;
  
  @Before
  public void setUp(){
    cc = new ColumnConverterSpanish();
  }
 
  public void testGetColumnDB(){
    assertEquals("i_p_1s",cc.getDBColumn(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE));
    assertEquals("s_pi_2s",cc.getDBColumn(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE));
    assertEquals("im_2p",cc.getDBColumn(null, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE));
  }
  
  public void testParseColumn(){
    assertEquals(new Flection(Tense.PRESENT, Person.FIRST, Number.SINGULAR, null, Mode.INDICATIVE),cc.parseDBColumn("i.p.1s"));
    assertEquals(new Flection(Tense.IMPERFECT, Person.SECOND, Number.SINGULAR, null, Mode.SUBJUNCTIVE),cc.parseDBColumn("s.pi.2s"));
    assertEquals(new Flection(null, Person.SECOND, Number.PLURAL, null, Mode.IMPERATIVE),cc.parseDBColumn("im.2p"));
  }
  
  public void testInEqualsOut(){
    for(Flection f: cc.flectionIterator()){
      String dbColumn = cc.getDBColumn(f.getTense(), f.getPerson(), f.getNumber(), f.getGender(), f.getMode());
      String wikiInflection = dbColumn.replace(ColumnConverterSpanish.SEPARATOR_DB, ColumnConverterSpanish.SEPARATOR_PARSER);
      Flection parsedFlection = cc.parseDBColumn(wikiInflection);
      assertNotNull("parsed flection null for " + wikiInflection,parsedFlection);
      assertEquals(f,parsedFlection);
    }
  }
  
}
