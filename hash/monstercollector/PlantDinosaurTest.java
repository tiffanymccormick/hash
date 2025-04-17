import java.util.Scanner;

public class PlantDinosaurTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Create the dinosaurs
        PlantDinosaur defaultDino = new PlantDinosaur();
        PlantDinosaur otherDino = new PlantDinosaur(120, 55, 40, 38);

        // Print initial states
        System.out.println(defaultDino.toString());
        System.out.println(otherDino.toString());

        // Get new values from user input
        System.out.print("\nEnter new current health for custom dinosaur: ");
        int newHealth = scan.nextInt();
        System.out.print("Enter new attack value for custom dinosaur: ");
        int newAttack = scan.nextInt();

        // Use user input to update dinosaur statistics
        otherDino.setCurrentHealth(newHealth);
        otherDino.setAttack(newAttack);

        // Print updated stats
        System.out.println("\n"+ defaultDino.toString());
        System.out.println(otherDino.toString());

        scan.close();
    }
}
