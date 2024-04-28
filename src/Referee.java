import javax.swing.*;

public class Referee {

    static int round, roundTotal, playerCount, player1Score, player2Score;

    public static int selectANumber(int min, int max){
        return (int)(Math.random() * ((max + 1) - min) + min);

    }

    public static void roundChoice() {

        roundTotal = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Enter an odd number of rounds to fight: ",
                "Set number of rounds",
                JOptionPane.PLAIN_MESSAGE));

        if (roundTotal % 2 == 0 || roundTotal <= 0) {
            JOptionPane.showMessageDialog(
                    null,
                    """ 
                    Invalid input.
                    
                    Please enter an odd number that is greater than 0.
                    
                    """,
                    "Restarting program...",
                    JOptionPane.PLAIN_MESSAGE,
                    null);
            roundChoice();

        }
    }

    public static String compareSpeed(Fighter fighter1, Fighter fighter2){

        boolean coinToss = false;
        String decision;

        if (fighter1.attackSpeed == fighter2.attackSpeed){
            coinToss = true;

            if (selectANumber(0,1) == 0) {
                decision = fighter1.name;

            } else {
                decision = fighter2.name;

            }

        } else if (fighter1.attackSpeed > fighter2.attackSpeed) {
            decision = fighter1.name;

        } else {
            decision = fighter2.name;

        }

        if (coinToss){
            JOptionPane.showMessageDialog(
                    null,
                    String.format("""
                                Looks like %s and %s are the same speed.
                                
                                The referee has called for a coin toss and...
                                
                                %s is going first!
                                
                                """,
                            fighter1.name,fighter2.name,decision),
                    "Coin Toss!",
                    JOptionPane.PLAIN_MESSAGE,
                    null);

        }else {
            JOptionPane.showMessageDialog(
                    null,
                    String.format("Looks like %s is faster, so they get to go first.",
                            decision),
                    "Comparing Speeds",
                    JOptionPane.PLAIN_MESSAGE,
                    null);

        }
        return decision;

    }

    public static boolean checkForKO(Fighter fighter){
        return fighter.hitPoints <= 0;

    }

    public static void results() {

        String winner;
        int choice;

        String[] options = {"Another battle!", "Exit"};

        if (player1Score > player2Score){
            winner = CharacterBattle.player1;

        } else {
            winner = CharacterBattle.player2;

        }
        choice = JOptionPane.showOptionDialog(
                null,
                String.format("""
                            %s's final score: %d
                            %s's final score: %d
                            
                            %s is the winner!
                            
                            """,
                        CharacterBattle.player1, player1Score,
                        CharacterBattle.player2, player2Score, winner),
                "Character Battle Complete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,options,
                null);

        if(choice == 0){
            fullReset();

        }else{
            JOptionPane.showMessageDialog(
                    null,
                    "Thanks for playing :)",
                    "Goodbye!",
                    JOptionPane.PLAIN_MESSAGE,
                    null);
            System.exit(0);

        }
    }

    public static void fullReset(){

        round = 1;
        roundTotal = 0;
        playerCount = 0;
        player1Score = 0;
        player2Score = 0;
        CharacterBattle.player1 = "Player 1";
        CharacterBattle.player2 = "Player 2";

        try{
            CharacterBattle.welcome();

        } catch (NumberFormatException invalidEntry){
            handleFormatException();

        }
    }

    public static void roundWinner(String player, String fighter){

        JOptionPane.showMessageDialog(
                null,
                String.format("Nice job %s! %s won that round. ",
                        player, fighter),
                String.format("Round %d Complete", (round)),
                JOptionPane.PLAIN_MESSAGE,
                null);

    }

    public static void handleFormatException(){

        JOptionPane.showMessageDialog(
                null,
                """
                        Invalid input.
                        
                        One of the following must've occurred:
                        
                         - A non-integer was entered when only integers were allowed
                         - Input was too large of a number (We all have our limits)
                         - A required field was left blank (Math with null is quite dull)

                        Let's start over.
                        
                        """,
                "Restarting program...",
                JOptionPane.PLAIN_MESSAGE,
                null);

        fullReset();

    }

}