package de.malikatalla.ling.ling;

import de.malikatalla.ling.Global;

public class Flection {

  private Tense tense;
  private Person person;
  private Number number;
  private Gender gender;
  private Mode mode;

  public Flection(Tense t, Person p, Number n, Gender g, Mode m) {
    this.tense = t;
    this.person = p;
    this.number = n;
    this.gender = g;
    this.mode = m;
  }

  public Tense getTense() {
    return tense;
  }

  public Mode getMode() {
    return mode;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public void setTense(Tense tense) {
    this.tense = tense;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Number getNumber() {
    return number;
  }

  public void setNumber(Number number) {
    this.number = number;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((mode == null) ? 0 : mode.hashCode());
    result = prime * result + ((number == null) ? 0 : number.hashCode());
    result = prime * result + ((person == null) ? 0 : person.hashCode());
    result = prime * result + ((tense == null) ? 0 : tense.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Flection other = (Flection) obj;
    if (gender != other.gender)
      return false;
    if (mode != other.mode)
      return false;
    if (number != other.number)
      return false;
    if (person != other.person)
      return false;
    if (tense != other.tense)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return Global.getColumnConverter().flectionToString(this);
  }
}
