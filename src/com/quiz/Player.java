package com.quiz;

public class Player {
    private String name;
    private int totalScore;
    private boolean hasExtraRoll;

    public Player(String name){
        this.name = name;
        totalScore = 0;
        hasExtraRoll = false;
    }

    public String getName() {
        return name;
    }

    public void setHasExtraRoll(boolean hasExtraRoll) {
        this.hasExtraRoll = hasExtraRoll;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean hasExtraRoll() {
        return hasExtraRoll;
    }

    public void updateScore(int setScore){
        totalScore += setScore;
    }
}
