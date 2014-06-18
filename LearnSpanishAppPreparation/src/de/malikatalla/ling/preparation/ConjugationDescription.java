package de.malikatalla.ling.preparation;

import de.malikatalla.ling.ling.Flections;

public class ConjugationDescription {

  private String basicConjugation;
  private String root;
  private Flections irregularFlections = new Flections();
  /** A connection element possibly needed to create the regular conjugation */
  private String nexo;

  /**
   * Those flections that are not derived from the basic conjugations. Is never
   * null
   */
  public Flections getIrregularFlections() {
    return irregularFlections;
  }

  /** Root and basic conjugation define the regular flections */
  public String getBasicConjugation() {
    return basicConjugation;
  }

  public void setBasicConjugation(String basicConjugation) {
    this.basicConjugation = basicConjugation;
  }

  @Override
  public String toString() {
    return basicConjugation + ", " + root + ", " + irregularFlections;
  }

  /** Root and basic conjugation define the regular flections */
  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }
  
  public void setNexo(String nexo){
    this.nexo = nexo;
  }
}
