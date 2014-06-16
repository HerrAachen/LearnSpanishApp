package de.malikatalla.ling.preparation;

import java.util.Map;

public interface ConjugationPatch {

	public void applyPatch(Map<String, ConjugationDescription> conjugations);
}
