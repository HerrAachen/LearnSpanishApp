package de.malikatalla.ling;

import java.util.List;

import de.malikatalla.ling.ling.Flection;
import de.malikatalla.ling.ling.Gender;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Number;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;

public interface ColumnConverter {
   /**
    * 
    * @param t
    * @param p
    * @param n
    * @param g
    * @param m
    * @return the string denoting the database column where the conjugation for this combination is stored
    */
   String getDBColumn(Tense t, Person p, Number n, Gender g, Mode m);
   
   List<Flection> flectionIterator();
}
