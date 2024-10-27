package mainGame.member;

public class Race {
    String raceName; // Name of the race
    String raceDescription; // Description of the race traits
    int strengthBuff; // Strength bonus for the race
    int defenceBuff; // Defence bonus for the race
    int intelligenceBuff; // Intelligence bonus for the race

    // Constructor to initialise race with specified attributes
    public Race(String name, String description, int strength, int defence, int intelligence) {
        raceName = name;
        raceDescription = description;
        strengthBuff = strength;
        defenceBuff = defence;
        intelligenceBuff = intelligence;
    }

    // Getters

    public String getRaceName() {
        return raceName;
    }

    public String getRaceDescription() {
        return raceDescription;
    }

    public int getStrengthBuff() {
        return strengthBuff;
    }

    public int getDefenceBuff() {
        return defenceBuff;
    }

    public int getIntelligenceBuff() {
        return intelligenceBuff;
    }

    @Override
    public String toString() {
        return "Name: " + raceName +
                " Description: " + raceDescription +
                " Strength: " + strengthBuff +
                " Defence: " + defenceBuff +
                " Intelligence: " + intelligenceBuff;
    }
}
