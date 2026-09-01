import java.util.Random;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] samplePlayerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"}; // Predefined moves for 5 rounds[cite: 1]
        
        Random random = new Random();
        
        int rounds = 5;
        int wins = 0, losses = 0, draws = 0;

        String[] playerMovesHistory = new String[rounds];
        String[] computerMovesHistory = new String[rounds];
        String[] resultsHistory = new String[rounds];

        for (int i = 0; i < rounds; i++) {
            String playerMove = samplePlayerMoves[i];
            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            playerMovesHistory[i] = playerMove;
            computerMovesHistory[i] = computerMove;
            resultsHistory[i] = result;

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;
        }

        // Summary Table
        System.out.printf("%-8s | %-12s | %-14s | %-13s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < rounds; i++) {
            System.out.printf("%-8d | %-12s | %-14s | %-13s%n", 
                              (i + 1), playerMovesHistory[i], computerMovesHistory[i], resultsHistory[i]);
        }

        double winPercentage = ((double) wins / rounds) * 100;
        System.out.println("\nFinal Summary");
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
    }
}