public class RoundResult {
  private Options player2Choice;
  private Options player1Choice;
  private Player winner;

  public RoundResult(Options[] choices, Player winner) {
    player1Choice = choices[0];
    player2Choice = choices[1];
    this.winner = winner;
  }


  public Player getWinner() {
    return winner;
  }
}
