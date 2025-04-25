/**
 * ItemTest - Class for testing the updated Monster class with the new Items
 * 
 * @author Tiffany McCormick
 * @copyright 2025 Howard Community College
 * @version 1.0
 *
 */
public class ItemTest {

	public static void main(String[] args) {

		// TODO instantiate objects with values shown in the requirements.
		HealItem superHeal = new HealItem("Super Heal", "Heal a monster by 20 health", 10.0, 20);
        HealItem maxHeal = new HealItem("Max Heal", "Fully heal a monster", 40.0, -1);
        IncreaseItem superAttack = new IncreaseItem("Super Attack", "Increase attack stat by 20 points", 10.0, Stat.ATTACK, 20);
		
		// Create a default monster
		Monster monster = new Monster();
		
		// Print the monsters stats
		System.out.println("==== Plant Dinosaur starting stats ====");
		System.out.println(monster);
		System.out.println();
		
		// Attempt to use superHeal and check stats
		// TODO call superHeal use method and passing monsters as argument
		superHeal.use(monster);

        System.out.println("==== After using super heal item ====");
        System.out.println(monster);
        System.out.println();

		// Use superAttack item and check stats
		// TODO call superAttack use method passing monster as argument
		superAttack.use(monster);

        System.out.println("==== After using increase item ====");
        System.out.println(monster);
        System.out.println();
		
		// Use set current health to weaken monster
		// TODO manually decrease monsters health by 100 points
		monster.setCurrentHealth(monster.getCurrentHealth() - 100);

        System.out.println("==== After decreasing health ====");
        System.out.println(monster);
        System.out.println();
		
		// Attempt to use superHeal and check stats
		// TODO call superHeal use method passing monster as argument
		superHeal.use(monster);

        System.out.println("==== After using super heal item again ====");
        System.out.println(monster);
        System.out.println();

		
		// Attempt to use potion and check stats
		// TODO call maxHeal use method passing monster as argument
		maxHeal.use(monster);
		
        System.out.println("==== After using max heal item ====");
        System.out.println(monster);
		

	} // end method main

} // end class ItemTest
