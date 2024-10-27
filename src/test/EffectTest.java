package test;

import mainGame.battle.Effect;
import mainGame.member.Member;
import mainGame.member.Race;
import mainGame.member.Stats;
import mainGame.member.Class;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EffectTest {
    public Member gandalf;
    public Effect strengthDebuff;

    @BeforeEach
    public void setup() {
        // Create race and class objects
        Race elfRace = new Race("Elf", "A mystical race", 2, -1, 3);
        Class mageClass = new Class("Mage", "A wizard who has studied alchemy", -1, 0, 1);

        // Create a Member object with predefined stats
        gandalf = new Member("Gandalf", 1000, elfRace, mageClass, null, null, null);

        // Create an effect that debuffs stats temporarily (3 rounds)
        strengthDebuff = new Effect("Strength Debuff", "Reduces strength and defence temporarily", "debuff", -5, -5, 0,
                3);
    }

    /**
     * Ensures Member's initial stats are correctly set, especially health.
     */
    @Test
    public void testInitialStats() {
        assertEquals(1000, gandalf.getHealth(), "Initial health should be 1000.");
    }

    /**
     * Tests the application of a temporary debuff effect on a member's stats.
     * Verifies if strength and defence are modified as expected after applying the
     * effect.
     */
    @Test
    public void testApplyEffect() {
        // Calculate expected stats with the effect
        int expectedStrength = Math.max(0,
                gandalf.getCurrentStats().getStrength() + strengthDebuff.getStrengthChange());
        int expectedDefence = Math.max(0, gandalf.getCurrentStats().getDefence() + strengthDebuff.getDefenceChange());

        // Apply debuff effect
        strengthDebuff.apply(gandalf.getCurrentStats());

        // Assert updated values
        assertEquals(expectedStrength, gandalf.getCurrentStats().getStrength(),
                "Strength should be updated correctly.");
        assertEquals(expectedDefence, gandalf.getCurrentStats().getDefence(), "Defence should be updated correctly.");
    }

    /**
     * Tests the expiration of an effect based on duration.
     * Applies effect over several rounds and checks its active status after
     * expiration.
     */
    @Test
    public void testEffectDuration() {
        // Apply the effect over multiple rounds and check active status
        for (int round = 1; round <= 4; round++) {
            strengthDebuff.apply(gandalf.getBaseStats());
        }
        assertFalse(strengthDebuff.isActive(), "Effect should not be active after 3 rounds.");
    }

    @Test
    public void testResetStats() {
        // Create a snapshot of the original stats
        Stats originalStats = gandalf.getBaseStats().createSnapshot();

        // Apply the debuff
        strengthDebuff.apply(gandalf.getBaseStats());

        // Reset stats
        gandalf.getBaseStats().updateStats(originalStats.getStrength(), originalStats.getDefence(),
                originalStats.getIntelligence());

        // Check if stats are reset correctly
        assertEquals(originalStats.getStrength(), gandalf.getBaseStats().getStrength(),
                "Strength should be reset to original.");
        assertEquals(originalStats.getDefence(), gandalf.getBaseStats().getDefence(),
                "Defence should be reset to original.");
    }

    @Test
    public void testEffectIsActive() {
        // Check if the effect is initially active
        assertFalse(strengthDebuff.isActive(), "Effect should not be active initially.");

        // Apply the effect for 3 rounds
        for (int i = 0; i < 3; i++) {
            strengthDebuff.apply(gandalf.getBaseStats());
        }

        // Check if the effect is still active after 3 rounds
        assertTrue(strengthDebuff.isActive(), "Effect should still be active after 3 rounds.");

        // Apply the effect one more time to exceed the duration
        strengthDebuff.apply(gandalf.getBaseStats());

        // Check if the effect is no longer active
        assertFalse(strengthDebuff.isActive(), "Effect should not be active after exceeding duration.");
    }
}
