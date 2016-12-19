package com.example.gokcedemir.improveyourvocab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="MyVocabulary";

    public static final String KEY_MEAN="wordMean";

    public static final String TABLE_NAME="MyWords";

    public static final String KEY_WORD="word";

    public static final String KEY_SYNONIM="synonimofWord";

    public static final String KEY_ANTONYM="antonymofWord";

    public SqlLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 3);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="Create table if not exists "+TABLE_NAME+" ("+KEY_WORD+" TEXT , "+KEY_MEAN+" TEXT, "+KEY_SYNONIM+" TEXT, "+KEY_ANTONYM+" TEXT)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists "+TABLE_NAME);
        onCreate(db);

    }

}