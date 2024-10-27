package mainGame.battle;

import java.util.Random;
import mainGame.team.*;
import mainGame.member.*;

public class Battle {
    Team team1;
    Team team2;
    Random random;
    int roundNumber;
    int maxRounds = 1000;

    /**
     * Constructor initialises the battle with two teams, a random generator,
     * and sets the initial round to 1.
     * 
     * @param team1 The first team participating in the battle.
     * @param team2 The second team participating in the battle.
     */
    public Battle(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.random = new Random();
        this.roundNumber = 1;
    }

    /**
     * Begins the battle, alternating turns between the two teams until one team
     * is fully defeated or the maximum round limit is reached. Displays battle
     * outcome at the end.
     */
    public void startBattle() {
        boolean team1Starts = team1Starts();
        announceBattleStart(team1Starts);
        
        while (team1.isTeamAlive() && team2.isTeamAlive() && roundNumber <= maxRounds) {
            System.out.println("\n--- ROUND " + roundNumber + " ---");
            startRound(team1Starts); // Alternates team turns each round
            team1Starts = !team1Starts;
            roundNumber++;
            resetDefenseStatus(team1); // Reset defense status for team1 members
            resetDefenseStatus(team2); // Reset defense status for team2 members
        }

        concludeBattle();
    }

    /**
     * Announces the start of the battle and displays which team goes first.
     *
     * @param team1Starts True if team1 goes first, false if team2 goes first.
     */
    public void announceBattleStart(boolean team1Starts) {
        System.out.println("====== BATTLE BEGINS ======");
        System.out.println("Starting team: " + (team1Starts ? team1.getTeamName() : team2.getTeamName()));
    }

    /**
     * Executes a single round, allowing each team to take a turn in an
     * alternating order.
     *
     * @param team1Starts True if team1 should take the first turn, false if team2.
     */
    public void startRound(boolean team1Starts) {
        if (team1Starts) {
            if (team1.isTeamAlive()) takeTurn(team1, team2); // Team1 attacks first
            if (team2.isTeamAlive()) takeTurn(team2, team1); // Then team2 attacks
        } else {
            if (team2.isTeamAlive()) takeTurn(team2, team1); // Team2 attacks first
            if (team1.isTeamAlive()) takeTurn(team1, team2); // Then team1 attacks
        }
    }

    /**
     * Concludes the battle and announces the result based on the remaining
     * alive status of the teams or if the round limit was reached.
     */
    public void concludeBattle() {
        System.out.println("====== BATTLE ENDS ======");
        if (roundNumber > maxRounds) {
            System.out.println("The battle reached the maximum round limit and ends in a draw.");
        } else if (team1.isTeamAlive()) {
            System.out.println(team1.getTeamName() + " emerges victorious!");
        } else {
            System.out.println(team2.getTeamName() + " emerges victorious!");
        }
    }

    /**
     * Executes a turn for each member of the attacking team, deciding
     * whether each member attacks or defends.
     *
     * @param attackingTeam The team taking the current turn.
     * @param defendingTeam The team being attacked during this turn.
     */
    public void takeTurn(Team attackingTeam, Team defendingTeam) {
        for (int i = 0; i < attackingTeam.getTeamSize(); i++) {
            Member attacker = attackingTeam.getMember(i);
            if (attacker.isAlive()) { // Ensure only alive members take actions
                boolean willDefend = random.nextBoolean(); // Randomly decide action
                executeMemberAction(attacker, willDefend, defendingTeam);
            }
        }
    }

    /**
     * Determines and executes an action for a member, either setting
     * them to defend or initiating an attack on a defending team member.
     *
     * @param attacker The attacking member.
     * @param willDefend True if the attacker will defend, false to attack.
     * @param defendingTeam The team to attack if willDefend is false.
     */
    public void executeMemberAction(Member attacker, boolean willDefend, Team defendingTeam) {
        attacker.setDefending(willDefend); // Set defending status
        if (willDefend) {
            System.out.println(attacker.getName() + " chooses to defend!");
        } else {
            Member target = selectTarget(defendingTeam); // Find a target to attack
            if (target != null) {
                executeAttack(attacker, target);
            }
        }
    }

    /**
     * Selects a random target from the defending team, ensuring
     * the target is alive before attacking.
     *
     * @param defendingTeam The team being attacked.
     * @return A valid target member from the defending team.
     */
    public Member selectTarget(Team defendingTeam) {
        Member target = null;
        for (int j = 0; j < defendingTeam.getTeamSize(); j++) {
            Member potentialTarget = defendingTeam.getMember(random.nextInt(defendingTeam.getTeamSize()));
            if (potentialTarget.isAlive()) { // Only select alive members
                target = potentialTarget;
                break;
            }
        }
        return target;
    }

    /**
     * Executes an attack on the selected target, calculating damage
     * and applying it if the target is not defending.
     *
     * @param attacker The member attacking.
     * @param target The member being attacked.
     */
    public void executeAttack(Member attacker, Member target) {
        int damage = calculateDamage(attacker, target);
        System.out.println(attacker.getName() + " attempts to strike " + target.getName() +
                " for " + damage + " damage! (" + attacker.getWeaponName() + ")");
        
        if (target.isDefending()) {
            System.out.println("-> " + target.getName() + " defended and took no damage!");
        } else {
            target.takeDamage(damage);
            System.out.println("-> " + target.getName() + " took " + damage + " damage!");
            if (!target.isAlive()) {
                System.out.println("-> " + target.getName() + " has been defeated!");
            }
        }
    }

    /**
     * Calculates the damage dealt by an attacker to a target based on
     * strength, weapon damage, and current round number.
     *
     * @param attacker The member dealing damage.
     * @param target The member receiving damage.
     * @return The total calculated damage.
     */
    public int calculateDamage(Member attacker, Member target) {
        int baseDamage = attacker.getStrength() - target.getDefence();
        int weaponDamage = attacker.getWeaponDamage();
        int damage = baseDamage + weaponDamage + (roundNumber / 10); // Boost as rounds increase
        return Math.max(damage, 0); // Ensure non-negative damage
    }

    /**
     * Resets the defending status for all members of a team at the end of each round.
     *
     * @param team The team whose members' defense statuses will be reset.
     */
    public void resetDefenseStatus(Team team) {
        for (int i = 0; i < team.getTeamSize(); i++) {
            team.getMember(i).setDefending(false); // Reset defending status
        }
    }

    /**
     * Determines which team starts the battle based on the total intelligence
     * score of each team.
     *
     * @return True if team1 starts, false if team2 starts.
     */
    public boolean team1Starts() {
        return team1.totalTeamIntelligence(team1) >= team2.totalTeamIntelligence(team1);
    }
}
