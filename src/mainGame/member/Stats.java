package mainGame.member;

import java.util.Random;

public class Stats {
    int strength; // Strength stat value
    int intelligence; // Intelligence stat value
    int defence; // Defence stat value

    // Default constructor generates random stats between 1 and 20
    public Stats() {
        Random rand = new Random();
        this.strength = rand.nextInt(20) + 1;
        this.intelligence = rand.nextInt(20) + 1;
        this.defence = rand.nextInt(20) + 1;
    }

    // Constructor for specified stat values
    public Stats(int strength, int intelligence, int defence) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.defence = defence;
    }

    // Getters and setters for each stat

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    // Updates the current stats to new values
    public void updateStats(int strength, int defence, int intelligence) {
        this.strength = strength;
        this.defence = defence;
        this.intelligence = intelligence;
    }

    // Creates a copy of the current stats for when effects are applied and need to be restored
    public Stats createSnapshot() {
        return new Stats(strength, intelligence, defence);
    }

    @Override
    public String toString() {
        return "Strength: " + strength + " Defence: " + defence + " Intelligence: " + intelligence;
    }
}
