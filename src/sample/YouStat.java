package sample;
//Runs the upgrade part of the project for your player this is the stats for your player. and uses machine learning for the other class
//Precondition: The set stats that the user wants
//Postcondition: Upgrades stats when the user wants to upgrade
// Precondition: The stats are dependent on the mode that the User chooses with pre sets for the easy mode, hard mode and the Machine Learning mode that upgrades as time goes on.
////Postcondition: The stats of the user are determined every fight for the Machine Learning mode


import java.util.ArrayList;

public class YouStat {
    private int Strength;
    private int Speed;
    private int Intell;

    public YouStat(int Strength, int Speed, int Intell){
        this.Strength = Strength;
        this.Speed = Speed;
        this.Intell = Intell;
    }


    public int Uaverage(){
        int averageU = Speed*1/3 + Intell*1/3 + Strength*1/3;
        return averageU;
    }

}

