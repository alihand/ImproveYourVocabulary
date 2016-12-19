package com.example.gokcedemir.improveyourvocab;

public class Words {

    public String word;
    public String mean;
    public String synonim;
    public String antonym;

    /*public Words(String word,String mean,String synonim,String antonym){
        this.word = word;
        this.mean = mean;
        this.synonim = synonim;
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
    public String getSynonim() {
        return synonim;
    }
    public void setSynonim(String synonim) {
        this.synonim = synonim;
    }
    public String getAntonym() {
        return antonym;
    }
    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }
}
