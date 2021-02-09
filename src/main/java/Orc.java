import java.util.Hashtable;

/**
 * @author Daniel Earley
 * @version 3.0
 * @since 2021-02-06
 * Create and initialize Orc Class
 */

public class Orc extends Character{
    public String name;
    public int powerLvl;
    public double hp;
    public double strength;
    public double agility;
    public double defense;
    public double luck;

    public Orc(){
        setName(RandomizeNames.getRandomName());

        // Randomize Orc Stats
        Hashtable<String, Double> stats = new Hashtable<String, Double>();
        stats = RandomizeStats.statRandomizer();

        // Set Orc Stats
        setHealth(stats.get("hp"));
        setStrength(stats.get("str"));
        setAgility(stats.get("agl"));
        setDefense(stats.get("def"));
        setLuck(stats.get("luck"));

        // Set Orc Power Level
        setPowerLevel(calculatePowerLevel());
    }

    public Orc(double str, double agl, double def, double hp, double luck){
        setName(RandomizeNames.getRandomName());
        setStrength(str);
        setAgility(agl);
        setDefense(def);
        setHealth(hp);
        setLuck(luck);
        setPowerLevel(calculatePowerLevel());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPowerLevel() {
        return this.powerLvl;
    }

    @Override
    public double getHealth() {
        return this.hp;
    }

    @Override
    public double getStrength() {
        return this.strength;
    }

    @Override
    public double getAgility() {
        return this.agility;
    }

    @Override
    public double getDefense() {
        return this.defense;
    }

    @Override
    public double getLuck() {
        return this.luck;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setHealth(double health) {
        this.hp = health;
    }

    @Override
    public void setStrength(double strength) {
        this.strength = strength;
    }

    @Override
    public void setAgility(double agility) {
        this.agility = agility;
    }

    @Override
    public void setDefense(double defense) {
        this.defense = defense;
    }

    @Override
    public void setLuck(double luck) {
        this.luck = luck;
    }

    @Override
    public void setPowerLevel(int power) {
        this.powerLvl = power;
    }

    @Override
    public boolean isAlive() {
        if (this.getHealth() > 0){
            return true;
        } else {
            return false;
        }
    }

    public String toString(){      
        return String.format("Name: %s\nStr: %.2f, Agl: %.2f, Def: %.2f, Hp: %.2f, Luck: %.2f\nPowerLevel: %d", 
                            this.getName(), this.getStrength(), this.getAgility(), this.getDefense(), this.getHealth(), 
                            this.getLuck(), this.getPowerLevel());
    }   

    /**
     * based upon all stats, calculate the power level
     * Ideally the perfect orc is 300 power level
     * @return integer power level
     */
    private int calculatePowerLevel(){
        double statsPower = getStrength() + getAgility() + getDefense();
        statsPower = statsPower * 0.6;

        double hpPower = getHealth() * 0.39;

        double luckPower = getLuck() * 0.3;

        return (int) (statsPower + hpPower + luckPower);
    }

    public static void main(String[] args){
        Orc thisOrc = new Orc();
        Orc opOrc = new Orc(100,100,100,300,10);
        System.out.println(thisOrc);
        System.out.println(opOrc);
    }
    

}