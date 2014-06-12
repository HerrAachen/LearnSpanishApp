package de.malikatalla.ling;

import java.io.Serializable;

public class GameStatistics implements Statistics, Serializable {

	private int correct = 0;
	private int wrong = 0;

	public void incrementCorrect(){
	  correct++;
	}
	
	public void incrementWrong(){
	  wrong++;
	}
	
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
