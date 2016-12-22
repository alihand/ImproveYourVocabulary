package com.example.gokcedemir.improveyourvocab;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SqlLiteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> word;
    ArrayList<String> wordMean;
    ArrayList<String> synonymofWord;
    ArrayList<String> antonymofWord ;


    public SqlLiteListAdapter(Context context2, ArrayList<String> word, ArrayList<String> wordMean, ArrayList<String> synonymofWord, ArrayList<String> antonymofWord)
    {
        this.context = context2;
        this.word = word;
        this.wordMean = wordMean;
        this.synonymofWord = synonymofWord;
        this.antonymofWord = antonymofWord ;
    }

    public int getCount() {
        return word.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.list_item, null);

            holder = new Holder();

            holder.TWword = (TextView) child.findViewById(R.id.word);
            holder.TWwordMean = (TextView) child.findViewById(R.id.wordMean);
            holder.TWsynonymofWord = (TextView) child.findViewById(R.id.synonymofWord);
            holder.TWantonymofWord = (TextView) child.findViewById(R.id.antonymofWord);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.TWword.setText(word.get(position));
        holder.TWwordMean.setText(wordMean.get(position));
        holder.TWsynonymofWord.setText(synonymofWord.get(position));
        holder.TWantonymofWord.setText(antonymofWord.get(position));

        return child;
    }

    public class Holder {
        TextView TWword;
        TextView TWwordMean;
        TextView TWsynonymofWord;
        TextView TWantonymofWord;
    }

}
