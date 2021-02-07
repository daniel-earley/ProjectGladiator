import java.util.Random;
import java.util.Hashtable;

/**
 * @author Daniel Earley
 * @version 1.0
 * 
 * The purpose of this class is to randomize the stats of the characters
 * However, depending on certain stats, the other stats will have higher or lower ranges 
 * to be randomized from
 * keep track of each stat or calculate each stat based off of hte last
 * Strength -> Agility -> Defense -> HP -> Luck
 * If strength is really high, then lower defense range     || If strength is low, then raise the defense range
 * If Agility is really high, then lower the strength range || If Agility is low, then raise the strength range
 * If defense is really high, then lower agility range      || If defense is low, then raise the agility range
*/

// Str -> Def -> Spd -> Str
public class RandomizeStats {

    /**
     * This function calls all the other stat randomizers
     * @return Hashtable of stats
     */
    public static Hashtable<String, Double> statRandomizer(){
        // Create dictionary of stats
        Hashtable<String, Double> stats = new Hashtable<String, Double>();
        stats = initialRandomize();

        // Balance the stats by changing the random range
        double str = rStat(stats.get("str"), stats.get("agl"));
        double def = rStat(stats.get("def"), stats.get("str"));
        double agl = rStat(stats.get("agl"), stats.get("def"));
        
        // replace old stats
        stats.put("str", str);
        stats.put("def", def);
        stats.put("agl", agl);
        return stats;
    }

    /**
     * This function creates the initial stats before they get randomized a 2nd time
     * The 2nd randomization is for balancing
     * @return Hashtable of stats
     */
    private static Hashtable<String, Double> initialRandomize(){
        Random r = new Random();
        
        double str = 40.0 + r.nextDouble() * (100.0 - 40.0);
        double agl = 40.0 + r.nextDouble() * (100.0 - 40.0);
        double def = 40.0 + r.nextDouble() * (100.0 - 40.0);
        double hp  = 300.0 + r.nextDouble() * (400.0 - 300.0);
        double luck = 1.0 + r.nextDouble() * (10.0 - 1.0);
        
        Hashtable<String, Double> stats = new Hashtable<String, Double>();
        stats.put("str", str);
        stats.put("agl", agl);
        stats.put("def", def);
        stats.put("hp", hp);
        stats.put("luck", luck);

        return stats;
    }

    /**
     * This function randomizes a stat depending on another stat @param
     * @param dependency
     * @return stat
     */
    private static double rStat(double stat, double dependency){
        Random r = new Random();
        
        // Randomize stat based on whether dependency is a high or low value
        if (dependency >= 80.0) {
            stat = 40.0 + r.nextDouble() * (65.0 - 40.0);
        } else if (dependency <= 60.0){
            stat = 65.0 + r.nextDouble() * (100.0 - 65.0);
        }

        return stat;
    }

    // public static void main(String[] args){
    //     Hashtable<String, Double> stats = new Hashtable<String, Double>();
    //     stats = statRandomizer();
    //     System.out.println("Stats after balancing: " + stats);
    // }


}
