package de.malikatalla.ling.gui;

import java.util.LinkedList;

import de.malikatalla.ling.ConjugationListFragment;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.R;
import de.malikatalla.ling.R.id;
import de.malikatalla.ling.R.layout;
import de.malikatalla.ling.R.menu;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Flection;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;
import de.malikatalla.ling.ling.Number;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;

/** Shows the conjugation of one specific verb */
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
    addFragment(intent, verb, Tense.IMPERFECT, Mode.INDICATIVE, R.id.imperfect_indicative_container, allInflectedForms);
    addFragment(intent, verb, Tense.SIMPLE_PAST, Mode.INDICATIVE, R.id.simple_past_indicative_container, allInflectedForms);
    addFragment(intent, verb, Tense.PAST_PERFECT, Mode.INDICATIVE, R.id.past_perfect_indicative_container, allInflectedForms);
    addFragment(intent, verb, Tense.PLUSCUAM_PERFECT, Mode.INDICATIVE, R.id.pluscuam_perfect_container, allInflectedForms);
    addFragment(intent, verb, Tense.FUTURE_PERFECT, Mode.INDICATIVE, R.id.future_perfect_container, allInflectedForms);
    addFragment(intent, verb, Tense.CONDITIONAL_PERFECT, Mode.INDICATIVE, R.id.conditional_perfect_container, allInflectedForms);
    addFragment(intent, verb, Tense.FUTURE, Mode.INDICATIVE, R.id.future_indicative_container, allInflectedForms);
    addFragment(intent, verb, Tense.CONDITIONAL, Mode.INDICATIVE, R.id.conditional_indicative_container, allInflectedForms);
    addFragment(intent, verb, Tense.PAST_PERFECT, Mode.INDICATIVE, R.id.past_perfect_indicative_container, allInflectedForms);
    addFragment(intent, verb, Tense.PRESENT, Mode.SUBJUNCTIVE, R.id.present_subjunctive_container, allInflectedForms);
    addFragment(intent, verb, Tense.IMPERFECT, Mode.SUBJUNCTIVE, R.id.imperfect_subjunctive_container, allInflectedForms);
    addFragment(intent, verb, Tense.FUTURE, Mode.SUBJUNCTIVE, R.id.future_subjunctive_container, allInflectedForms);
    addFragment(intent, verb, Tense.PAST_PERFECT, Mode.SUBJUNCTIVE, R.id.past_perfect_subjunctive_container, allInflectedForms);
    addFragment(intent, verb, Tense.PLUSCUAM_PERFECT, Mode.SUBJUNCTIVE, R.id.pluscuam_perfect_subjunctive_container, allInflectedForms);
    addFragment(intent, verb, null, Mode.IMPERATIVE, R.id.imperative_container, allInflectedForms);
  }

  private void addFragment(Intent intent, String verb, Tense tense, Mode mode, int fragmentContainer, Dictionary allInflectedForms) {
    ConjugationListFragment fragment = new ConjugationListFragment();
    LinkedList<String> conjugations = new LinkedList<String>();
    LinkedList<String> pronouns = new LinkedList<String>();
    addConjugation(verb, new Flection(tense, Person.FIRST, Number.SINGULAR, null, mode), allInflectedForms, conjugations, pronouns);
    addConjugation(verb, new Flection(tense, Person.SECOND, Number.SINGULAR, null, mode), allInflectedForms, conjugations, pronouns);
    addConjugation(verb, new Flection(tense, Person.THIRD, Number.SINGULAR, null, mode), allInflectedForms, conjugations, pronouns);
    addConjugation(verb, new Flection(tense, Person.FIRST, Number.PLURAL, null, mode), allInflectedForms, conjugations, pronouns);
    addConjugation(verb, new Flection(tense, Person.SECOND, Number.PLURAL, null, mode), allInflectedForms, conjugations, pronouns);
    addConjugation(verb, new Flection(tense, Person.THIRD, Number.PLURAL, null, mode), allInflectedForms, conjugations, pronouns);
    Bundle args = new Bundle();
    args.putSerializable(Tense.class.getName(), tense);
    args.putSerializable(Mode.class.getName(), mode);
    // args.putString(ShowVerbsActivity.VERB, verb);
    args.putSerializable("conjugations", conjugations);
    args.putSerializable("pronouns", pronouns);
    fragment.setArguments(args);
    getSupportFragmentManager().beginTransaction().add(fragmentContainer, fragment).commit();
  }

  private void addConjugation(String verb, Flection f, Dictionary allInflectedForms,
      LinkedList<String> conjugations, LinkedList<String> pronouns) {
    String inflectedForm = allInflectedForms.getInflectedForm(verb, f);
    conjugations.add(inflectedForm != null ? inflectedForm : "?");
    pronouns.add(Global.getDictionary().getPersonalPronoun(f.getTense(), f.getPerson(), f.getNumber(), null, f.getMode()));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.show_conjugation, menu);
    return true;
  }
}
