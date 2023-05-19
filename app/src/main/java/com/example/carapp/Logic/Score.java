package com.example.carapp.Logic;

public class Score {

    private String userName = "";
    private int userScore=0 ;
    private double lat=0.0;
    private double lag=0.0;


    public void Score(){}

    public String getUserName() {
        return userName;
    }

    public Score setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getUserScore() {
        return userScore;
    }

    public Score setUserScore(int userScore) {
        this.userScore = userScore;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Score setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLag() {
        return lag;
    }

    public Score setLag(double lag) {
        this.lag = lag;
        return this;
    }

    @Override
    public String toString() {
        return "Score{" +
                "userName='" + userName + '\'' +
                ", userScore=" + userScore +
                ", lat=" + lat +
                ", lag=" + lag +
                '}';
    }

}
