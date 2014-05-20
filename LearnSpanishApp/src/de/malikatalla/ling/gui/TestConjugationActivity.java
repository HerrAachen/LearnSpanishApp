package de.malikatalla.ling.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import de.malikatalla.ling.Global;
import de.malikatalla.ling.R;
import de.malikatalla.ling.ling.ColumnConverter;
import de.malikatalla.ling.ling.Dictionary;
import de.malikatalla.ling.ling.Flection;

public class TestConjugationActivity extends Activity {

  private Dictionary dictionary;
  private String currentVerb;
  private Flection currentFlection;
  private TextView verbView;
  private TextView flectionView;
  private TextView personalPronounView;
  private TextView questionNumberView;
  private String inflectedForm;
  private int questionCount = 0;
  private static final int MAX_QUESTIONS = 3;
  private Random randomNumberGenerator;
  private List<String> verbs;
  private List<Button> buttons;
  private Button chosenButton;
  private Button correctButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    chosenButton = null;
    questionCount = 0;
    setContentView(R.layout.activity_test_conjugation);
    dictionary = Global.getDictionary();
    verbs = dictionary.getAllVerbs();
    buttons = new LinkedList<Button>();
    buttons.add((Button) findViewById(R.id.test_button1));
    buttons.add((Button) findViewById(R.id.test_button2));
    buttons.add((Button) findViewById(R.id.test_button3));
    buttons.add((Button) findViewById(R.id.test_button4));
    ButtonClickListener buttonClickListener = new ButtonClickListener();
    for (Button b : buttons) {
      b.setOnClickListener(buttonClickListener);
    }
    verbView = (TextView) findViewById(R.id.test_text);
    flectionView = (TextView) findViewById(R.id.test_flection);
    personalPronounView = (TextView) findViewById(R.id.test_personal_pronoun);
    randomNumberGenerator = new Random();
    questionNumberView = (TextView) findViewById(R.id.test_question_number);
    nextQuestionToGUI();
  }

  private void nextQuestionToGUI() {
    if (questionCount>=MAX_QUESTIONS){
      Intent intent = new Intent(this, FinishedActivity.class);
      intent.putExtra("Bla", "Go Ahead");
      startActivity(intent);
    }
    correctButton = null;
    chosenButton = null;
    for (Button b : buttons) {
      b.setBackgroundResource(android.R.drawable.btn_default);
    }
    questionNumberView.setText(++questionCount + "/" + MAX_QUESTIONS);
    int randomIndex = randomNumberGenerator.nextInt(verbs.size());
    currentVerb = verbs.get(randomIndex);
    verbView.setText(currentVerb);
    ColumnConverter columnConverter = Global.getColumnConverter();
    currentFlection = columnConverter.getRandomFlection();
    flectionView.setText(currentFlection.getMode() + ", " + currentFlection.getTense());
    String personalPronoun = dictionary.getPersonalPronoun(currentFlection.getTense(), currentFlection.getPerson(),
        currentFlection.getNumber(), currentFlection.getGender(), currentFlection.getMode());
    personalPronounView.setText(personalPronoun);
    inflectedForm = dictionary.getInflectedForm(currentVerb, currentFlection);
    setAnswersToButtons();
  }

  private void setAnswersToButtons() {
    int buttonIndex = randomNumberGenerator.nextInt(buttons.size());
    int i = 0;
    for (Button b : buttons) {
      b.getBackground().setColorFilter(Color.WHITE, Mode.MULTIPLY);
      if (i == buttonIndex) {
        b.setText(inflectedForm);
        correctButton = b;
      } else {
        String inflected = null;
        do {
          Flection randomFlection = Global.getColumnConverter().getRandomFlection();
          inflected = dictionary.getInflectedForm(currentVerb, randomFlection);
        } while (inflectedForm != null && inflectedForm.equals(inflected));
        b.setText(inflected);
      }
      i++;
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.test_conjugation, menu);
    return true;
  }

  private void verifyAnswer() {
    int delay = 1000;
    String answer = chosenButton.getText().toString();
    if (answer.equals(inflectedForm)) {
      // chosenButton.setBackgroundColor(Color.GREEN);
      chosenButton.getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
      delay = 300;
    } else {
      chosenButton.getBackground().setColorFilter(Color.RED, Mode.MULTIPLY);
      correctButton.getBackground().setColorFilter(Color.GREEN, Mode.MULTIPLY);
    }
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      public void run() {
        nextQuestionToGUI();
      }
    }, delay);
  }

  private class ButtonClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
      chosenButton = (Button) v;
      verifyAnswer();
    }
  }

  // class PressEnterListener implements TextView.OnEditorActionListener {
  //
  // @Override
  // public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
  // if (arg2.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
  // verifyAnswer();
  // }
  // return false;
  // }
  // }
}
