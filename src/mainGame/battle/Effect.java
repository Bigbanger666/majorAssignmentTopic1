package mainGame.battle;

import mainGame.member.Stats;

public class Effect {
    String name;
    String description;
    String type; // Type of effect (e.g., "buff", "debuff")
    int strengthChange;
    int defenceChange;
    int intelligenceChange;
    int duration; // Duration in rounds for which the effect will be active
    boolean isActive; // Tracks if the effect is currently active
    Stats originalStats; // Stores the original stats before applying effect

    public Effect(String name, String description, String type, int strengthChange, int defenceChange, int intelligenceChange, int duration) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.strengthChange = strengthChange; // Change to strength; can be positive or negative
        this.defenceChange = defenceChange;
        this.intelligenceChange = intelligenceChange;
        this.duration = duration + 1; // Duration extended by 1 to account for initial application round
        this.isActive = false; // Starts as inactive
    }

    public void apply(Stats targetStats) {
        /*
         Apply effect if duration is still active.
         Ensures effect is applied only if duration is greater than 0.
        */
        if (duration > 0) {
            // initialise effect by saving original stats once at the start
            if (!isActive) {
                originalStats = targetStats.createSnapshot(); // Capture original stats for restoration
                isActive = true; // Mark effect as active
            }

            // Apply each stat change within valid bounds of 0 to 20
            targetStats.setStrength(Math.max(Math.min(originalStats.getStrength() + strengthChange, 20), 0));
            targetStats.setDefence(Math.max(Math.min(originalStats.getDefence() + defenceChange, 20), 0));
            targetStats.setIntelligence(Math.max(Math.min(originalStats.getIntelligence() + intelligenceChange, 20), 0));

            duration--; // Reduce duration for each round the effect is applied
        }

        // Restore original stats if duration has expired and effect was active
        if (duration == 0 && isActive) {
            targetStats.updateStats(originalStats.getStrength(), originalStats.getDefence(), originalStats.getIntelligence());
            isActive = false; // Mark effect as inactive after restoring stats
        }
    }

    // Getter to check if effect is currently active
    public boolean isActive() {
        return isActive;
    }

    // Getters for effect name, description, and type for display or logging purposes
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getStrengthChange() {
        return strengthChange;
    }

    public int getDefenceChange() {
        return defenceChange;
    }

    public int getIntelligenceChange() {
        return intelligenceChange;
    }
}
