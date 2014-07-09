package de.malikatalla.ling.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.malikatalla.ling.R;
import de.malikatalla.ling.R.id;
import de.malikatalla.ling.R.layout;
import de.malikatalla.ling.R.menu;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Tense;

public class TenseChooserActivity extends Activity {

   private String verb;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_tense_chooser);

//      Intent intent = getIntent();
//      verb = intent.getStringExtra(ShowVerbsActivity.VERB);
//      TextView t = (TextView) findViewById(R.id.tenseChooserVerb);
//      t.setText(verb);
      LinearLayout lay = (LinearLayout) findViewById(R.id.tenseButtonsLayout);
      for (int i = 1; i < 3; i++) {
        Button btnTag = new Button(this);
        btnTag.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        btnTag.setText("Button " + i);
        btnTag.setId(i);
        lay.addView(btnTag);
//        ((Button) findViewById(i)).setOnClickListener(this);
    }
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
