package de.malikatalla.ling.gui;

import de.malikatalla.ling.R;
import de.malikatalla.ling.R.layout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/** Is shown when user has to wait */
public class LoadingScreenFragment extends Fragment {

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      final View view = inflater.inflate(R.layout.loading_fragment, container, false);
      return view;
   }
}
