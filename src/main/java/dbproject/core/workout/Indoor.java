package dbproject.core.workout;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Vestein on 15.03.2016.
 */
public class Indoor extends Workout {

  private int spectators;

  public Indoor(String NAME) {
    super(NAME);
  }

  public void make() {
    super.make();
    setSpectators();
  }

  public int getSpectators() {
    return spectators;
  }

  public void setSpectators() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("Antall tilskuere: ");
      try {
        int input = scanner.nextInt();
        spectators = input;
        break;
      } catch (InputMismatchException e) {
        System.out.println("Ugyldig tall");
      }
    }
  }

}
