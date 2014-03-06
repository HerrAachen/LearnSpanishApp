package de.malikatalla.ling;

import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Tense;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;

public class ShowConjugationActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_conjugation);
		Intent intent = getIntent();
		String verb = intent.getStringExtra(ShowVerbsActivity.VERB);
		TextView t = (TextView) findViewById(R.id.verbTitle);
		t.setText(verb);

		addFragment(intent, verb, Tense.PRESENT, Mode.INDICATIVE, R.id.present_indicative_container);
		addFragment(intent, verb, Tense.IMPERFECT, Mode.INDICATIVE, R.id.imperfect_indicative_container);
	}

	private void addFragment(Intent intent, String verb, Tense tense, Mode mode, int fragmentContainer) {
		ConjugationListFragment fragment = new ConjugationListFragment();
		Bundle args = new Bundle();
		args.putSerializable(Tense.class.getName(), tense);
		args.putSerializable(Mode.class.getName(), mode);
		args.putString(ShowVerbsActivity.VERB, verb);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction().add(fragmentContainer, fragment).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_conjugation, menu);
		return true;
	}
}
