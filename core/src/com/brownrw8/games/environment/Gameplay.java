package com.brownrw8.games.environment;


import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Gameplay {
    private static Gameplay ourInstance = new Gameplay();

    public static final int GAME_OVER = 0;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_PAUSED = 2;

    private double playerPoints = 0;
    private double competitorPoints = 0;
    private DecimalFormat df;

    private int state;
    public static Gameplay getInstance() {
        return ourInstance;
    }

    private Gameplay() {
        df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.FLOOR);
        state = GAME_RUNNING;
    }

    public boolean getIsRunning(){
        return state==GAME_RUNNING;
    }

    public boolean getIsGameOver(){
        return state==GAME_OVER;
    }

    public boolean getIsPaused(){
        return state==GAME_PAUSED;
    }

    public String getDisplayedCompetitorPoints() {
        return df.format(competitorPoints);
    }

    public double getCompetitorPoints() {
        return competitorPoints;
    }

    public void setCompetitorPoints(double points) {
        this.competitorPoints = points;
    }

    public void addCompetitorPoints(double points) {
        this.competitorPoints += points;
    }

    public void subtractCompetitorPoints(double points){
        this.competitorPoints -= points;
    }

    public String getDisplayedPlayerPoints() {
        return df.format(playerPoints);
    }

    public double getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(double points) {
        this.playerPoints = points;
    }

    public void addPlayerPoints(double points) {
        this.playerPoints += points;
    }

    public void subtractPlayerPoints(double points){
        this.playerPoints -= points;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getWinner(){
        String result;
        if(competitorPoints>playerPoints){
            result = "P2 Wins!!";
        }else if(playerPoints>competitorPoints){
            result = "P1 Wins!!";
        }else{
            result = "It's a tie!!";
        }
        return result;
    }
}
