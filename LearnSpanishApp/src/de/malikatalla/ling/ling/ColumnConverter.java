package de.malikatalla.ling.ling;

import java.util.List;

public interface ColumnConverter {

  /**
   * 
   * @param t
   * @param p
   * @param n
   * @param g
   * @param m
   * @return the string denoting the database column where the conjugation for
   *         this combination is stored
   */
  String getDBColumn(Tense t, Person p, Number n, Gender g, Mode m);
  
  Flection parseDBColumn(String input);

  List<Flection> flectionIterator();
}
