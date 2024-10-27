package test;

import mainGame.member.Stats;
import mainGame.member.Class;
import mainGame.member.Race;
import mainGame.utils.StatCalculator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StatsTest {

    Stats initialStats = new Stats();

    /**
     * Tests the initial random stat ranges for a new Stats object.
     * Ensures each stat falls within the defined range (1-20).
     */
    @Test
    public void testInitialStats() {
        // Assert that stats are within the expected range (1-20)
        assertTrue(initialStats.getStrength() >= 1 && initialStats.getStrength() <= 20,
                "Strength should be between 1 and 20.");
        assertTrue(initialStats.getDefence() >= 1 && initialStats.getDefence() <= 20,
                "Defence should be between 1 and 20.");
        assertTrue(initialStats.getIntelligence() >= 1 && initialStats.getIntelligence() <= 20,
                "Intelligence should be between 1 and 20.");
    }

    /**
     * Validates the modifier from Race and Class applying to the Stats.
     * Ensures strength, defence, and intelligence are updated based on race and
     * class buffs.
     */
    @Test
    public void testApplyModifiers() {
        for (int i = 0; i < 100; i++) {
            Stats snapshotStats = initialStats.createSnapshot();
            Race elfRace = new Race("Elf", "A mystical race", 2, -1, 3);
            Class mageClass = new Class("Mage", "A wizard who has studied alchemy", -1, 0, 1);
            StatCalculator.applyBuffs(initialStats, mageClass, elfRace, null, null, null);

            int expectedStrength = Math.max(
                    Math.min(snapshotStats.getStrength() + elfRace.getStrengthBuff() + mageClass.getStrengthBuff(), 20),
                    1);
            int expectedDefence = Math.max(
                    Math.min(snapshotStats.getDefence() + elfRace.getDefenceBuff() + mageClass.getDefenceBuff(), 20),
                    1);
            int expectedIntelligence = Math.max(Math.min(
                    snapshotStats.getIntelligence() + elfRace.getIntelligenceBuff() + mageClass.getIntelligenceBuff(),
                    20), 1);

            // Validate that the modifiers have been applied correctly
            assertEquals(expectedStrength, initialStats.getStrength(), "Strength should be modified correctly.");
            assertEquals(expectedDefence, initialStats.getDefence(), "Defence should be modified correctly.");
            assertEquals(expectedIntelligence, initialStats.getIntelligence(),
                    "Intelligence should be modified correctly.");
        }
    }

    /**
     * Ensures that a Stats snapshot keeps the original values even after the
     * current stats are modified.
     * Also checks that updates respect the maximum stat limit of 20.
     */
    @Test
    public void testSnapshot() {
        // Create a snapshot of the initial stats
        for (int i = 0; i < 100; i++) {
            Stats snapshotStats = initialStats.createSnapshot();

            // Verify that the snapshot remains unchanged
            assertEquals(snapshotStats.getStrength(), initialStats.getStrength(),
                    "Snapshot strength should remain unchanged.");
            assertEquals(snapshotStats.getDefence(), initialStats.getDefence(),
                    "Snapshot defence should remain unchanged.");
            assertEquals(snapshotStats.getIntelligence(), initialStats.getIntelligence(),
                    "Snapshot intelligence should remain unchanged.");

            // Attempt to update stats beyond their maximum value
            int newStrength = Math.min(initialStats.getStrength() + 1, 20); // Ensure it does not exceed 20
            int newDefence = Math.min(initialStats.getDefence() + 1, 20); // Ensure it does not exceed 20
            int newIntelligence = Math.min(initialStats.getIntelligence() + 1, 20); // Ensure it does not exceed 20

            // Set the new values
            initialStats.setStrength(newStrength);
            initialStats.setDefence(newDefence);
            initialStats.setIntelligence(newIntelligence);

            // Additionally check if the updated values are correct
            if (initialStats.getStrength() < 20) {
                assertEquals(newStrength, initialStats.getStrength(), "Strength should be updated correctly.");
            } else {
                assertEquals(20, initialStats.getStrength(), "Strength should remain at the maximum value of 20.");
            }

            if (initialStats.getDefence() < 20) {
                assertEquals(newDefence, initialStats.getDefence(), "Defence should be updated correctly.");
            } else {
                assertEquals(20, initialStats.getDefence(), "Defence should remain at the maximum value of 20.");
            }

            if (initialStats.getIntelligence() < 20) {
                assertEquals(newIntelligence, initialStats.getIntelligence(),
                        "Intelligence should be updated correctly.");
            } else {
                assertEquals(20, initialStats.getIntelligence(),
                        "Intelligence should remain at the maximum value of 20.");
            }
        }
    }

    /**
     * Tests edge cases for Stats, setting values to minimum and maximum bounds.
     */
    @Test
    public void testEdgeCases() {
        Stats edgeStats = new Stats(1, 1, 1); // Minimum values
        assertEquals(1, edgeStats.getStrength(), "Strength should be 1.");
        assertEquals(1, edgeStats.getDefence(), "Defence should be 1.");
        assertEquals(1, edgeStats.getIntelligence(), "Intelligence should be 1.");

        edgeStats.setStrength(20); // Maximum value
        edgeStats.setDefence(20); // Maximum value
        edgeStats.setIntelligence(20); // Maximum value
        assertEquals(20, edgeStats.getStrength(), "Strength should be 20.");
        assertEquals(20, edgeStats.getDefence(), "Defence should be 20.");
        assertEquals(20, edgeStats.getIntelligence(), "Intelligence should be 20.");
    }
}
