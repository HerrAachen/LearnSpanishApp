package de.malikatalla.ling;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Number;
import de.malikatalla.ling.ling.Person;
import de.malikatalla.ling.ling.Tense;

public class ShowConjugationActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_show_conjugation);
      Intent intent = getIntent();
      String verb = intent.getStringExtra(ShowVerbsActivity.VERB);
      Bundle extras = intent.getExtras();
      Tense tense = (Tense) extras.get(Tense.class.getName());
      Mode mode = (Mode) extras.get(Mode.class.getName());
      TextView t = (TextView) findViewById(R.id.verbTitle);
      t.setText(verb);
      TextView conjugationCombination = (TextView) findViewById(R.id.conjCombination);
      conjugationCombination.setText(tense + " " + mode);
      ListView conjList = (ListView) findViewById(R.id.conjugationListView);

      Dictionary dictionary = Global.getDictionary();
      List<String> conjugations = new LinkedList<String>();
      conjugations.add(dictionary.getInflectedForm(verb, tense, Person.FIRST, Number.SINGULAR, null, mode));
      conjugations.add(dictionary.getInflectedForm(verb, tense, Person.SECOND, Number.SINGULAR, null, mode));
      conjugations.add(dictionary.getInflectedForm(verb, tense, Person.THIRD, Number.SINGULAR, null, mode));
      conjugations.add(dictionary.getInflectedForm(verb, tense, Person.FIRST, Number.PLURAL, null, mode));
      conjugations.add(dictionary.getInflectedForm(verb, tense, Person.SECOND, Number.PLURAL, null, mode));
      conjugations.add(dictionary.getInflectedForm(verb, tense, Person.THIRD, Number.PLURAL, null, mode));
      conjList.setAdapter(new ArrayAdapter<String>(this, R.layout.conjugation_list_item, conjugations
            .toArray(new String[0])));

   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.show_conjugation, menu);
      return true;
   }

}
