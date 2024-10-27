package mainGame.equipment;

public class Armour {
    String armourName; // Name of the armour
    int defenceBuff; // Defense bonus provided by the armour

    // Default constructor: sets default armour to "Naked" with no defense buff
    public Armour() {
        armourName = "Naked";
        defenceBuff = 0;
    }

    // Parameterised constructor: initialises armour with a specific name and defense buff
    public Armour(String name, int buff) {
        armourName = name;
        defenceBuff = buff;
    }

    // Getter for armour name
    public String getName() {
        return armourName;
    }

    // Getter for defense buff
    public int getDefenceBuff() {
        return defenceBuff;
    }

    // Setter for defense buff, returns updated defense buff
    public int setDefenceBuff(int n) {
        return defenceBuff = n;
    }

    @Override
    public String toString() {
        return "Name: " + armourName + ", DefenceBuff: " + defenceBuff;
    }
}
