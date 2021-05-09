import java.util.Scanner;

public class UI {
  private static Scanner scanner = new Scanner(System.in);

  public static void showOptions() {
    for (int i = 0; i < Options.values().length; i++) {
      System.out.printf("%s.\t%s\n", i + 1, Options.values()[i]);
    }
  }

  public static int getRoundInput() {
    boolean valid = false;
    int rounds = 0;
    printMsg("Best out of: ");
    while (!valid) {
      if (scanner.hasNextInt()) {
        rounds = scanner.nextInt();
        scanner.nextLine();
        if (rounds % 2 == 0) {
          printMsg("Cant be divisable by 2\nTry again:");
        } else {
          valid = true;
        }
      }
    }
    return rounds;
  }

  public static void printMsg(String msg) {
    System.out.println(msg);
  }

  public static int getIntInput() {
    boolean validInput = false;
    int choice = 0;
    while (!validInput) {
      choice = scanner.nextInt();
      if (choice < 1 || choice > Options.values().length) {
        System.out.print("Try again:");
      } else {
        validInput = true;
      }
    }
    return choice;
  }
}
