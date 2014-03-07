package de.malikatalla.ling;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Mode;
import de.malikatalla.ling.ling.Number;
import de.malikatalla.ling.ling.Person;
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

//		Dictionary dictionary = Global.getDictionary();
//		List<String> conjugations = new LinkedList<String>();
//		conjugations.add(dictionary.getInflectedForm(verb, tense, Person.FIRST, Number.SINGULAR, null, mode));
//		conjugations.add(dictionary.getInflectedForm(verb, tense, Person.SECOND, Number.SINGULAR, null, mode));
//		conjugations.add(dictionary.getInflectedForm(verb, tense, Person.THIRD, Number.SINGULAR, null, mode));
//		conjugations.add(dictionary.getInflectedForm(verb, tense, Person.FIRST, Number.PLURAL, null, mode));
//		conjugations.add(dictionary.getInflectedForm(verb, tense, Person.SECOND, Number.PLURAL, null, mode));
//		conjugations.add(dictionary.getInflectedForm(verb, tense, Person.THIRD, Number.PLURAL, null, mode));
		List<String> conjugations = (List<String>) args.getSerializable("conjugations");
		
		TextView conjugationCombination = (TextView) inflate.findViewById(R.id.conjCombination);
		conjugationCombination.setText(tense + " " + mode);
		ListView conjList = (ListView) inflate.findViewById(R.id.conjugationListView);
		conjList.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.conjugation_list_item, conjugations
				.toArray(new String[0])));
		// Inflate the layout for this fragment
		return inflate;
	}

}
