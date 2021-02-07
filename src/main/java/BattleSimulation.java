import java.util.Random;
import java.lang.Math;
/**
 * @author Daniel Earley
 * @version 1.0
 * @since 2021-02-06
 * 
 * The purpose of this class is to simulate a fight between 2 Orcs
 * Strength - Defense = Attack dmg
 * Agility and Luck will determine whether the opposing attack hits
 * Attacking and dodging is randomized
 */

public class BattleSimulation {
    /**
     * This is the driver for the battle
     * Attack and Dodge methods are called here
     * Attacking and Dodging is randomized
     * @param orc1  
     * @param orc2
     */
    public static void simulation(Orc orc1, Orc orc2){
        boolean finished = false;
        int roundCounter = 0;
        while (!finished){
            
            System.out.printf("~~~~~~~~~~Begin Round %d~~~~~~~~~~\n", roundCounter);

            // Check if either orc is dead
            if (orc1.isAlive() == false){
                System.out.printf("%s has slayn %s!\n%s is victorius!", orc2.getName(), orc1.getName(), orc2.getName());
                finished = true;
                break;
            } else if (orc2.isAlive() == false){
                System.out.printf("%s has slayn %s!\n%s is victorius!", orc1.getName(), orc2.getName(), orc1.getName());
                finished = true;
                break;
            }

            // Fight
            // Randomize who moves first based on the higher value
            if (move(orc1) >= move(orc2)){
                // Orc1 attacks first and Orc2 attack second
                attack(orc1, orc2);
                attack(orc2, orc1);
            } else {
                // Orc2 attacks first and Orc1 attack second
                attack(orc2, orc1);
                attack(orc1, orc2);
            }

            System.out.printf("~~~~~~~~~~End of Round %d~~~~~~~~~~\n", roundCounter);
            roundCounter++;
        }
    }

    /**
     * This method determines whether the attack hits and 
     * the amount of damage it deals
     * @param orc1
     * @param orc2
     */
    public static void attack(Orc orc1, Orc orc2){
        // Calculate the dmg dealt
        double dmg = orc1.getStrength() - (orc2.getDefense()*0.75);
        if (dmg <= 10) {dmg = 10;}
        
        // Calculate % chance for orc2 to dodge (A perfect agility and luck stat will allow for 60% dodging)
        double enemyDodge = move(orc2);

        // Randomize attack hitting,
        Random r = new Random();
        double accuracy = r.nextDouble() * 100.0;

        if (accuracy <= enemyDodge){
            // Attack misses or orc2 dodges or orc2 counterattacks
            counterAttack(orc2, orc1);
            System.out.printf("%s dodged %s's attack!\n", orc2.getName(), orc1.getName());
        } else {
            // Attack hit
            System.out.printf("%s hit %s for %.2f damage!\n", orc1.getName(), orc2.getName(), dmg);
            orc2.setHealth(orc2.getHealth() - dmg);
        }
    }

    public static double move(Orc orc){
        return (orc.getAgility() * 0.5) + orc.getLuck();
    }

    /**
     * When an orc dodges an attack, there will be a small percent chance that they can counterattack
     * This percent chance depends on their luck
     * @param orc1
     * @param orc2
     */
    public static void counterAttack(Orc orc1, Orc orc2){
        Random r = new Random();
        double percent = r.nextDouble() * 10;

        double dmg = orc1.getStrength() - (orc2.getDefense()*0.75) + 10;
        if (dmg <= 10) {dmg = 20;}

        if ((orc1.getLuck()/2)+1 >= percent){
            System.out.printf("%s counterattacks %s and deals %.2f damage!\n", orc1.getName(), orc2.getName(), dmg);
            orc2.setHealth(orc2.getHealth() - dmg);
        }
    }

    // testing
    public static void main(String[] args){
        Orc A = new Orc(100.0,100.0,100.0,350.0,1);
        Orc B = new Orc(40,100,100,350,1);

        System.out.println(A);
        System.out.println(B);

        simulation(A, B);
    }
}
