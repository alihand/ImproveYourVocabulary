package com.example.gokcedemir.improveyourvocab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button testButton = (Button) findViewById(R.id.btnTest);

        testButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(MainMenu.this, Test.class);
                startActivity(intent);
            }

        });

        Button addButton = (Button) findViewById(R.id.btnAdd);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(MainMenu.this, AddNewWord.class);
                startActivity(intent);
            }

        });

        Button showButton = (Button) findViewById(R.id.btnShow);

        showButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(MainMenu.this, ShowAllWord.class);
                startActivity(intent);
            }

        });
    }
}
