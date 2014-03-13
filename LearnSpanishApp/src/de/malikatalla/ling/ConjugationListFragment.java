package de.malikatalla.ling;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Tense;

public class ConjugationListFragment extends Fragment {

	public ConjugationListFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View inflate = inflater.inflate(R.layout.conjugation_list_fragment, container, false);
		Bundle args = getArguments();
//		String verb = args.getString(ShowVerbsActivity.VERB);
		Tense tense = (Tense)args.getSerializable(Tense.class.getName());
		Mode mode= (Mode)args.getSerializable(Mode.class.getName());

		List<String> conjugations = (List<String>) args.getSerializable("conjugations");
		List<String> pronouns = (List<String>) args.getSerializable("pronouns");
		
		TextView conjugationCombination = (TextView) inflate.findViewById(R.id.conjCombination);
		conjugationCombination.setText(tense + " " + mode);
		LinearLayout conjList = (LinearLayout) inflate.findViewById(R.id.conjugationListView);
		LinearLayout pronounList = (LinearLayout) inflate.findViewById(R.id.pronounListView);
//		conjList.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.conjugation_list_item, conjugations
//				.toArray(new String[0])));
		for(String conjugation: conjugations){
			TextView textView = new TextView(getActivity());
			textView.setText(conjugation);
			conjList.addView(textView);
		}
		for(String pronoun: pronouns){
			TextView textView = new TextView(getActivity());
			textView.setText(pronoun);
			pronounList.addView(textView);
		}
		// Inflate the layout for this fragment
		return inflate;
	}
}
