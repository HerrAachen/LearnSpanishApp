package de.malikatalla.ling.gui;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.R;
import de.malikatalla.ling.R.id;
import de.malikatalla.ling.R.layout;
import de.malikatalla.ling.R.menu;
import de.malikatalla.ling.ling.Dictionary;

/**
 * Shows a list of all verbs. It is loaded in the background while a loading
 * screen is shown.
 */
public class ShowVerbsActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<String>> {

  static final String VERB = "VERB";
  private Dictionary dictionary = Global.getDictionary();
  private ArrayAdapter<String> adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.loading_fragment);
    adapter = new ArrayAdapter<String>(getContext(), R.layout.list_view_item);
    getSupportLoaderManager().initLoader(0, null, this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.show_verbs, menu);
    return true;
  }

  Context getContext() {
    return this;
  }

  @Override
  public Loader<List<String>> onCreateLoader(int arg0, Bundle arg1) {
    Log.i(Global.DEBUG, "onCreateLoader");
    return new VerbListLoader(getContext(), dictionary);
  }

  @Override
  public void onLoadFinished(Loader<List<String>> arg0, List<String> arg1) {
    Log.i(Global.DEBUG, "onLoadFinished");
    adapter.clear();
    for (String s : arg1) {
      adapter.add(s);
    }
    setContentView(R.layout.activity_show_verbs);
    ListView v = (ListView) findViewById(R.id.verbListView);
    v.setAdapter(adapter);
    v.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        String verb = (String) ((TextView) arg1).getText();
        Log.i(Global.DEBUG, "Clicked on " + verb);
        Intent intent = new Intent(getContext(), ShowConjugationActivity.class);
        intent.putExtra(VERB, verb);
        startActivity(intent);
      }
    });
  }

  @Override
  public void onLoaderReset(Loader<List<String>> arg0) {
    adapter.clear();
  }

  private static class VerbListLoader extends AsyncTaskLoader<List<String>> {

    private Dictionary dictionary;
    private List<String> allVerbs = null;

    public VerbListLoader(Context context, Dictionary dictionary) {
      super(context);
      this.dictionary = dictionary;
    }

    @Override
    public List<String> loadInBackground() {
      Log.i(Global.DEBUG, "loadInBackground");
      List<String> allVerbs = dictionary.getAllVerbs();
      return allVerbs;
    }

    @Override
    protected void onStartLoading() {
      if (allVerbs != null) {
        deliverResult(allVerbs);
      } else {
        forceLoad();
      }
    }
  }
}
