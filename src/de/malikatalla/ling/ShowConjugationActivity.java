package de.malikatalla.ling;

import java.util.LinkedList;

import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;
import de.malikatalla.ling.ling.Number;
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

		Dictionary allInflectedForms = Global.getDictionary().getAllInflectedForms(verb);
		addFragment(intent, verb, Tense.PRESENT, Mode.INDICATIVE, R.id.present_indicative_container, allInflectedForms);
		addFragment(intent, verb, Tense.IMPERFECT, Mode.INDICATIVE, R.id.imperfect_indicative_container,
				allInflectedForms);
		addFragment(intent, verb, Tense.SIMPLE_PAST, Mode.INDICATIVE, R.id.simple_past_indicative_container,
				allInflectedForms);
		addFragment(intent, verb, Tense.PAST_PERFECT, Mode.INDICATIVE, R.id.past_perfect_indicative_container,
				allInflectedForms);
	}

	private void addFragment(Intent intent, String verb, Tense tense, Mode mode, int fragmentContainer,
			Dictionary allInflectedForms) {
		ConjugationListFragment fragment = new ConjugationListFragment();
		LinkedList<String> conjugations = new LinkedList<String>();
		addConjugation(verb, tense, Person.FIRST, Number.SINGULAR, mode, allInflectedForms, conjugations);
		addConjugation(verb, tense, Person.SECOND, Number.SINGULAR, mode, allInflectedForms, conjugations);
		addConjugation(verb, tense, Person.THIRD, Number.SINGULAR, mode, allInflectedForms, conjugations);
		addConjugation(verb, tense, Person.FIRST, Number.PLURAL, mode, allInflectedForms, conjugations);
		addConjugation(verb, tense, Person.SECOND, Number.PLURAL, mode, allInflectedForms, conjugations);
		addConjugation(verb, tense, Person.THIRD, Number.PLURAL, mode, allInflectedForms, conjugations);
		Bundle args = new Bundle();
		args.putSerializable(Tense.class.getName(), tense);
		args.putSerializable(Mode.class.getName(), mode);
		// args.putString(ShowVerbsActivity.VERB, verb);
		args.putSerializable("conjugations", conjugations);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction().add(fragmentContainer, fragment).commit();
	}

	private void addConjugation(String verb, Tense tense, Person p, Number n, Mode mode, Dictionary allInflectedForms,
			LinkedList<String> conjugations) {
		String inflectedForm = allInflectedForms.getInflectedForm(verb, tense, p, n, null, mode);
		conjugations.add(inflectedForm != null ? inflectedForm : "?");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_conjugation, menu);
		return true;
	}
}
