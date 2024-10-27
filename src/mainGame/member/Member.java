package mainGame.member;

import mainGame.equipment.*;
import mainGame.utils.StatCalculator;

public class Member {

    String memberName; // Name of the member
    int health; // Health points of the member
    Race memberRace; // Race to which the member belongs
    Class memberClass; // Class of the member
    Stats baseStats; // Base stats before any equipment
    Stats stats; // Current stats after buffs
    Armour armour; // Armour equipped by the member
    Ring ring; // Ring equipped by the member
    Weapon weapon; // Weapon equipped by the member
    boolean defending = false; // Whether the member is defending

    // Constructor to initialise member with specified details and equipment
    public Member(String memberName, int health, Race memberRace, Class memberClass, Armour armour, Ring ring, Weapon weapon) {
        this.memberName = memberName;
        this.health = health;
        this.memberRace = memberRace;
        this.memberClass = memberClass;
        this.baseStats = new Stats();
        
        // Assigns default armour if none provided
        if(armour == null) {
            this.armour = new Armour();
        } else {
            this.armour = armour;
        }
        
        // Assigns default ring if none provided
        if (ring == null) {
            this.ring = new Ring();
        } else {
            this.ring = ring;
        }
        
        // Assigns default weapon if none provided
        if(weapon == null) {
            this.weapon = new Weapon();
        } else {
            this.weapon = weapon;
        }
        
        // Apply buffs from class, race, and equipment to base stats
        StatCalculator.applyBuffs(this.baseStats, this.memberClass, this.memberRace, this.armour, this.ring, this.weapon);
        this.stats = baseStats.createSnapshot();
    }

    // Getters

    public String getName() {
        return memberName;
    }

    public int getStrength() {
        return baseStats.getStrength();
    }

    public int getDefence() {
        return baseStats.getDefence();
    }

    public int getIntelligence() {
        return baseStats.getIntelligence();
    }

    public int getHealth() {
        if(health <= 0) {
            return 0;
        } return health;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    public Stats getCurrentStats() {
        return stats;
    }

    public String getWeaponName() {
        return weapon.getName();
    }

    public int getWeaponDamage() {
        return weapon.rollDamage();
    }

    // Checks if member is alive based on health points
    public boolean isAlive() {
        return health > 0;
    }

    // Checks if member is defending
    public boolean isDefending() {
        return defending;
    }

    // Sets defending status
    public void setDefending(boolean defending) {
        this.defending = defending;
    }

    // Applies damage to member if not defending
    public void takeDamage(int damage) {
        if (!defending) {
            health -= damage;
        } else {
            System.out.println(memberName + " defended and took no damage!");
        }
    }

    @Override
    public String toString() {
        return "Name: " + memberName
                + " Health: " + health
                + " Race: " + memberRace.raceName
                + " Class: " + memberClass.className
                + " " + baseStats.toString()
                + " Armour: " + armour.getName()
                + " Ring: " + ring.getName()
                + " Weapon: " + weapon.getName();
    }
}
