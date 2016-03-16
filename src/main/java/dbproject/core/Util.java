package dbproject.core;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Vestein on 15.03.2016.
 */
public class Util {

  public static int getIntInput(String prompt, String error) {
    int input;
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print(prompt + ": ");
      try {
        input = scanner.nextInt();
        break;
      } catch (InputMismatchException e) {
        System.out.println(error);
      }
    }
    return input;
  }

  public static String getStringInput(String prompt, String error) {
    return getStringInput(prompt, error, false);
  }

  public static String getStringInput(String prompt, String error, boolean caseSensetive) {
    String input;
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print(prompt + ": ");
      try {
        input = scanner.nextLine().trim();
        if (!caseSensetive) {
          input = input.toLowerCase();
        }
        break;
      } catch (InputMismatchException e) {
        System.out.println(error);
      }
    }
    return input;
  }

}
