package de.malikatalla.ling.gui;

import java.util.ArrayList;
import java.util.List;
import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import android.widget.SearchView;
import android.widget.TextView;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.R;
import de.malikatalla.ling.ling.Dictionary;

/**
 * Shows a list of all verbs. It is loaded in the background while a loading
 * screen is shown.
 */
public class ShowVerbsActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<String>> {

  static final String VERB = "VERB";
  private Dictionary dictionary = Global.getDictionary();
  private ArrayAdapter<String> adapter;
  private VerbListLoader loader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.loading_fragment);
    adapter = new ArrayAdapter<String>(getContext(), R.layout.list_view_item);
    getSupportLoaderManager().initLoader(0, null, this);
    handleIntent(getIntent());
    // onSearchRequested();
  }

  @Override
  protected void onNewIntent(Intent intent) {
//    Log.i(Global.DEBUG, "on new intent:" + intent.getAction());
    setIntent(intent);
    handleIntent(intent);
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.show_verbs, menu);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

      // Associate searchable configuration with the SearchView
      SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
      SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
      searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    }
    return true;
  }

  Context getContext() {
    return this;
  }

  private void handleIntent(Intent intent) {
    Log.i(Global.DEBUG, "handle intent action: " + intent.getAction());
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//      Log.i(Global.DEBUG, "handle intent action: search");
      String query = intent.getStringExtra(SearchManager.QUERY);
      if (loader != null) {
        loader.setSearchQuery(query);
        loader.onStartLoading();
      } else {
        Log.e(Global.DEBUG, "VerbListLoader is null");
      }
    }
  }

  @Override
  public Loader<List<String>> onCreateLoader(int arg0, Bundle arg1) {
    loader = new VerbListLoader(getContext(), dictionary);
    return loader;
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
        // Log.i(Global.DEBUG, "Clicked on " + verb);
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
    private String searchQuery = null;

    public VerbListLoader(Context context, Dictionary dictionary) {
      super(context);
      Log.i(Global.DEBUG, "Create Loader");
      this.dictionary = dictionary;
    }

    public void setSearchQuery(String query) {
      searchQuery = query;
    }

    @Override
    public List<String> loadInBackground() {
      Log.i(Global.DEBUG, "loadInBackground");
      allVerbs = dictionary.getAllVerbs();
      return allVerbs;
    }

    @Override
    protected void onStartLoading() {
//      Log.i(Global.DEBUG, "VerbListLoader.onStartLoading query: " + searchQuery);
//      Log.i(Global.DEBUG, "VerbListLoader.onStartLoading allVerbs!=null:" + (allVerbs!=null));
      if (allVerbs != null) {
        List<String> result = null;
        if (searchQuery == null) {
          result = allVerbs;
        } else {
          result = filter(allVerbs, searchQuery);
        }
//        Log.i(Global.DEBUG, "VerbListLoader.onStartLoading result: " + result);
        deliverResult(result);
      } else {
        forceLoad();
      }
    }

    private List<String> filter(List<String> allVerbs2, String searchQuery2) {
      List<String> result = new ArrayList<String>();
      for (String item : allVerbs2) {
        if (item.startsWith(searchQuery2)) {
          result.add(item);
        }
      }
      return result;
    }
  }
}
