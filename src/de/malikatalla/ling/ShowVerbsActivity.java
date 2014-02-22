package de.malikatalla.ling;

import java.util.List;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.malikatalla.ling.ling.Dictionary;

public class ShowVerbsActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_show_verbs);
      Dictionary dictionary = Global.getDictionary();
      ListDbAdapter adapter = new ListDbAdapter(dictionary);
      ListView v = (ListView) findViewById(R.id.verbListView);
      v.setAdapter(adapter);
      getLoaderManager().initLoader(0, null, this);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.show_verbs, menu);
      return true;
   }

   private Context getContext() {
      return this;
   }

   private class ListDbAdapter implements ListAdapter {

      List<String> verbs;

      public ListDbAdapter(Dictionary d) {
         verbs = d.getAllVerbs();
      }

      @Override
      public int getCount() {
         return verbs.size();
      }

      @Override
      public Object getItem(int arg0) {
         return verbs.get(arg0);
      }

      @Override
      public long getItemId(int arg0) {
         // TODO Auto-generated method stub
         return 0;
      }

      @Override
      public int getItemViewType(int arg0) {
         return 0;
      }

      @Override
      public View getView(int pos, View convertView, ViewGroup arg2) {
         String verb = verbs.get(pos);
         if (convertView != null && convertView instanceof TextView) {
            ((TextView) convertView).setText(verb);
            return convertView;
         } else {
            TextView v = new TextView(getContext());
            v.setText(verb);
            return v;
         }
      }

      @Override
      public int getViewTypeCount() {
         return 1;
      }

      @Override
      public boolean hasStableIds() {
         // TODO Auto-generated method stub
         return true;
      }

      @Override
      public boolean isEmpty() {
         return verbs == null || verbs.size() == 0;
      }

      @Override
      public void registerDataSetObserver(DataSetObserver arg0) {

      }

      @Override
      public void unregisterDataSetObserver(DataSetObserver arg0) {

      }

      @Override
      public boolean areAllItemsEnabled() {
         return true;
      }

      @Override
      public boolean isEnabled(int arg0) {
         return true;
      }
   }
}
