import java.util.Scanner;
/**
 * @author Daniel Earley
 * @version 1.0
 * @since 2021-02-06
 * 
 * The purpose of this class is to create bets
 * on the outcome of battles
 * As well as create a currency system (which at this point is 
 * basically just points that aren't worth anything)
 * 
 * Betting example
 * player A and player B
 * odds are 6/1 for player A to win against player B
 * if you bet $100 on A, and A wins then you receive $100 + $(100 * 1/6)
 * else if you bet $100 on B, and B wins then you receive $100 + $(100 * 6/1)
 */
public class Gamble {
    public int pot;
    private int powerLevel1;
    private int powerLevel2;
    private int choice;
    private String orcName1;
    private String orcName2;
    

    public Gamble(Orc orc1, Orc orc2){
        this.powerLevel1 = orc1.getPowerLevel();
        this.powerLevel2 = orc2.getPowerLevel();
        this.orcName1 = orc1.getName();
        this.orcName2 = orc2.getName();
    }

    public void bet(Player player){
        // int value, int choice

        Scanner scan = new Scanner(System.in);
        System.out.printf("Place your bets!\nType 1 if you believe %s will win!\nType 2 if you think %s will be victorius!\n", orcName1, orcName2);
        int choice = scan.nextInt();
        System.out.printf("Now enter the amount of money you would like to bet!\n");
        int value = scan.nextInt();
        scan.close();
        
        // Solidify choice
        switch (choice){
            case 1:
                System.out.printf("You've bet %d on %s to win!\n", value, orcName1);
                break;
            case 2:
                System.out.printf("You've bet %d on %s to win!\n", value, orcName2);
                break;
        }

        // Add money to the pot and solidfy choice
        this.pot = player.withdraw(value);
        this.choice = choice;
    }

    public int returns(Orc orc){
        // Based on the Winning orc's name
        if (orc.getName() == orcName1) {
            // If you chose this orc to win then
            if (choice == 1){
                int pay = payout();
                System.out.printf("Congratulations! you've just won $%d\n", pay);
                return pay;
            } else {
                System.out.printf("Sorry, but unfortunately you've lost $%d\n", pot);
                return 0;
            }
        } else {
            if (choice == 2) {
                int pay = payout();
                System.out.printf("Congratulations! you've just won $%d\n", pay);
                return pay;
            } else {
                System.out.printf("Sorry, but unfortunately you've lost $%d\n", pot);
                return 0;
            }
        }   
    }

    private int payout(){
        int odds = calculateOdds();
        System.out.printf("powerLevel1 = %d, powerLevel2 = %d\n", powerLevel1, powerLevel2);
        // If you bet with the odds in your favour, payout is less
        if (odds == choice) {
            // If the odds are that orc1 will win, then the lesser payout is power2/power1
            if (odds == 1){
                return (int)(pot + (pot * ((double)powerLevel2/(double)powerLevel1)));
            } else {
                return (int)(pot + (pot * ((double)powerLevel1/(double)powerLevel2)));
            }
        } else if (odds == 3) {
            // if the odds were even
            System.out.println(pot + pot);
            return pot + pot;
        }

        // If you bet with the odds not in your favour, payout is greater
        if (choice != odds && odds != 3){
            // If the odds are that orc1 will win, then the greater payout is power1/power2
            if (odds == 1){
                return (int)(pot + (pot * ((double)powerLevel1/(double)powerLevel2)));
            } else {
                return (int)(pot + (pot * ((double)powerLevel2/(double)powerLevel1)));
            }
        }

        System.out.println("There is a problem if the return is 0");
        return 0;
    }

    public void printOdds(Orc orc1, Orc orc2){

        switch(calculateOdds()){
            case 1:
                System.out.printf("The odds are %d to %d on %s to beat %s!\n", powerLevel1, powerLevel2, orc1.getName(), orc2.getName());
                break;
            
            case 2:
                System.out.printf("The odds are %d to %d on %s to beat %s!\n", powerLevel2, powerLevel1, orc2.getName(), orc1.getName());
                break;

            case 3:
                System.out.println("The odds are 1 to 1!");
                System.out.printf("%s and %s are evenly matched it seems!\n", orc1.getName(), orc2.getName());
                break;
        }
    }

    public int calculateOdds(){
        if (powerLevel1 > powerLevel2){
           return 1;
        } else if (powerLevel2 > powerLevel1){
            return 2;
        } else {
            return 3;
        }
    }
}
