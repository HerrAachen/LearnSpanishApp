package de.malikatalla.ling;

import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.DummyDictionary;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    private static final String MESSAGE = "de.malikatalla.MESSAGE";
    
    private Dictionary dictionary;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dictionary = new DummyDictionary();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	public void callTestConjugation(View view) {
	    Intent intent = new Intent(this, TestConjugationActivity.class);
	    intent.putExtra(MESSAGE, "Go Ahead");
	    startActivity(intent);

	}
}
