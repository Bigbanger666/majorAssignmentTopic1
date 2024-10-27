package test;

import mainGame.member.Member;
import mainGame.equipment.Armour;
import mainGame.equipment.Weapon;
import mainGame.member.Class;
import mainGame.member.Race;
import mainGame.member.Stats;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberTest {
    public Member gandalf;
    public Race elfRace;
    public Class mageClass;
    public Armour armour;
    public Weapon weapon;

    @BeforeEach
    public void setup() {
        // Create race and class for the member
        elfRace = new Race("Elf", "A mystical race", 2, -1, 3);
        mageClass = new Class("Mage", "A wizard who has studied alchemy", -1, 0, 1);

        // Create armour and weapon for the member
        armour = new Armour("Godly Armour", 5);
        weapon = new Weapon("Godly Sword", "Sword", 8, 3);

        // Create a new Member object with these attributes
        gandalf = new Member("Gandalf", 1000, elfRace, mageClass, armour, null, weapon);
    }

    @Test
    /**
     * initial Member creation, including base health and name.
     */
    public void testMemberCreation() {
        // Verify member creation
        assertNotNull(gandalf, "Gandalf should be successfully created.");
        assertEquals("Gandalf", gandalf.getName(), "Member name should be Gandalf.");
        assertEquals(1000, gandalf.getHealth(), "Initial health should be 1000.");
    }

    @Test
    /**
     * Tests base and current stats in a new Member.
     * Ensures base stats are the same as current stats when creating.
     */
    public void testMemberStats() {
        Stats baseStats = gandalf.getBaseStats();
        Stats currentStats = gandalf.getCurrentStats();

        // Check if each stat matches
        assertEquals(baseStats.getStrength(), currentStats.getStrength(),
                "Strength should match between base and current stats.");
        assertEquals(baseStats.getIntelligence(), currentStats.getIntelligence(),
                "Intelligence should match between base and current stats.");
        assertEquals(baseStats.getDefence(), currentStats.getDefence(),
                "Defence should match between base and current stats.");
    }

    /**
     * Tests damage of weapons
     * Ensures that the damage roll is correctly calculating the damage.
     */
    @Test
    public void testWeaponDamageRolls() {
        // Testing weapon damage over multiple rolls
        for (int i = 0; i < 20; i++) {
            int damage = gandalf.getWeaponDamage();
            assertTrue(damage >= 0 && damage <= 8, "Damage rolled should be within the expected range for the weapon.");
        }
    }

    /**
     * Tests if each Member's health reduces and the defending behaviour when taking damage.
     * Confirms that defending prevents health decrease, but if youre not defending you take damage.
     * it.
     */
    @Test
    public void testTakeDamage() {
        // Testing health and defending behavior
        gandalf.takeDamage(50);
        assertEquals(950, gandalf.getHealth(), "Health should decrease to 950 after taking 50 damage.");

        // Set defending to true and attempt to take damage
        gandalf.setDefending(true);
        gandalf.takeDamage(100);
        assertEquals(950, gandalf.getHealth(), "Health should remain unchanged at 950 while defending.");

        // Turn off defending and apply damage again
        gandalf.setDefending(false);
        gandalf.takeDamage(50);
        assertEquals(900, gandalf.getHealth(), "Health should decrease to 900 after taking another 50 damage.");
    }

    @Test
    public void testIsAlive() {
        // Check if the member is alive
        assertTrue(gandalf.isAlive(), "Gandalf should be alive initially.");

        // Make Gandalf take enough damage to potentially die
        gandalf.takeDamage(1000);
        assertFalse(gandalf.isAlive(), "Gandalf should not be alive after taking 1000 damage.");
    }

    @Test
    public void testDefendingBehavior() {
        // Set defending to true
        gandalf.setDefending(true);
        assertTrue(gandalf.isDefending(), "Gandalf should be defending.");

        // Set defending to false
        gandalf.setDefending(false);
        assertFalse(gandalf.isDefending(), "Gandalf should not be defending.");
    }
}
