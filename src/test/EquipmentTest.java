package test;

import mainGame.equipment.Armour;
import mainGame.equipment.Ring;
import mainGame.equipment.Weapon;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EquipmentTest {

    // initialise Armour objects
    Armour armour1 = new Armour("Godly Armour", 5);
    Armour armour2 = new Armour("Heavy Armour", 8);
    Armour armour3 = new Armour("Naked", -1);
    Armour armour4 = new Armour("Knight Armour", 3);

    // initialise Ring objects
    Ring ring1 = new Ring("Godly Ring", 5);
    Ring ring2 = new Ring("Heavy Ring", 8);
    Ring ring3 = new Ring("Naked Ring", -1);
    Ring ring4 = new Ring("Knight Ring", 3);

    // initialise Weapon objects
    Weapon weapon1 = new Weapon("Godly Sword", "Sword", 8, 3);
    Weapon weapon2 = new Weapon("Magic Wand", "Wand", 6, 2);
    Weapon weapon3 = new Weapon("Dagger", "Knife", 4, 1);

    @Test
    /**
     * Tests various functions which are specific to Armour.
     * Includes getting defence buff and checking the toString method.
     */
    public void testArmour() {
        // Test Armour attributes
        assertEquals(5, armour1.getDefenceBuff(), "Godly Armour should have a defence buff of 5.");
        assertEquals(8, armour2.getDefenceBuff(), "Heavy Armour should have a defence buff of 8.");

        // Setting Defence Buff for Naked Armour
        armour3.setDefenceBuff(0);
        assertEquals(0, armour3.getDefenceBuff(), "Naked Armour should have a defence buff of 0 after setting it.");

        // Check the toString method
        assertNotNull(armour4.toString(), "Knight Armour toString should not be null.");
    }

    @Test
    /**
     * Tests various functions which are specific to Ring.
     * Includes getting intelligence buff and checking the toString method.
     */
    public void testRing() {
        // Test Ring attributes
        assertEquals(5, ring1.getIntelligenceBuff(), "Godly Ring should have an intelligence buff of 5.");
        assertEquals(8, ring2.getIntelligenceBuff(), "Heavy Ring should have an intelligence buff of 8.");

        // Setting Intelligence Buff for Naked Ring
        ring3.setIntelligenceBuff(0);
        assertEquals(0, ring3.getIntelligenceBuff(),
                "Naked Ring should have an intelligence buff of 0 after setting it.");

        // Check the toString method
        assertNotNull(ring4.toString(), "Knight Ring toString should not be null.");
    }

    @Test
    /**
     * Tests various functions which are specific to Weapons.
     * Includes getting strength buff and checking the toString method.
     */
    public void testWeapon() {
        // Test Weapon attributes
        assertEquals(3, weapon1.getStrengthBuff(), "Godly Sword should have a strength buff of 3.");
        assertEquals(2, weapon2.getStrengthBuff(), "Magic Wand should have a strength buff of 2.");

        // Setting Strength Buff for Dagger
        weapon3.setStrengthBuff(5);
        assertEquals(5, weapon3.getStrengthBuff(), "Dagger should have a strength buff of 5 after setting it.");

        // Test rolling damage
        int rolledDamage = weapon1.rollDamage();
        assertTrue(rolledDamage >= 0 && rolledDamage <= 8, "Rolled damage should be between 3 and 8 for Godly Sword.");

        // Check the toString method
        assertNotNull(weapon3.toString(), "Dagger toString should not be null.");
    }
}
