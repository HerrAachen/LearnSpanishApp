package de.malikatalla.ling;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import de.malikatalla.ling.ling.Dictionary;

public class ShowVerbsActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<String>> {

   private Dictionary           dictionary = Global.getDictionary();
   private ArrayAdapter<String> adapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.loading_fragment);
      adapter = new ArrayAdapter<String>(getContext(), R.layout.list_view_item);
      // ListView v = (ListView) findViewById(R.id.verbListView);
      // v.setAdapter(adapter);
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
      Log.i("Test", "onCreateLoader");
      return new VerbListLoader(getContext(), dictionary);
   }

   @Override
   public void onLoadFinished(Loader<List<String>> arg0, List<String> arg1) {
      Log.i("Test", "onLoadFinished");
      adapter.clear();
      for (String s : arg1) {
         adapter.add(s);
      }
      setContentView(R.layout.activity_show_verbs);
      ListView v = (ListView) findViewById(R.id.verbListView);
      v.setAdapter(adapter);
   }

   @Override
   public void onLoaderReset(Loader<List<String>> arg0) {
      adapter.clear();
   }

   private static class VerbListLoader extends AsyncTaskLoader<List<String>> {

      private Dictionary   dictionary;
      private List<String> allVerbs = null;

      public VerbListLoader(Context context, Dictionary dictionary) {
         super(context);
         this.dictionary = dictionary;
      }

      @Override
      public List<String> loadInBackground() {
         Log.i("Test", "loadInBackground");
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
