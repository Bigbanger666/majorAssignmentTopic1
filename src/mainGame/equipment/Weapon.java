package mainGame.equipment;

import mainGame.battle.DiceRoll;

public class Weapon {
    String weaponName; // Name of the weapon
    String weaponType; // Type of weapon (e.g., "sword", "bow")
    int damageRoll; // Sides on the dice used to determine damage
    int strengthBuff; // Strength bonus provided by the weapon

    // Default constructor: sets weapon to "Fists" with minimum damage roll and no strength buff
    public Weapon() {
        this.weaponName = "Fists";
        this.weaponType = ""; 
        this.damageRoll = 1; // d1 for minimum damage roll
        this.strengthBuff = 0; // No strength buff
    }

    // Parameterised constructor: initialises weapon with a specific name, type, damage roll, and strength buff
    public Weapon(String name, String type, int roll, int buff) {
        weaponName = name;
        weaponType = type;
        damageRoll = roll;
        strengthBuff = buff;
    }

    // Getter for weapon name
    public String getName() {
        return weaponName;
    }

    // Rolls for damage based on the dice roll specified for the weapon
    public int rollDamage() {
        DiceRoll dice = new DiceRoll();
        return dice.roll(damageRoll); // Rolls a dice of `damageRoll` sides to determine damage
    }

    // Getter for strength buff
    public int getStrengthBuff() {
        return strengthBuff;
    }

    // Setter for strength buff, returns updated strength buff
    public int setStrengthBuff(int n) {
        return strengthBuff = n;
    }

    @Override
    public String toString() {
        return "Name: " + weaponName +
                ", Type: " + weaponType +
                ", Damage Roll: d" + damageRoll +
                ", Strength Buff: +" + strengthBuff;
    }
}
