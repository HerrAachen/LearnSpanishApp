package de.malikatalla.ling.gui;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.R;
import de.malikatalla.ling.ling.ColumnConverter;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Flection;

public class TestConjugationActivity extends Activity {

  private Dictionary dictionary;
  private EditText e;
  private String currentVerb;
  private Flection currentFlection;
  private TextView verbView;
  private TextView flectionView;
  private TextView personalPronounView;
  private TextView resultView;
  private TextView questionNumberView;
  private String inflectedForm;
  private int questionCount = 0;
  private static final int MAX_QUESTIONS = 10;
  private Random randomVerbGenerator;
  private List<String> verbs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    questionCount = 0;
    setContentView(R.layout.activity_test_conjugation);
    dictionary = Global.getDictionary();
    verbs = dictionary.getAllVerbs();
    e = (EditText) findViewById(R.id.test_answer);
    e.setOnEditorActionListener(new PressEnterListener());
    verbView = (TextView) findViewById(R.id.test_text);
    flectionView = (TextView) findViewById(R.id.test_flection);
    personalPronounView = (TextView) findViewById(R.id.test_personal_pronoun);
    resultView = (TextView) findViewById(R.id.test_result);
    randomVerbGenerator = new Random();
    questionNumberView = (TextView) findViewById(R.id.test_question_number);

    nextQuestionToGUI();
  }

  private void nextQuestionToGUI() {
    resultView.setText("");
    e.setText("");
    questionNumberView.setText(++questionCount + "/" + MAX_QUESTIONS);
    int randomIndex = randomVerbGenerator.nextInt(verbs.size());
    currentVerb = verbs.get(randomIndex);
    verbView.setText(currentVerb);
    ColumnConverter columnConverter = Global.getColumnConverter();
    currentFlection = columnConverter.getRandomFlection();
    flectionView.setText(currentFlection.getMode() + ", " + currentFlection.getTense());
    String personalPronoun = dictionary.getPersonalPronoun(currentFlection.getTense(), currentFlection.getPerson(),
        currentFlection.getNumber(), currentFlection.getGender(), currentFlection.getMode());
    personalPronounView.setText(personalPronoun);
    inflectedForm = dictionary.getInflectedForm(currentVerb, currentFlection.getTense(), currentFlection.getPerson(),
        currentFlection.getNumber(), currentFlection.getGender(), currentFlection.getMode());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.test_conjugation, menu);
    return true;
  }

  private void verifyAnswer() {
    String answer = e.getText().toString();
    if (answer.equals(inflectedForm)) {
      resultView.setText("Correct");
      resultView.setBackgroundColor(Color.GREEN);
    } else {
      resultView.setText("Wrong");
      resultView.setBackgroundColor(Color.RED);
    }
    Handler handler = new Handler(); 
    handler.postDelayed(new Runnable() { 
         public void run() { 
              nextQuestionToGUI();
         } 
    }, 1000); 
  }

  class PressEnterListener implements TextView.OnEditorActionListener {

    @Override
    public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
      if (arg2.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
        verifyAnswer();
      }
      return false;
    }
  }
}
