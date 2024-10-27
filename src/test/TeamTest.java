package test;

import mainGame.battle.Battle;
import mainGame.equipment.Weapon;
import mainGame.member.Class;
import mainGame.member.Member;
import mainGame.member.Race;
import mainGame.team.Team;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {
    Race elfRace;
    Class mageClass;

    Race orcRace;
    Class warriorClass;

    Race dwarfRace;
    Class blacksmithClass;

    Race humanRace;
    Class rogueClass;

    Member thrall;
    Member gimli;
    Member arwen;
    Member varys;

    Team team1; 
    Team team2;

    @BeforeEach
    public void setUp() {
        elfRace = new Race("Elf", "A mystical race", 2, -1, 3);
        mageClass = new Class("Mage", "A wizard who has studied alchemy", -1, 0, 1);

        orcRace = new Race("Orc", "A strong and resilient race", 3, -2, 1);
        warriorClass = new Class("Warrior", "A brave fighter skilled in melee combat", 2, 1, 0);

        dwarfRace = new Race("Dwarf", "A stout and hardy race", 1, 3, -1);
        blacksmithClass = new Class("Blacksmith", "An expert in forging weapons and armor", 1, 2, -2);

        humanRace = new Race("Human", "A versatile and adaptable race", 0, 0, 0);
        rogueClass = new Class("Rogue", "A stealthy character with expertise in deception", 0, 2, 1);

        thrall = new Member("Thrall", 35, orcRace, warriorClass, null, null, null);
        gimli = new Member("Gimli", 140, dwarfRace, blacksmithClass, null, null, null);
        arwen = new Member("Arwen", 280, elfRace, mageClass, null, null, null);
        varys = new Member("Varys", 45, humanRace, rogueClass, null, null, null);

        team1 = new Team("The Warriors", null, true); 
        team2 = new Team("The Survivors", null, false);

        team1.addMember(varys);
        team1.addMember(thrall);
        team2.addMember(arwen);
        team2.addMember(gimli);
    }

    @Test
    public void testTeamNames() {
        assertEquals("The Warriors", team1.getTeamName(), "Team 1 name should be 'The Warriors'.");
        assertEquals("The Survivors", team2.getTeamName(), "Team 2 name should be 'The Survivors'.");
    }

    /**
     * Tests adding a new member to a team, checking if team size increases.
     * Edge cases:
     * - Adding a member with null values to see how the team handles incomplete data.
     * - Adding a duplicate member and confirming the team size reflects duplicates if allowed.
     */
    @Test
    public void testAddMemberToTeam() {
        Member newMember = new Member("New Orc", 45, new Race("Orc", "New Orc Race", 1, 0, 0), 
                                       new Class("New Warrior", "New Orc Warrior", 1, 0, 0), 
                                       null, null, new Weapon("New Axe", "Axe", 5, 2));
        
        team1.addMember(newMember);
        assertEquals(3, team1.getTeamSize(), "Team 1 should have 5 members after adding a new member.");

        // Edge case: Adding a null member 
        team1.addMember(null);
        assertEquals(4, team1.getTeamSize(), "Team size should increase by one even when adding a null member.");

        // Edge case: Adding a duplicate member
        team1.addMember(newMember);
        assertEquals(5, team1.getTeamSize(), "Team size should increase when adding a duplicate member.");
    }

    /**
     * Tests that after the battle, only one team is alive.
     * Edge cases:
     * - Both teams completely wiped out.
     * - Verifies team alive status in case of non-standard battle results (e.g., no remaining members).
     */
    @Test
    public void testTeamAliveStatus() {
        Battle battle = new Battle(team1, team2);
        battle.startBattle();
    
        boolean team1IsAlive = team1.isTeamAlive();
        boolean team2IsAlive = team2.isTeamAlive();
    
        assertTrue((team1IsAlive && !team2IsAlive) || (!team1IsAlive && team2IsAlive),
                "Only one team should be alive after the battle.");

        // Edge case: No team is alive (if battle logic allows mutual destruction)
        assertFalse(team1IsAlive && team2IsAlive, "Both teams should not be alive simultaneously.");
    }
    

    @Test
    public void testTeamEquality() {
        Team anotherTeam1 = new Team("The Warriors", null, true);
        anotherTeam1.addMember(varys);
        anotherTeam1.addMember(thrall);
        assertEquals(team1.getTeamName(), anotherTeam1.getTeamName(), "Team names should be equal.");
        assertEquals(team1.getTeamSize(), anotherTeam1.getTeamSize(), "Team sizes should be equal.");
    }

    @Test
    public void testToString() {
        String expectedString = "Team Name: The Warriors\nTeam Size: 2\nIs Team Alive: true"; // Adjust expected values based on initial team members
        assertEquals(expectedString, team1.toString(), "Team 1 string representation should match expected format.");
    }
}
