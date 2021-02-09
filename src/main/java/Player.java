/**
 * @author Daniel Earley
 * @version 2.0
 * @since 2021-02-06
 * Player class for the user
 */
public class Player {
    public String name;
    public int money;

    public Player(String name){
        setName(name);
        setmoney(500);
    }

    // Getters
    public String getName(){return this.name;}
    public int getmoney(){return this.money;}

    // Setters
    public void setName(String name){ this.name = name; }
    public void setmoney(int money){ this.money = money; }

    // Other Methods
    public int withdraw(int money){
        if (isEmpty()){
            System.out.println("No money left in the bank!");
            return 0;
        }
        this.money -= money;
        return money;
    }

    public void deposit(int money){
        this.money += money;
    }

    public boolean isEmpty(){
        if (this.money <= 0){
            return true;
        } else { 
            return false;
        }
    }

    public String toString(){
        if (isEmpty()){
            // return "Name: " + name + "\nBank Account: $" + 0 + "\n";
            return String.format("Name: %s, Bank Account: $%d", name, 0);
        }
        
        return String.format("Name: %s, Bank Account: $%d", name, money);
    }
}
