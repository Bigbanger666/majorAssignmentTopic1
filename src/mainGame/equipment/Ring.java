package mainGame.equipment;

public class Ring {
    String ringName; // Name of the ring
    int intelligenceBuff; // Intelligence bonus provided by the ring

    // Default constructor: sets default ring to "None" with no intelligence buff
    public Ring() {
        ringName = "None";
        intelligenceBuff = 0;
    }

    // Parameterised constructor: initialises ring with a specific name and intelligence buff
    public Ring(String name, int buff) {
        ringName = name;
        intelligenceBuff = buff;
    }

    // Getter for ring name
    public String getName() {
        return ringName;
    }

    // Getter for intelligence buff
    public int getIntelligenceBuff() {
        return intelligenceBuff;
    }

    // Setter for intelligence buff, returns updated intelligence buff
    public int setIntelligenceBuff(int n) {
        return intelligenceBuff = n;
    }

    @Override
    public String toString() {
        return "Name: " + ringName + ", IntelligenceBuff: " + intelligenceBuff;
    }
}
