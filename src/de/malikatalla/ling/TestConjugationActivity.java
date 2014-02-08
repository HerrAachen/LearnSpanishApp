package de.malikatalla.ling;

import java.util.List;

import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Flection;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class TestConjugationActivity extends Activity {

	private Dictionary dictionary;
	private EditText e;
	private String currentVerb;
	private Flection currentFlection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_conjugation);
		dictionary = Global.getDictionary();
		List<String> verbs = dictionary.getAllVerbs();
		e=(EditText)findViewById(R.id.test_answer);
		e.setOnEditorActionListener(new PressEnterListener());
		TextView t=(TextView)findViewById(R.id.test_text);
		currentVerb = verbs.get(0);
		t.setText(currentVerb);
		
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
