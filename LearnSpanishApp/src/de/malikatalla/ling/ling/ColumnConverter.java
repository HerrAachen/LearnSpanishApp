package de.malikatalla.ling.ling;

import java.util.List;
import java.util.Random;

public abstract class ColumnConverter {

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
  public abstract String getDBColumn(Tense t, Person p, Number n, Gender g, Mode m);
  
  public abstract Flection parseDBColumn(String input);

  public abstract List<Flection> flectionIterator();
  
  public Flection getRandomFlection(){
    List<Flection> allFlections = flectionIterator();
    Random r = new Random();
    int index = r.nextInt(allFlections.size());
    return allFlections.get(index);
  }

  public abstract String flectionToString(Flection f);
}
