/**
 * Trainer class for Monster Collector Game that will hold the trainer's name and the monsters they have collected,
 * money, and battles won
 * The trainer has a list of the types of monsters they have collected
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */

import java.util.*;

public class Trainer {
    private String name;
    private int battlesWon;
    private double money;
    private List<String> team;
    private int activeMonsterIndex;

    // Constructor that initializes the trainer's name, money, and starter monster. By default, activeMonsterIndex is 0 because the
    // first monster in the team is the active one automatically
    public Trainer(String name, double money, String starter){
        this.name = name;
        this.money = money;
        battlesWon = 0;
        team = new ArrayList<>();
        team.add(starter);
        activeMonsterIndex = 0;
    }

    // Default Trainer constructor with "Plant Dinosaur" as the starter monster, and 0.0 money
    public Trainer(){
        this("Red", 0.0, "Plant Dinosaur");
    }

    public String getName() {
        return name;
    }

    public int getBattlesWon() {
        return battlesWon;
    }

    public double getMoney() {
        return money;
    }

    public List<String> getTeam() {
        return team;
    }

    public int getActiveMonsterIndex() {
        return activeMonsterIndex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBattlesWon(int battlesWon) {
        this.battlesWon = battlesWon;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }

    public void setActiveMonsterIndex(int activeMonsterIndex) {
        this.activeMonsterIndex = activeMonsterIndex;
    }

    // Method to add a new monster to the trainer's team
    public void add(String monster) {
        team.add(monster);
    }

    @Override
    public String toString() {
        return "Trainer [" +
                "name='" + name + '\'' +
                ", battlesWon=" + battlesWon +
                ", money=" + money +
                ", team=" + team +
                ", activeMonsterIndex=" + activeMonsterIndex +
                ']';
    }
}
