import java.util.ArrayList;

public class Game {
  private final int INITIAL_ROUNDS;
  private final Player[] PLAYERS = {new HumanPlayer(), new ComputerPlayer()};
  private ArrayList<RoundResult> results = new ArrayList<>();

  public Game(int rounds) {
    INITIAL_ROUNDS = rounds;
  }

  public void displayResult(int rounds) {
    UI.printMsg("Game ended after " + rounds + " rounds\nBest out of " + INITIAL_ROUNDS + "\n");
    for (int i = 0; i < results.size(); i++) {
      Player winner = results.get(i).getWinner();
      if (winner == null) {
        UI.printMsg("Round " + (i + 1) + " was a draw");
      } else {
        UI.printMsg("Round " + (i + 1) + " winner: " + winner);
      }
    }
    UI.printMsg("Overall winner: " + calcOverallWinner(results));
  }

  public void play() {
    Options[] roundResult = new Options[2];
    Player winner;
    for (int i = 0; i < roundResult.length; i++) {
      roundResult[i] = PLAYERS[i].takeTurn();
      UI.printMsg(PLAYERS[i] + " chose " + roundResult[i]);
    }
    winner = calcRoundWinner(roundResult, PLAYERS);
    results.add(new RoundResult(roundResult, winner));
  }

  public void start() {
    boolean gameOver = false;
    int rounds = INITIAL_ROUNDS;
    int roundCount = 0;
    UI.printMsg("Game starts");

    while (!gameOver) {
      if (roundCount == rounds) {
        gameOver = true;
      } else {
        play();
        if (results.get(roundCount).getWinner() == null) {
          rounds++;
        }
        if (!bothPlayersAbleToWin(INITIAL_ROUNDS, results)) {
          gameOver = true;
        }
        roundCount++;
      }
    }
    displayResult(roundCount);
  }

  private Player calcRoundWinner(Options[] choices, Player[] players) {
    if (choices[1] == Options.Paper && choices[0] == Options.Rock) {
      return players[1];
    } else if (choices[1] == Options.Paper && choices[0] == Options.Scissor) {
      return players[0];
    } else if (choices[1] == Options.Rock && choices[0] == Options.Scissor) {
      return players[1];
    } else if (choices[0] == Options.Paper && choices[1] == Options.Scissor) {
      return players[1];
    } else if (choices[0] == Options.Rock && choices[1] == Options.Scissor) {
      return players[0];
    } else if (choices[0] == Options.Paper && choices[1] == Options.Rock) {
      return players[0];
    } else {
      return null;
    }
  }

  private boolean bothPlayersAbleToWin(int totalRounds, ArrayList<RoundResult> roundResults) {
    int[] winsPrPlayer;

    if (totalRounds % 2 == 0) {
      totalRounds += 1;
    }

    winsPrPlayer = calcWinCount(roundResults);

    if ((winsPrPlayer[0] > totalRounds / 2) || (winsPrPlayer[1] > totalRounds / 2)) {
      return false;
    }
    return true;
  }

  private int[] calcWinCount(ArrayList<RoundResult> roundResults) {
    int[] winCount = new int[PLAYERS.length];
    Player winner;

    for (RoundResult result : roundResults) {
      winner = result.getWinner();
      if (winner == PLAYERS[0]) {
        winCount[0]++;
      } else if (winner == PLAYERS[1]) {
        winCount[1]++;
      }
    }

    return winCount;
  }


  private Player calcOverallWinner(ArrayList<RoundResult> roundResults){
    Player winner;
    int[] winsPrPlayer = calcWinCount(roundResults);

    if(winsPrPlayer[0] > winsPrPlayer[1]){
      winner = PLAYERS[0];
    }
    else{
      winner = PLAYERS[1];
    }
    return winner;
  }
}
