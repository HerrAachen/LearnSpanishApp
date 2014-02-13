package de.malikatalla.ling.ling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.malikatalla.ling.DBConnector;

public class DbDictionary implements Dictionary {

  private DBConnector con; 
  
  public DbDictionary (Context context){
    con = new DBConnector(context);
  }
  
  @Override
  public void loadDictionary() {

  }

  @Override
  public String getInflectedForm(String infinitive, Tense t, Person p, Number n, Gender g) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getPersonalPronoun(Tense t, Person p, Number n, Gender g) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getAllVerbs() {
    Log.i("TEST", "getAllVerbs");
    SQLiteDatabase db = con.getReadableDatabase();
    Cursor res = db.query("VERBS", new String[] {"VERB"}, null, null, null, null, null);
    List<String> verbs = new ArrayList<String>();
    while(res.moveToNext()){
      String verb = res.getString(0);
      verbs.add(verb);
    } 
    return verbs;
  }

}
