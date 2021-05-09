public class Main {
  public static void main(String[] args) {
    int rounds = UI.getRoundInput();
    Game game = new Game(rounds);
    game.start();
  }
}
