import java.util.Scanner;
public class Driver {
    public static void main(String[] args){
        // Get Player Name
        Scanner sc = new Scanner(System.in);
        String name = "";

        System.out.print("Please enter your name: ");
        if (sc.hasNextLine()){
            name = sc.nextLine();

        }
        

        // Initialize everything
        BattleSimulation bs = new BattleSimulation();
        Player player = new Player(name);
        Orc orc1;
        Orc orc2;
        Gamble gamble;
        
        // Main loop
        do {
            // Initialize Orcs and Gambling
            orc1 = new Orc();
            orc2 = new Orc();
            gamble = new Gamble(orc1, orc2);

            // Print Orc Stats
            System.out.println("\n~~~~~~~~~~Presenting Your Fighters!~~~~~~~~~~");
            System.out.println(orc1);
            System.out.println(orc2);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            // Print The Odds
            gamble.printOdds(orc1, orc2);

            // Place Bets
            gamble.bet(player);

            // Run Simulation
            Orc winner = bs.simulation(orc1, orc2);

            // Payout Money
            player.deposit(gamble.returns(winner));
            System.out.println("\n~~~~~~~~~~Player stats~~~~~~~~~~~~");
            System.out.println(player);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        } while (!player.isEmpty());
        System.out.printf("Sorry %s, but without any money you can't stay here!\n", player.getName());
        sc.close();
    }
}
