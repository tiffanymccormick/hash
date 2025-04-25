/**
 * Base class for all items in the Monster Collector Game.
 * Items can be used on monsters and have a name, description, and cost.
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */

 public class Item {
    private String name;
    private String description;
    private double cost;

    // Default constructor
    public Item() {
        this.name = "None";
        this.description = "None";
        this.cost = 0.0;
    }

    // 3-arg constructor
    public Item(String name, String description, double cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    @Override
    public String toString() {
        return String.format("%s: %s ($%.2f)", name, description, cost);
    }

    public void use(Monster monster) {
    }
}
