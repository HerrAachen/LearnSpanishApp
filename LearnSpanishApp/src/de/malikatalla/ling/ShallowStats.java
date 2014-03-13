package de.malikatalla.ling;

public class ShallowStats implements Statistics {

	private int correct = 0;
	private int wrong = 0;
	
	@Override
	public float getPercentageCorrect() {
		return ((float)correct)/((float)(correct+wrong));
	}

	@Override
	public int getAbsoluteTotal() {
		return correct + wrong;
	}

	@Override
	public int getAbsoluteCorrect() {
		return correct;
	}

	@Override
	public int getAbsoluteWrong() {
		return wrong;
	}
}
