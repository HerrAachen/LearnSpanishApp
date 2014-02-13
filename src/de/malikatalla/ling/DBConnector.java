package de.malikatalla.ling;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.malikatalla.ling.DBContract.ConjugationTable;
import de.malikatalla.ling.DBContract.VerbTable;
import de.malikatalla.ling.ling.FileHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBConnector extends SQLiteOpenHelper {
  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "Conjugation.db";
  private static final String COMMA = ",";
  private Context context;

  private boolean isDataBaseExist(Context context) {
    File dbFile = context.getDatabasePath(DATABASE_NAME);
    return dbFile.exists();
  }

  public static void main(String[] args) {
  }

  public DBConnector(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
    try {
      if (!isDataBaseExist(context)) {
        // get database, we will override it in next steep
        // but folders containing the database are created
        SQLiteDatabase db = getWritableDatabase();
        db.close();
        // copy database
        copyDataBase();
      }
    } catch (SQLException eSQL) {
      Log.e("TEST", "Cannot open database");
    } catch (IOException IOe) {
      Log.e("TEST", "Can not copy initial database");
    }

  }

  /**
   * Copies the database file from assets to the database directory
   * @throws IOException
   */
  private void copyDataBase() throws IOException {
    // Open your local db as the input stream
    InputStream myInput = context.getAssets().open("databases/" + DATABASE_NAME);
    // Path to the just created empty db
    OutputStream myOutput = new FileOutputStream(context.getDatabasePath(DATABASE_NAME));
    // transfer bytes from the inputfile to the outputfile
    FileHelper.copyFile(myInput, myOutput);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // db.execSQL("CREATE TABLE " + VerbTable.TABLE_NAME + " (" + VerbTable._ID
    // + " INTEGER PRIMARY KEY" + COMMA
    // + VerbTable.COLUMN_INFINITIVE + COMMA + VerbTable.COLUMN_ROOT + COMMA +
    // VerbTable.COLUMN_CONJUGATION + " )");
  }

  // public void initialize() {
  // SQLiteDatabase db = getWritableDatabase();
  //
  // // Create a new map of values, where column names are the keys
  // ContentValues values = new ContentValues();
  // values.put(VerbTable.COLUMN_INFINITIVE, "tener");
  // values.put(VerbTable.COLUMN_ROOT, "ten");
  // values.put(VerbTable.COLUMN_CONJUGATION, "es.v.conj.tener");
  //
  // // Insert the new row, returning the primary key value of the new row
  // long newRowId;
  // newRowId = db.insert(VerbTable.TABLE_NAME, null, values);
  // Log.i("TEST", "created row number " + newRowId);
  //
  // }
  //
  // public void deleteAll(){
  // SQLiteDatabase db = getWritableDatabase();
  // db.execSQL("DROP TABLE IF EXISTS " + VerbTable.TABLE_NAME);
  // db.execSQL("DROP TABLE IF EXISTS " + ConjugationTable.TABLE_NAME);
  // }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  /*
   * public void onOpen (SQLiteDatabase db){
   * 
   * }
   */
}
