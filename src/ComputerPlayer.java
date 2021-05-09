import java.util.Random;

public class ComputerPlayer implements Player {
  Random random = new Random();
  @Override
  public Options takeTurn() {
    int choice = random.nextInt(3);
    return Options.values()[choice];
  }

  @Override
  public String toString(){
    return "Computer";
  }

}
