package de.malikatalla.ling.gui;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.R;
import de.malikatalla.ling.ling.ColumnConverter;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Flection;

public class TestConjugationActivity extends Activity {

	private Dictionary dictionary;
	private EditText e;
	private String currentVerb;
	private Flection currentFlection;
    private TextView verbView;
    private TextView flectionView;
    private TextView personalPronounView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_conjugation);
		dictionary = Global.getDictionary();
		List<String> verbs = dictionary.getAllVerbs();
		e=(EditText)findViewById(R.id.test_answer);
		e.setOnEditorActionListener(new PressEnterListener());
		verbView=(TextView)findViewById(R.id.test_text);
        flectionView=(TextView)findViewById(R.id.test_flection);
        personalPronounView=(TextView)findViewById(R.id.test_personal_pronoun);
		Random r = new Random();
		int randomIndex = r.nextInt(verbs.size());
		currentVerb = verbs.get(randomIndex);
		verbView.setText(currentVerb);
		ColumnConverter columnConverter = Global.getColumnConverter();
		currentFlection = columnConverter.getRandomFlection();
		flectionView.setText(currentFlection.toString());
		String personalPronoun = dictionary.getPersonalPronoun(currentFlection.getTense(), currentFlection.getPerson(), currentFlection.getNumber(), currentFlection.getGender(), currentFlection.getMode());
		personalPronounView.setText(personalPronoun);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_conjugation, menu);
		return true;
	}
	
	class PressEnterListener implements TextView.OnEditorActionListener {

		@Override
		public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
			if (arg2.getKeyCode() == KeyEvent.KEYCODE_ENTER){
				
			}
			return false;
		}
	}
}
