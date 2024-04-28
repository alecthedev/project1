import javax.swing.*;

public class Fighter {

    String name;
    int hitPoints = 0;
    String move;
    int movePower = 0;
    int attackSpeed = 0;

    public static Fighter generateAIFighter(){

        Fighter ai = new Fighter();

        String[] nameOptions = {
                "Tombstone","Son of Whyachi","Minotaur","Huge","Hydra",
                "Captain Shred","Lock-Jaw","Gigabyte","Death Roll","Bronco"};

        String[] moveOptions = {
                "Vertical spinner", "Horizontal Spinner",
                "Drum Spinner", "Flipper", "Shell Spinner"};

        ai.name = nameOptions[Referee.selectANumber(0, 9)];
        ai.hitPoints = Referee.selectANumber(25, 100);
        ai.move = moveOptions[Referee.selectANumber(0, 4)];
        ai.movePower = Referee.selectANumber(10, 50);
        ai.attackSpeed = Referee.selectANumber(25, 100);

        return ai;

    }

    public void attack(Fighter defender) {

        defender.hitPoints -= this.movePower;

        if(defender.hitPoints < 0){
            defender.hitPoints = 0;

        }
        JOptionPane.showMessageDialog(
                null,
                String.format("""
                        %s uses %s against %s.
                                    
                        It does %d damage!
                                    
                        %s has %d health remaining.
                                    
                        """,
                        this.name, this.move, defender.name,
                        this.movePower, defender.name, defender.hitPoints),
                String.format("Round %d of %d", (Referee.round), Referee.roundTotal),
                JOptionPane.PLAIN_MESSAGE,
                null);

    } public void statAssignment(String player){

        this.name = JOptionPane.showInputDialog(
                null,
                String.format("%s:\nPlease enter your fighter's name.", player),
                String.format("%s's Fighter Creation - Round %d", player, Referee.round),
                JOptionPane.PLAIN_MESSAGE);

        this.hitPoints = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                String.format("%s:\nPlease enter your fighter's total health.", player),
                String.format("%s's Fighter Creation - Round %d", player, Referee.round),
                JOptionPane.PLAIN_MESSAGE));

        this.move = JOptionPane.showInputDialog(
                null,
                String.format("%s:\nPlease enter the name of your fighter's move.", player),
                String.format("%s's Fighter Creation - Round %d", player, Referee.round),
                JOptionPane.PLAIN_MESSAGE);

        this.movePower = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                String.format("%s:\nPlease enter this move's power.", player),
                String.format("%s's Fighter Creation - Round %d", player, Referee.round),
                JOptionPane.PLAIN_MESSAGE));

        this.attackSpeed = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                String.format("%s:\nPlease enter your fighter's attack speed.", player),
                String.format("%s's Fighter Creation - Round %d", player, Referee.round),
                JOptionPane.PLAIN_MESSAGE));

        if (this.hitPoints <= 0 || this.movePower <= 0 || this.attackSpeed <= 0){
            JOptionPane.showMessageDialog(
                    null,
                    """
                        Please only enter values greater than 0 for your fighter's stats.
                        
                        Anything else breaks the game mechanics.
                                    
                        """,
                    String.format("Error in Stat Entry for %s", this.name),
                    JOptionPane.PLAIN_MESSAGE,
                    null);

            CharacterBattle.characterCreation();

        }

    }
}
