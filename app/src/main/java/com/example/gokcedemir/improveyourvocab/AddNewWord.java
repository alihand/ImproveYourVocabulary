package com.example.gokcedemir.improveyourvocab;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWord extends AppCompatActivity {

    SQLiteDatabase myDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_word);

        myDatabase = openOrCreateDatabase("MyVocabulary",MODE_PRIVATE,null);
        String createQuery = "Create table if not exists MyWords(word text,wordMean text,synonymofWord text,antonymofWord text); ";
        myDatabase.execSQL(createQuery);

        Button saveButton = (Button) findViewById(R.id.btnSave);

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                EditText wordText = (EditText) findViewById(R.id.txtWord);
                EditText meanText = (EditText) findViewById(R.id.txtMean);
                EditText synonymText = (EditText) findViewById(R.id.txtSynonym);
                EditText antonymText = (EditText) findViewById(R.id.txtAntonym);

                String word = wordText.getText().toString();
                String mean = meanText.getText().toString();
                String synonim = synonymText.getText().toString();
                String antonym = antonymText.getText().toString();

                AddWordToDatabase(word,mean,synonim,antonym);
                Toast.makeText(AddNewWord.this, "Success !", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void AddWordToDatabase(String word, String mean, String synonim, String antonym) {

        myDatabase = openOrCreateDatabase("MyVocabulary",MODE_PRIVATE,null);
        String insertQuery = "insert into MyWords (word,wordMean,synonymofWord,antonymofWord) values ('";
        insertQuery += word + "','" + mean + "','" + synonim + "','" + antonym + "')";
        myDatabase.execSQL(insertQuery);
    }
}
