package com.example.gokcedemir.improveyourvocab;

public class Words {

    public String word;
    public String mean;
    public String synonym;
    public String antonym;

    /*public Words(String word,String mean,String synonym,String antonym){
        this.word = word;
        this.mean = mean;
        this.synonym = synonym;
        this.antonym = antonym;
    }*/

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getMean() {
        return mean;
    }
    public void setMean(String mean) {
        this.mean = mean;
    }
    public String getSynonym() {
        return synonym;
    }
    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }
    public String getAntonym() {
        return antonym;
    }
    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }
}
