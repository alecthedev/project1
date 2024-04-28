import javax.swing.*;

public class CharacterBattle {

    static Fighter fighter1 = new Fighter();
    static Fighter fighter2 = new Fighter();

    static String player1 = "Player 1";
    static String player2 = "Player 2";

    public static void main(String[] args) {
        try{
            welcome();

        } catch (NumberFormatException invalidEntry){
            Referee.handleFormatException();

        }
    }

    public static void welcome(){

        String[] options = {"Let's Fight!", "Battle for me?", "I'm too scared!"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "Welcome, and get ready to battle!",
                "Character Battle",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, options,
                null);

        switch(choice){
            case 0:
                playerSelection();
                break;

            case 1:
                Referee.roundChoice();
                battle();
                break;

            case 2:
                JOptionPane.showMessageDialog(
                        null,
                        "That's okay, come back when you're ready!",
                        "Exiting program...",
                        JOptionPane.PLAIN_MESSAGE,
                        null);

        }
    }

    private static void playerSelection(){

        String[] playerOption = {"1 Player", "2 Players"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "How many people are playing?",
                "Character Battle",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, playerOption,
                null);

        if(choice == 0 ){
            Referee.playerCount = 1;
            player1 = playerNameAssignment(player1);

        } else {
            Referee.playerCount = 2;
            player1 = playerNameAssignment(player1);
            player2 = playerNameAssignment(player2);

        }
        Referee.roundChoice();
        battle();

    }

    private static String playerNameAssignment(String player){

        return JOptionPane.showInputDialog(
                null,
                String.format("%s!\nPlease enter your name.", player),
                String.format("%s Name Entry", player),
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void characterCreation() {

        if (Referee.playerCount == 1 || Referee.playerCount == 2) {
            fighter1.statAssignment(player1);

            if (Referee.playerCount == 1) {
                fighter2 = Fighter.generateAIFighter();

            } else {
                fighter2.statAssignment(player2);

            }

        } else {
            fighter1 = Fighter.generateAIFighter();
            fighter2 = Fighter.generateAIFighter();

        }
    }

    private static void battle(){

        for(Referee.round = 1; Referee.round <= Referee.roundTotal; Referee.round++){
            boolean fightOngoing = true;
            characterCreation();

            JOptionPane.showMessageDialog(
                    null,
                    String.format("""
                            %s's fighter is %s.
                            
                            %s's fighter is %s.
                            
                            """,
                            player1, fighter1.name, player2, fighter2.name),
                    String.format("Round %d Participants",(Referee.round)),
                    JOptionPane.PLAIN_MESSAGE,
                    null);

            String turn = Referee.compareSpeed(fighter1, fighter2);

            while(fightOngoing) {

                if (turn.equals(fighter1.name)) {
                    fighter1.attack(fighter2);

                    if (Referee.checkForKO(fighter2)) {
                        Referee.player1Score += 1;
                        Referee.roundWinner(player1,fighter1.name);
                        fightOngoing = false;

                    } else {
                        turn = fighter2.name;

                    }

                } else {
                    fighter2.attack(fighter1);

                    if (Referee.checkForKO(fighter1)) {
                        Referee.player2Score += 1;
                        Referee.roundWinner(player2, fighter2.name);
                        fightOngoing = false;

                    } else {
                        turn = fighter1.name;

                    }
                }
            }
        }
        Referee.results();

    }

}