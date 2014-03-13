package de.malikatalla.ling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Tense;

public class TenseChooserActivity extends Activity {

   private String verb;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_tense_chooser);

      Intent intent = getIntent();
      verb = intent.getStringExtra(ShowVerbsActivity.VERB);
      TextView t = (TextView) findViewById(R.id.tenseChooserVerb);
      t.setText(verb);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.tense_chooser, menu);
      return true;
   }

   public void callShowPresentIndicative(View view) {
      Intent intent = new Intent(this, ShowConjugationActivity.class);
      intent.putExtra(ShowVerbsActivity.VERB, verb);
      intent.putExtra(Tense.class.getName(), Tense.PRESENT);
      intent.putExtra(Mode.class.getName(), Mode.INDICATIVE);
      startActivity(intent);
   }
}
