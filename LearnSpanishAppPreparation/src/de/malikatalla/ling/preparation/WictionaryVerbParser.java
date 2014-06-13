package de.malikatalla.ling.preparation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Takes a wictionary xml dump as input and extracts the verbs and their corresponding articles
 * 
 * @author Malik Atalla
 */
public class WictionaryVerbParser {

   public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
      extractVerbs(FileUtils.findWikiDump());
   }

   public static Map<String, String> extractVerbs(File wikiDump) throws ParserConfigurationException, SAXException, IOException {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      WictionaryVerbTextExtractor handler = new WictionaryVerbTextExtractor();
      saxParser.parse(wikiDump, handler);
      return handler.getTitle2Text();
   }

   static class WictionaryVerbTextExtractor extends DefaultHandler {
      private static final String PAGE       = "page";
      private static final String TITLE      = "title";
      private static final String TEXT       = "text";
      boolean                     inPage     = false;
      boolean                     inTitle    = false;
      boolean                     inText     = false;
      boolean                     inVerb     = false;
      String                      title;
      StringBuilder               text;
      Map<String, String>         title2Text = new HashMap<>();

      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
         if (qName.equals(PAGE)) {
            inPage = true;
            title = null;
            text = new StringBuilder();
         } else if (qName.equals(TITLE)) {
            inTitle = true;
         } else if (qName.equals(TEXT)) {
            inText = true;
         }
      }

      public void endElement(String uri, String localName, String qName) throws SAXException {
         if (qName.equals(PAGE)) {
            inPage = false;
            if (inVerb) {
               if (text.toString().contains("{{ES|")) {
                  title2Text.put(title, text.toString());
               }
            }
            inVerb = false;
         } else if (qName.equals(TITLE)) {
            inTitle = false;
         } else if (qName.equals(TEXT)) {
            inText = false;
         }
      }

      public void characters(char[] ch, int start, int length) throws SAXException {
         if (inTitle || inText) {
            StringBuilder builder = new StringBuilder();
            for (int i = start; i < start + length; i++) {
               builder.append(ch[i]);
            }
            String text = builder.toString();
            this.text.append(text);
            if (inTitle) {
               title = text;
            }
            if (inText && text.contains("==Conjugación")) {
               inVerb = true;
            }
         }
      }

      public Map<String, String> getTitle2Text() {
         return title2Text;
      }
   }
}
