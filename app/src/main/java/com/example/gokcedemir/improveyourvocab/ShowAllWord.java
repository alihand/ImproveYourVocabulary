package com.example.gokcedemir.improveyourvocab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class ShowAllWord extends AppCompatActivity {

    SqlLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SqlLiteListAdapter ListAdapter ;

    ArrayList<String> word_ArrayList = new ArrayList<String>();
    ArrayList<String> mean_ArrayList = new ArrayList<String>();
    ArrayList<String> synonim_ArrayList = new ArrayList<String>();
    ArrayList<String> antonym_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_word);

        LISTVIEW = (ListView) findViewById(R.id.lwWordList);
        SQLITEHELPER = new SqlLiteHelper(this);

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM MyWords", null);

        word_ArrayList.clear();
        mean_ArrayList.clear();
        synonim_ArrayList.clear();
        antonym_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                word_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_WORD)));
                mean_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_MEAN)));
                synonim_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_SYNONIM)));
                antonym_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_ANTONYM)));
            } while (cursor.moveToNext());
        }

        ListAdapter = new SqlLiteListAdapter(ShowAllWord.this,

                word_ArrayList,
                mean_ArrayList,
                synonim_ArrayList,
                antonym_ArrayList

        );

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }
}
