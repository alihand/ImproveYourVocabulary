package com.example.gokcedemir.improveyourvocab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class Test extends AppCompatActivity {

    SQLiteDatabase myDatabase = null;
    ArrayList<Words> myWordList = new ArrayList<>();
    ArrayList<Words> askingWordInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        TextView question = (TextView) findViewById(R.id.lblQuestion);
        question.setText(SetQuestions());

        Button controlButton = (Button) findViewById(R.id.btnControl);

        controlButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                TextView question = (TextView) findViewById(R.id.lblQuestion);
                String questionNow = question.getText().toString();

                EditText txtAnswer = (EditText) findViewById(R.id.txtAnswer);
                String answer = txtAnswer.getText().toString();

                if(ControlAnswer(answer, questionNow)) {
                    Toast.makeText(Test.this, "That's TRUE !", Toast.LENGTH_SHORT).show();
                    question.setText(SetQuestions());
                    txtAnswer.clearFocus();
                }
                else
                {
                    Toast.makeText(Test.this, "That's FALSE !", Toast.LENGTH_SHORT).show();
                    question.setText(SetQuestions());
                    txtAnswer.clearFocus();
                }
            }
        });
    }

    public ArrayList<Words> GetWordListFromDatabase(){

        ArrayList<Words> myWords = new ArrayList<>();

        myDatabase = openOrCreateDatabase("MyVocabulary",MODE_PRIVATE,null);
        String selectQuery = "select * from MyWords";

        Cursor myCursor = myDatabase.rawQuery(selectQuery,null);
        myCursor.moveToFirst();

        String dbResult = null;
        while (!myCursor.isAfterLast()){

            if(myCursor.getString(myCursor.getColumnIndex("word"))!= null)
            {
                dbResult+= myCursor.getString(myCursor.getColumnIndex("word"));
                dbResult+="\n";

                String word = myCursor.getString(myCursor.getColumnIndex("word"));
                String mean = myCursor.getString(myCursor.getColumnIndex("wordMean"));
                String synonim = myCursor.getString(myCursor.getColumnIndex("synonimofWord"));
                String antonym = myCursor.getString(myCursor.getColumnIndex("antonymofWord"));

                Words newWord = new Words();
                newWord.setWord(word);
                newWord.setMean(mean);
                newWord.setSynonim(synonim);
                newWord.setAntonym(antonym);

                myWords.add(newWord);
            }
            myCursor.moveToNext();
        }
        myDatabase.close();
        return myWords;
    }

    public ArrayList<Words> GetSpecificAskingWord(){

        ArrayList<Words> myAskingWord = new ArrayList<>();
        myWordList = GetWordListFromDatabase();

        Random random = new Random();
        int rdNumber = random.nextInt(myWordList.size());

        String gettingWord = myWordList.get(rdNumber).word;
        String gettingMean = myWordList.get(rdNumber).mean;
        String gettingSynonim = myWordList.get(rdNumber).synonim;
        String gettingAntonym = myWordList.get(rdNumber).antonym;

        Words askingWord = new Words();
        askingWord.setWord(gettingWord);
        askingWord.setMean(gettingMean);
        askingWord.setSynonim(gettingSynonim);
        askingWord.setAntonym(gettingAntonym);

        myAskingWord.add(askingWord);

        return myAskingWord;
    }

    public String SetQuestions(){

        askingWordInfo = GetSpecificAskingWord();

        Random random = new Random();
        int rdNumber = random.nextInt(4);

        String question = null;
        if(rdNumber == 1)
        {
            question = "'"+ askingWordInfo.get(0).word + "' anlamı nedir ?";
        }
        else if(rdNumber == 2)
        {
            question = "Anlamı " + "'"+ askingWordInfo.get(0).mean + "' olan kelime nedir ?";
        }
        else if(rdNumber == 3)
        {
            question = "Eş Anlamı " + "'"+ askingWordInfo.get(0).synonim + "' olan kelime nedir ?";
        }
        else if(rdNumber == 4)
        {
            question = "Zıt Anlamı " + "'"+  askingWordInfo.get(0).antonym + "' olan kelime nedir ?";
        }

        return question;
    }

    public boolean ControlAnswer(String answer,String question){

        boolean result = false;

        if (askingWordInfo.get(0).mean == answer && question.contains(askingWordInfo.get(0).word))
            result = true;
        else if (askingWordInfo.get(0).word == answer && question.contains(askingWordInfo.get(0).mean))
            result = true;
        else if (askingWordInfo.get(0).word == answer && question.contains(askingWordInfo.get(0).synonim))
            result = true;
        else if (askingWordInfo.get(0).word == answer && question.contains(askingWordInfo.get(0).antonym))
            result = true;
        else
            result = false;

        return result;
    }
}
