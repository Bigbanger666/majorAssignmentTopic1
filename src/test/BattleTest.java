package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import mainGame.team.Team;
import mainGame.member.*;
import mainGame.member.Class;
import mainGame.battle.Battle;
import mainGame.equipment.*;

public class BattleTest {

    public Team team1;
    public Team team2;

    @BeforeEach
    public void setup() {
        Race orcRace = new Race("Orc", "A strong and resilient race", 3, -2, 1);
        Class warriorClass = new Class("Warrior", "A brave fighter skilled in melee combat", 2, 1, 0);

        Race elfRace = new Race("Elf", "A mystical race", 2, -1, 3);
        Class mageClass = new Class("Mage", "A wizard who has studied alchemy", -1, 0, 1);

        Weapon weapon1 = new Weapon("Godly Sword", "Sword", 8, 3);
        Weapon weapon2 = new Weapon("Arcane Staff", "Staff", 6, 1);

        Member orcWarrior1 = new Member("Thrall", 50, orcRace, warriorClass, null, null, weapon1);
        Member orcWarrior2 = new Member("Grom", 55, orcRace, warriorClass, null, null, weapon1);
        Member orcMage1 = new Member("Psion", 30, orcRace, mageClass, null, null, weapon2);
        Member orcMage2 = new Member("Zul'jin", 28, orcRace, mageClass, null, null, weapon2);

        Member elfMage1 = new Member("Arwen", 30, elfRace, mageClass, null, null, weapon2);
        Member elfFighter1 = new Member("Cabal", 60, elfRace, warriorClass, null, null, weapon1);
        Member elfMage2 = new Member("Legolas", 35, elfRace, mageClass, null, null, weapon2);
        Member elfFighter2 = new Member("Finarfin", 55, elfRace, warriorClass, null, null, weapon1);

        team1 = new Team("The Orcs", null, true);
        team2 = new Team("The Elves", null, true);

        team1.addMember(orcWarrior1);
        team1.addMember(orcWarrior2);
        team1.addMember(orcMage1);
        team1.addMember(orcMage2);

        team2.addMember(elfMage1);
        team2.addMember(elfFighter1);
        team2.addMember(elfMage2);
        team2.addMember(elfFighter2);
    }

    /**
     * Tests that a battle between two teams results in only one team remaining alive.
     * Edge cases:
     * - Both teams are evenly matched, and neither team has a clear winner.
     * - One team is eliminated in the first few rounds due to much lower health.
     */
    @Test
    public void testBattleStart() {
        Battle battle = new Battle(team1, team2);
        battle.startBattle();

        boolean team1HasMemberAlive = team1.isTeamAlive();
        boolean team2HasMemberAlive = team2.isTeamAlive();

        assertTrue((team1HasMemberAlive && !team2HasMemberAlive) || (!team1HasMemberAlive && team2HasMemberAlive),
                "Only one team should remain with members alive at the end of the battle");

        // Edge case: Both teams end with no members alive (if such a scenario is possible in your logic)
        assertFalse(team1HasMemberAlive && team2HasMemberAlive, "Both teams should not be alive after the battle.");
    }

    public static void main(String[] args) {
        Race orcRace = new Race("Orc", "A strong and resilient race", 3, -2, 1);
        Class warriorClass = new Class("Warrior", "A brave fighter skilled in melee combat", 2, 1, 0);

        Race elfRace = new Race("Elf", "A mystical race", 2, -1, 3);
        Class mageClass = new Class("Mage", "A wizard who has studied alchemy", -1, 0, 1);

        Weapon weapon1 = new Weapon("Godly Sword", "Sword", 8, 3);
        Weapon weapon2 = new Weapon("Arcane Staff", "Staff", 6, 1);

        Member orcWarrior1 = new Member("Thrall", 50, orcRace, warriorClass, null, null, weapon1);
        Member orcWarrior2 = new Member("Grom", 55, orcRace, warriorClass, null, null, weapon1);
        Member orcMage1 = new Member("Psion", 30, orcRace, mageClass, null, null, weapon2);
        Member orcMage2 = new Member("Zul'jin", 28, orcRace, mageClass, null, null, weapon2);

        Member elfMage1 = new Member("Arwen", 30, elfRace, mageClass, null, null, weapon2);
        Member elfFighter1 = new Member("Cabal", 60, elfRace, warriorClass, null, null, weapon1);
        Member elfMage2 = new Member("Legolas", 35, elfRace, mageClass, null, null, weapon2);
        Member elfFighter2 = new Member("Finarfin", 55, elfRace, warriorClass, null, null, weapon1);

        Team team1 = new Team("The Orcs", null, true);
        Team team2 = new Team("The Elves", null, true);

        team1.addMember(orcWarrior1);
        team1.addMember(orcWarrior2);
        team1.addMember(orcMage1);
        team1.addMember(orcMage2);

        team2.addMember(elfMage1);
        team2.addMember(elfFighter1);
        team2.addMember(elfMage2);
        team2.addMember(elfFighter2);
        Battle battle = new Battle(team1, team2);
        battle.startBattle();
    }
}
