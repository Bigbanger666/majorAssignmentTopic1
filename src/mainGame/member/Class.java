package mainGame.member;

public class Class {
    String className; // Name of the class
    String classDescription; // Description of the class's traits
    int strengthBuff; // Strength bonus for the class
    int defenceBuff; // Defence bonus for the class
    int intelligenceBuff; // Intelligence bonus for the class

    // Constructor to initialise a class with specified attributes
    public Class(String name, String description, int strength, int defence, int intelligence) {
        className = name;
        classDescription = description;
        strengthBuff = strength;
        defenceBuff = defence;
        intelligenceBuff = intelligence;
    }

    // Getters

    public String getClassName() {
        return className;
    }

    public String getClassDescription() {
        return classDescription;
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
        return "Name: " + className +
                " Description: " + classDescription +
                " Strength: " + strengthBuff +
                " Defence: " + defenceBuff +
                " Intelligence: " + intelligenceBuff;
    }
}
