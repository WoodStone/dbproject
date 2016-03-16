package dbproject.core.workout;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Vestein on 15.03.2016.
 */
public class Outdoor extends Workout {

  private String weather;
  private int temp;

  public Outdoor(String NAME) {
    super(NAME);
  }

  public void make() {
    super.make();
    setWeather();
    setTemp();
  }

  public String getWeather() {
    return weather;
  }

  public void setWeather() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("Vær: ");
      try {
        String input = scanner.nextLine();
        weather = input;
        break;
      } catch (InputMismatchException e) {
        System.out.println("Ugyldig input");
      }
    }
  }

  public int getTemp() {
    return temp;
  }

  public void setTemp() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("Temperatur: ");
      try {
        int input = scanner.nextInt();
        temp = input;
        break;
      } catch (InputMismatchException e) {
        System.out.println("Ugyldig temperatur");
      }
    }
  }
}
