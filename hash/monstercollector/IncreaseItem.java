/**
 * IncreaseItem increases a specific stat on a monster by overriding the use method to apply 
 * stat increases
 *  
 * Copyright 2025 Howard Community College
 *
 * @author Tiffany McCormick
 * @version 2.0
 */

 public class IncreaseItem extends Item {
    private Stat stat;
    private int increase;

    // Default constructor
    public IncreaseItem() {
        super();
        this.stat = Stat.CURRENT_HEALTH;
        this.increase = 0;
    }

    // 5-arg constructor
    public IncreaseItem(String name, String description, double cost, Stat stat, int increase) {
        super(name, description, cost);
        this.stat = stat;
        this.increase = increase;
    }

    public Stat getStat() { return stat; }
    public void setStat(Stat stat) { this.stat = stat; }

    public int getIncrease() { return increase; }
    public void setIncrease(int increase) { this.increase = increase; }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Increases: %s by %d]", stat, increase);
    }

    @Override
    public void use(Monster monster) {
        switch (stat) {
            case MAX_HEALTH:
                monster.setMaxHealth(monster.getMaxHealth() + increase);
                break;
            case CURRENT_HEALTH:
                monster.setCurrentHealth(monster.getCurrentHealth() + increase);
                break;
            case ATTACK:
                monster.setAttack(monster.getAttack() + increase);
                break;
            case DEFENSE:
                monster.setDefense(monster.getDefense() + increase);
                break;
            case SPEED:
                monster.setSpeed(monster.getSpeed() + increase);
                break;
        }
    }
}
