package de.malikatalla.ling;

import de.malikatalla.ling.ling.Gender;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Number;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;

public class ColumnConverterSpanish implements ColumnConverter {

   @Override
   public String getDBColumn(Tense t, Person p, Number n, Gender g, Mode m) {
      StringBuilder builder = new StringBuilder();
      builder.append(n.getShortName()).append("_").append(p.getShortName()).append("_").append(m.getShortName()).append("_").append(t.getShortName());
      return builder.toString();
   }
}
