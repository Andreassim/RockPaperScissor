public class HumanPlayer implements Player{

  @Override
  public Options takeTurn() {
    UI.showOptions();
    UI.printMsg("Take your pick: ");
    int choice = UI.getIntInput();
    return Options.values()[choice-1];
  }

  @Override
  public String toString(){
    return "You";
  }

}
