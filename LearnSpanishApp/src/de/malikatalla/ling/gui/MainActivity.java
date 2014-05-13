package de.malikatalla.ling.gui;

import de.malikatalla.ling.Global;
import de.malikatalla.ling.R;
import de.malikatalla.ling.R.layout;
import de.malikatalla.ling.R.menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/** The initial screen of the app */
public class MainActivity extends Activity {

   private static final String MESSAGE = "de.malikatalla.MESSAGE";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Global.init(this);
   }

//   @Override
//   public boolean onCreateOptionsMenu(Menu menu) {
//      // Inflate the menu; this adds items to the action bar if it is present.
//      getMenuInflater().inflate(R.menu.main, menu);
//      return true;
//   }

   public void callTestConjugation(View view) {
      Intent intent = new Intent(this, TestConjugationActivity.class);
      intent.putExtra(MESSAGE, "Go Ahead");
      startActivity(intent);

   }

   public void callShowConjugation(View view) {
      Intent intent = new Intent(this, ShowVerbsActivity.class);
      startActivity(intent);
   }
}
