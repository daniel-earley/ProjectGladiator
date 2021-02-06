/**
 * @author Daniel Earley
 * @version 1.0
 * @since 2021-02-03
 * 
 * Abstract character class
 * Used to define the attributes that the characters will have
 * Used to define the methods that will be utilized by the characters
 */

public abstract class Character {
    public String name;
    public int powerLvl;
    public double hp;
    public double strength;
    public double agility;
    public double defense;
    public double luck;

    // Getters
    public abstract String getName();
    public abstract int getPowerLevel();
    public abstract double getHealth();
    public abstract double getStrength();
    public abstract double getAgility();
    public abstract double getDefense();
    public abstract double getLuck();

    // Setters
    public abstract void setName(String name);
    public abstract void setHealth(double health);
    public abstract void setStrength(double strength);
    public abstract void setAgility(double agility);
    public abstract void setDefense(double defense);
    public abstract void setLuck(double luck);
    public abstract void setPowerLevel(int power);

    public abstract boolean isAlive();
}
