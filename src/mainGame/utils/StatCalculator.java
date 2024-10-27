package mainGame.utils;

import mainGame.equipment.*;
import mainGame.member.Class;
import mainGame.member.Race;
import mainGame.member.Stats;

public class StatCalculator {
    
    public static void applyBuffs(Stats stats, Class memberClass, Race memberRace, Armour armour, Ring ring, Weapon weapon) {
        
        // Initialise buff values with class and race buffs
        int strengthBuff = memberClass.getStrengthBuff() + memberRace.getStrengthBuff();
        int defenceBuff = memberClass.getDefenceBuff() + memberRace.getDefenceBuff();
        int intelligenceBuff = memberClass.getIntelligenceBuff() + memberRace.getIntelligenceBuff();

        // Add weapon's strength buff if provided; otherwise, use default weapon buff
        if (weapon != null) {
            strengthBuff += weapon.getStrengthBuff();
        } else {
            strengthBuff += new Weapon().getStrengthBuff();
        }

        // Add armour's defence buff if provided; otherwise, use default armour buff
        if (armour != null) {
            defenceBuff += armour.getDefenceBuff();
        } else {
            defenceBuff += new Armour().getDefenceBuff();
        }

        // Add ring's intelligence buff if provided; otherwise, use default ring buff
        if (ring != null) {
            intelligenceBuff += ring.getIntelligenceBuff();
        } else {
            intelligenceBuff += new Ring().getIntelligenceBuff();
        }

        // Apply the buffs, ensuring they remain within the range of 1 to 20
        int newStrength = Math.max(1, Math.min(stats.getStrength() + strengthBuff, 20));
        int newDefence = Math.max(1, Math.min(stats.getDefence() + defenceBuff, 20));
        int newIntelligence = Math.max(1, Math.min(stats.getIntelligence() + intelligenceBuff, 20));

        stats.updateStats(newStrength, newDefence, newIntelligence);
    }
}
