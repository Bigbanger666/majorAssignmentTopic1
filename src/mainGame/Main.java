package mainGame;

import java.util.ArrayList;
import java.util.Scanner;
import mainGame.member.*;
import mainGame.member.Class;
import mainGame.team.*;
import mainGame.equipment.*;
import mainGame.battle.Battle;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Battle Simulator!");

        // Create both teams
        Team team1 = createTeam(scanner, "Team 1");
        Team team2 = createTeam(scanner, "Team 2");

        // Display details for both teams
        System.out.println("\nTeam 1 Details:\n" + team1.getMembers());
        System.out.println("\nTeam 2 Details:\n" + team2.getMembers());

        // Prompt user to start the battle
        System.out.println("\nReady to start the battle? (yes/no)");
        String startBattle = scanner.nextLine().trim().toLowerCase();

        if (startBattle.equals("yes")) {
            // Begin battle between two teams
            Battle battle = new Battle(team1, team2);
            battle.startBattle();
        } else {
            System.out.println("Battle aborted.");
        }

        scanner.close();
    }

    // Method to create a team with user-defined members
    public static Team createTeam(Scanner scanner, String teamName) {
        System.out.println("Creating " + teamName + "...");
        ArrayList<Member> members = new ArrayList<>();

        System.out.print("Enter the number of members in " + teamName + ": ");
        int memberCount = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < memberCount; i++) {
            System.out.println("\nCreating Member " + (i + 1) + ":");

            // Gather member's name
            System.out.print("Enter member's name: ");
            String name = scanner.nextLine();

            // Define class details for the member
            System.out.print("Enter class name (e.g., Warrior, Mage, etc.): ");
            String className = scanner.nextLine();
            System.out.print("Enter class description: ");
            String classDescription = scanner.nextLine();
            System.out.print("Enter class strength buff: ");
            int classStrengthBuff = scanner.nextInt();
            System.out.print("Enter class defence buff: ");
            int classDefenceBuff = scanner.nextInt();
            System.out.print("Enter class intelligence buff: ");
            int classIntelligenceBuff = scanner.nextInt();
            scanner.nextLine(); 

            Class memberClass = new Class(className, classDescription, classStrengthBuff, classDefenceBuff, classIntelligenceBuff);

            // Define race details for the member
            System.out.print("Enter race name (e.g., Human, Elf, etc.): ");
            String raceName = scanner.nextLine();
            System.out.print("Enter race description: ");
            String raceDescription = scanner.nextLine();
            System.out.print("Enter race strength buff: ");
            int raceStrengthBuff = scanner.nextInt();
            System.out.print("Enter race defence buff: ");
            int raceDefenceBuff = scanner.nextInt();
            System.out.print("Enter race intelligence buff: ");
            int raceIntelligenceBuff = scanner.nextInt();
            scanner.nextLine(); 

            Race memberRace = new Race(raceName, raceDescription, raceStrengthBuff, raceDefenceBuff, raceIntelligenceBuff);

            // Select or define weapon details
            System.out.print("Enter weapon name (or press enter for default): ");
            String weaponName = scanner.nextLine();
            Weapon weapon;
            if (weaponName.isEmpty()) {
                weapon = new Weapon();
            } else {
                System.out.print("Enter weapon type: ");
                String weaponType = scanner.nextLine();
                System.out.print("Enter weapon damage roll (e.g., 6 for d6): ");
                int damageRoll = scanner.nextInt();
                System.out.print("Enter weapon strength buff: ");
                int weaponStrengthBuff = scanner.nextInt();
                scanner.nextLine(); 
                weapon = new Weapon(weaponName, weaponType, damageRoll, weaponStrengthBuff);
            }

            // Select or define armour details
            System.out.print("Enter armour name (or press enter for default): ");
            String armourName = scanner.nextLine();
            Armour armour;
            if (armourName.isEmpty()) {
                armour = new Armour();
            } else {
                System.out.print("Enter armour defence buff: ");
                int defenceBuff = scanner.nextInt();
                scanner.nextLine(); 
                armour = new Armour(armourName, defenceBuff);
            }

            // Select or define ring details
            System.out.print("Enter ring name (or press enter for default): ");
            String ringName = scanner.nextLine();
            Ring ring;
            if (ringName.isEmpty()) {
                ring = new Ring();
            } else {
                System.out.print("Enter ring intelligence buff: ");
                int intelligenceBuff = scanner.nextInt();
                scanner.nextLine(); 
                ring = new Ring(ringName, intelligenceBuff);
            }

            // Input health for the member and add them to the team
            System.out.print("Enter member's health: ");
            int health = scanner.nextInt();
            scanner.nextLine(); 

            Member member = new Member(name, health, memberRace, memberClass, armour, ring, weapon);
            members.add(member);
            System.out.println("Member " + (i + 1) + " added to " + teamName + ".");
        }

        return new Team(teamName, members, true);
    }
}
