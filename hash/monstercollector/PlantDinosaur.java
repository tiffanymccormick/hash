
/**
 * Making the Monster Collector Game more object oriented using the enum MonsterType for the Plant Dinosaur type
 * instead of the default "Grass"
 *
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 2.0
 */
public class PlantDinosaur {

    private final MonsterType type = MonsterType.GRASS;
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int defense;
    private int speed;

    // Default constructor that predefines statistics (the instance variables) for the Grass Dinosaur
    public PlantDinosaur() {
        maxHealth = 135;
        currentHealth = maxHealth;
        attack = 49;
        defense = 49;
        speed = 45;
    }

    // Constructor that initializes the instance variables with the values passed as arguments
    public PlantDinosaur(int maxHealth, int attack, int defense, int speed) {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    // getter methods for all instance variables
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public MonsterType getType() {
        return type;
    }

    // Setter methods for all instance variables
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // toString method that prints out the type and statistics associated with instance of PlantDinosaur object class 
    @Override
    public String toString() {
        return "PlantDinosaur [" +
                "type='" + type + '\'' +
                ", maxHealth=" + maxHealth +
                ", currentHealth=" + currentHealth +
                ", attack=" + attack +
                ", defense=" + defense +
                ", speed=" + speed +
                ']';
    }
}
