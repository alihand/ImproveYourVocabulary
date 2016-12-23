package com.example.gokcedemir.improveyourvocab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ShowAllWord extends AppCompatActivity {

    SqlLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SqlLiteListAdapter ListAdapter;

    ArrayList<String> word_ArrayList = new ArrayList<String>();
    ArrayList<String> mean_ArrayList = new ArrayList<String>();
    ArrayList<String> synonym_ArrayList = new ArrayList<String>();
    ArrayList<String> antonym_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    private static final int ID_SIL = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_word);

        LISTVIEW = (ListView) findViewById(R.id.lwWordList);
        SQLITEHELPER = new SqlLiteHelper(this);
        registerForContextMenu(LISTVIEW);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View vi, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,vi,menuInfo);

        menu.add(Menu.NONE,ID_SIL,0,"Sil");
    }

    public boolean onContextItemSelected(MenuItem item) {

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;

        switch (item.getItemId())
        {
            case ID_SIL:
                deleteWord(word_ArrayList.get(listPosition).toString());
                return true;
        }

        return false;
    }

    public void deleteWord(String word) {

        SQLiteDatabase db = SQLITEHELPER.getWritableDatabase();
        db.delete("MyWords", "word" +"='"+word+"'",null);
        db.close();
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
        synonym_ArrayList.clear();
        antonym_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                word_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_WORD)));
                mean_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_MEAN)));
                synonym_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_SYNONYM)));
                antonym_ArrayList.add(cursor.getString(cursor.getColumnIndex(SqlLiteHelper.KEY_ANTONYM)));
            } while (cursor.moveToNext());
        }

        ListAdapter = new SqlLiteListAdapter(ShowAllWord.this,

                word_ArrayList,
                mean_ArrayList,
                synonym_ArrayList,
                antonym_ArrayList

        );

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }
}
