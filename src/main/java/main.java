public class main {
    public static void main(String[] args){
        // Orc orcA = new Orc(70,70,70,300,5);
        // Orc orcB = new Orc(70,70,70,300,5);
        BattleSimulation bs = new BattleSimulation();
        Orc orcA = new Orc();
        Orc orcB = new Orc();
        Gamble money = new Gamble(orcA, orcB);
        Player me = new Player("Daniel");

        // Bet on Orcs
        money.printOdds(orcA, orcB);
        money.bet(me);

        // Run simulation
        Orc winner = bs.simulation(orcA, orcB);

        // Payout money
        me.deposit(money.returns(winner));
        System.out.println(me);
    }
}
