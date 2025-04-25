/**
 * HealItem extends the Item class to heal a monster, providing healing functionality
 * 
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 1.0
 */

public class HealItem extends Item {
    private int healAmount;

    // Default constructor
    public HealItem() {
        super();
        this.healAmount = 0;
    }

    // 4-arg constructor
    public HealItem(String name, String description, double cost, int healAmount) {
        super(name, description, cost);
        this.healAmount = healAmount;
    }

    public int getHealAmount() { return healAmount; }
    public void setHealAmount(int healAmount) { this.healAmount = healAmount; }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Heals: %s]", (healAmount == -1 ? "FULL" : healAmount));
    }

    @Override
    public void use(Monster monster) {
        if (healAmount == -1) {
            monster.setCurrentHealth(monster.getMaxHealth());
        } else {
            int newHealth = monster.getCurrentHealth() + healAmount;
            monster.setCurrentHealth(Math.min(newHealth, monster.getMaxHealth()));
        }
    }
}
