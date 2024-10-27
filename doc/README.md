# Battle Game

## Problem Statement

The Battle Game is designed to provide an engaging and interactive experience where players can create teams of characters, each with unique equipment. The game simulates battles between these teams. It solves the problem of creating a dynamic combat system that combines character creation, turn-based mechanics, and random events to enhance gameplay.

## Program Structure

The application is structured into several key components:

- **mainGame.battle**: Contains classes responsible for managing battles, including the `Battle` class for handling the battle logic, `DiceRoll` for simulating dice rolls, and `Effect` for managing buffs and debuffs.
- **mainGame.equipment**: Contains classes such as `Weapon`, `Armour`, and `Ring` that define the equipment available to characters, including their attributes and effects on gameplay.

- **mainGame.member**: Includes classes for managing character statistics and attributes (e.g., `Class`, `Race`, and `Stats`), allowing for custom character creation.

- **mainGame.team**: Manages collections of `Member` objects, representing a player's team in the battle.

- **Main File**: Handles character creation, allowing players to choose their character's stats and equipment through the terminal.

- **test**: Contains various tests to test the functionality of each class.

- **doc**: Contains documentation such as `README.md` and `gameUML` (in the form of a drawio document and png)

## How to Run the Program

To run the Battle Game, follow these instructions:

1. **Download the Repository**:
   Download the `majorAssignmentTopic1.zip` provided.

2. **Extract the Provided Zip File**:
   Extract `majorAssignmentTopic1.zip` into a preferred location.

3. **Open the Extracted Folder in an IDE (e.g., VS Code)**:
   Open the folder in an IDE such as VS Code.

4. **Read documentation and look at UML digrams in `src/test`**
   Navigate to this location to look at the README.md, as well as the UML diagram.
   - NOTE: I reccomend accessing the `gameUML.drawio`, as it looks better in VsCode, otherwise open the PNG (do this in file explorer as vscode pngs will appear transparent in VSCode). The UML diagram in written form is also below.
     - You can install the drawio extension(or find it) via the information:
       "Name: Draw.io Integration
       Id: hediet.vscode-drawio
       Description: This unofficial extension integrates Draw.io into VS Code.
       Version: 1.6.6
       Publisher: Henning Dieterichs
       VS Marketplace Link: https://marketplace.visualstudio.com/items?itemName=hediet.vscode-drawio"
5. **Open `src/mainGame/Main.java`**:
   Navigate to this location and click on the `Run` button to launch the game in the terminal.

Optional: **Open `src/test/`**:

- You can open any of the provided Java files in this location to test the functionality of each class. This also helps you understand the mechanics of the game better.

## UML

[Battle | +team1 : Team; +team2 : Team; +random : Random; +roundNumber : int; +maxRounds : int | +Battle(team1 : Team, team2 : Team); +startBattle() : void; +announceBattleStart(team1Starts : boolean) : void; +startRound(team1Starts : boolean) : void; +concludeBattle() : void; +takeTurn(attackingTeam : Team, defendingTeam : Team) : void; +executeMemberAction(attacker : Member, willDefend : boolean, defendingTeam : Team) : void; +selectTarget(defendingTeam : Team) : Member; +executeAttack(attacker : Member, target : Member) : void; +calculateDamage(attacker : Member, target : Member) : int; +resetDefenseStatus(team : Team) : void; +team1Starts() : boolean]

[DiceRoll |  | +roll(sides : int) : int]

[Effect | +name : String; +description : String; +type : String; +strengthChange : int; +defenceChange : int; +intelligenceChange : int; +duration : int; +isActive : boolean; +originalStats : Stats | +Effect(name : String, description : String, type : String, strengthChange : int, defenceChange : int, intelligenceChange : int, duration : int); +apply(targetStats : Stats) : void; +isActive() : boolean; +getName() : String; +getDescription() : String; +getType() : String; +getStrengthChange() : int; +getDefenceChange() : int; +getIntelligenceChange() : int]

[Armour | +armourName : String; +defenceBuff : int | +Armour(); +Armour(name : String, buff : int); +getName() : String; +getDefenceBuff() : int; +setDefenceBuff(n : int) : int; +toString() : String]

[Ring | +ringName : String; +intelligenceBuff : int | +Ring(); +Ring(name : String, buff : int); +getName() : String; +getIntelligenceBuff() : int; +setIntelligenceBuff(n : int) : int; +toString() : String]

[Weapon | +weaponName : String; +weaponType : String; +damageRoll : int; +strengthBuff : int | +Weapon(); +Weapon(name : String, type : String, roll : int, buff : int); +getName() : String; +rollDamage() : int; +getStrengthBuff() : int; +setStrengthBuff(n : int) : int; +toString() : String]

[Class | +className : String; +classDescription : String; +strengthBuff : int; +defenceBuff : int; +intelligenceBuff : int | +Class(name : String, description : String, strength : int, defence : int, intelligence : int); +getClassName() : String; +getClassDescription() : String; +getStrengthBuff() : int; +getDefenceBuff() : int; +getIntelligenceBuff() : int; +toString() : String]

[Member | +memberName : String; +health : int; +memberRace : Race; +memberClass : Class; +baseStats : Stats; +stats : Stats; +armour : Armour; +ring : Ring; +weapon : Weapon; +defending : boolean | +Member(memberName : String, health : int, memberRace : Race, memberClass : Class, armour : Armour, ring : Ring, weapon : Weapon); +getName() : String; +getStrength() : int; +getDefence() : int; +getIntelligence() : int; +getHealth() : int; +getBaseStats() : Stats; +getCurrentStats() : Stats; +getWeaponName() : String; +getWeaponDamage() : int; +isAlive() : boolean; +isDefending() : boolean; +setDefending(defending : boolean); +takeDamage(damage : int); +toString() : String]

[Race | +raceName : String; +raceDescription : String; +strengthBuff : int; +defenceBuff : int; +intelligenceBuff : int | +Race(name : String, description : String, strength : int, defence : int, intelligence : int); +getRaceName() : String; +getRaceDescription() : String; +getStrengthBuff() : int; +getDefenceBuff() : int; +getIntelligenceBuff() : int; +toString() : String]

[Stats | +strength : int; +intelligence : int; +defence : int | +Stats(); +Stats(strength : int, intelligence : int, defence : int); +getStrength() : int; +setStrength(strength : int) : void; +getIntelligence() : int; +setIntelligence(intelligence : int) : void; +getDefence() : int; +setDefence(defence : int) : void; +updateStats(strength : int, defence : int, intelligence : int) : void; +createSnapshot() : Stats; +toString() : String]

[Team | +teamName : String; +teamMembers : ArrayList<Member>; +isTeamAlive : boolean | +Team(teamName : String, teamMembers : ArrayList<Member>, isTeamAlive : boolean); +addMember(newMember : Member) : void; +getTeamSize() : int; +isTeamAlive() : boolean; +checkAlive(index : int) : boolean; +getMember(slot : int) : Member; +getTeamName() : String; +getMembers() : String; +totalTeamIntelligence(team : Team) : int; +toString() : String]

[StatCalculator |  | +applyBuffs(stats : Stats, memberClass : Class, memberRace : Race, armour : Armour, ring : Ring, weapon : Weapon) : void]

## Design Process, Task Allocation, and Contribution Percentage:

When designing our project we decided on a 1.5 hour brainstorming session for a couple weeks where the three of us came together to map out the classes, methods, and the overall setup and how it would operate. It allowed us to share ideas about what each of us want to do, allowing us to design the assignment around that in mind. 

After we had an outline, we talked about how we would divide the work in a way that felt fair and balanced. In the end we decided on a way where everyone would have an equal share of the workload. By doing this it allowed us all to be organised, as we were assigned areas where we felt stronger in.

For the contribution percentage:

    Jed Michaels: 33%
    Noah Pollack: 33%
    Eric Smallwood: 33%

This means that each member contributed roughly one-third to the project.

