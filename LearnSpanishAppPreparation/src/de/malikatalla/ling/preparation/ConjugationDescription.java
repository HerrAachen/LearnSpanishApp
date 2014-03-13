package de.malikatalla.ling.preparation;

public class ConjugationDescription {

  private String basicConjugation;
  private String root;

  public String getBasicConjugation() {
    return basicConjugation;
  }

  public void setBasicConjugation(String basicConjugation) {
    this.basicConjugation = basicConjugation;
  }

  @Override
  public String toString() {
    return "ConjugationDescription [basicConjugation=" + basicConjugation + "]";
  }

  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }
}
