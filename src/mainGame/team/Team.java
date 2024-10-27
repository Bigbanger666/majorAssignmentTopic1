package mainGame.team;

import java.util.ArrayList;
import mainGame.member.*;

public class Team {
    String teamName; // Name of the team
    ArrayList<Member> teamMembers; // List of team members
    boolean isTeamAlive; // Indicates if the team is still alive

    // Constructor to initialise team with a name, list of members, and alive status
    public Team(String teamName, ArrayList<Member> teamMembers, boolean isTeamAlive) {
        this.teamName = teamName;

        // Initialise teamMembers with empty list if null is provided
        if (teamMembers == null) {
            this.teamMembers = new ArrayList<Member>();
        } else {
            this.teamMembers = teamMembers;
        }

        this.isTeamAlive = isTeamAlive;
    }

    // Adds a new member to the team
    public void addMember(Member newMember) {
        teamMembers.add(newMember);
    }

    // Returns the size of the team (number of members)
    public int getTeamSize() {
        return teamMembers.size();
    }

    // Checks if the team has at least one member alive
    public boolean isTeamAlive() {
        return checkAlive(0); // Begin recursive check from the first member
    }

    // Recursive method to check if any member in the team is alive
    public boolean checkAlive(int index) {
        if (index >= teamMembers.size()) {
            return false; // All members checked and none are alive
        }
        if (teamMembers.get(index).isAlive()) {
            return true; // Found at least one member alive
        }
        return checkAlive(index + 1); // Continue checking the next member
    }

    // Retrieves a team member based on a given slot (index)
    public Member getMember(int slot) {
        if (slot >= teamMembers.size() || slot < 0) {
            System.out.println("Invalid member index: " + slot);
            return null; // Return null if the slot is out of bounds
        }
        return teamMembers.get(slot);
    }

    // Returns the team name
    public String getTeamName() {
        return teamName;
    }

    // Retrieves information about all team members in a formatted string
    public String getMembers() {
        String membersInfo = ""; // String to store member details
        for (Member member : teamMembers) {
            membersInfo += member.toString() + "\n"; 
        }
        return membersInfo;
    }

    /*
     * Calculates the total intelligence of the team (combined), this is helpful in
     * deciding who starts.
     */

    public int totalTeamIntelligence(Team team) {
        int totalIntelligence = 0;
        for (int i = 0; i < team.getTeamSize(); i++) {
            totalIntelligence += team.getMember(i).getIntelligence();
        }
        return totalIntelligence;
    }

    @Override
    public String toString() {
        return "Team Name: " + teamName + "\nTeam Size: " + getTeamSize() + "\nIs Team Alive: " + isTeamAlive;
    }
}
