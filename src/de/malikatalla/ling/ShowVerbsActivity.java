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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.malikatalla.ling.ling.Dictionary;

public class ShowVerbsActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<List<String>> {

   private Dictionary           dictionary = Global.getDictionary();
   private ArrayAdapter<String> adapter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_show_verbs);
      adapter = new VerbListAdapter(getContext());
      ListView v = (ListView) findViewById(R.id.verbListView);
      v.setAdapter(adapter);
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

   private class VerbListAdapter extends ArrayAdapter<String> {

      public VerbListAdapter(Context context) {
         super(context, 0);
      }

      @Override
      public View getView(int pos, View convertView, ViewGroup arg2) {
         TextView view;
         if (convertView == null || convertView instanceof TextView) {
            view = new TextView(getContext());
         } else {
            view = (TextView) convertView;
         }
         String item = getItem(pos);
         view.setText(item);
         return view;
      }
   }

   // private class ListDbAdapter implements ListAdapter {
   //
   // List<String> verbs;
   //
   // public ListDbAdapter(Dictionary d) {
   // verbs = d.getAllVerbs();
   // }
   //
   // @Override
   // public int getCount() {
   // return verbs.size();
   // }
   //
   // @Override
   // public Object getItem(int arg0) {
   // return verbs.get(arg0);
   // }
   //
   // @Override
   // public long getItemId(int arg0) {
   // // TODO Auto-generated method stub
   // return 0;
   // }
   //
   // @Override
   // public int getItemViewType(int arg0) {
   // return 0;
   // }
   //
   // @Override
   // public View getView(int pos, View convertView, ViewGroup arg2) {
   // String verb = verbs.get(pos);
   // if (convertView != null && convertView instanceof TextView) {
   // ((TextView) convertView).setText(verb);
   // return convertView;
   // } else {
   // TextView v = new TextView(getContext());
   // v.setText(verb);
   // return v;
   // }
   // }
   //
   // @Override
   // public int getViewTypeCount() {
   // return 1;
   // }
   //
   // @Override
   // public boolean hasStableIds() {
   // // TODO Auto-generated method stub
   // return true;
   // }
   //
   // @Override
   // public boolean isEmpty() {
   // return verbs == null || verbs.size() == 0;
   // }
   //
   // @Override
   // public void registerDataSetObserver(DataSetObserver arg0) {
   //
   // }
   //
   // @Override
   // public void unregisterDataSetObserver(DataSetObserver arg0) {
   //
   // }
   //
   // @Override
   // public boolean areAllItemsEnabled() {
   // return true;
   // }
   //
   // @Override
   // public boolean isEnabled(int arg0) {
   // return true;
   // }
   // }

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
