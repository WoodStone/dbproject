package dbproject.command;

import dbproject.Main;
import dbproject.command.core.CommandBase;
import dbproject.core.exercise.Exercise;
import dbproject.core.exercise.Stamina;
import dbproject.core.exercise.Strength;
import dbproject.core.workout.Indoor;
import dbproject.core.workout.Outdoor;
import dbproject.core.workout.Workout;
import dbproject.database.DBProxy;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Vestein on 15.03.2016.
 */
public class CommandExercise extends CommandBase {

  @Override
  public String getName() {
    return "ovelse";
  }

  @Override
  public String getUsage() {
    //TODO
    return null;
  }

  @Override
  public void processCommand(List<String> args) {

    Exercise exercise;
    String name;
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Navn på øvelse: ");
      String input = scanner.nextLine().trim();
      if (!DBProxy.containsExercise(input)) {
        name = input;
        break;
      }
      System.out.println("Øvelsen eksisterer allerede.");
    }

    while (true) {
      System.out.print("KondisjonStyrke eller Utholdenhet (k/u): ");
      String input = scanner.nextLine().toLowerCase().trim();
      if (input.equals("u")) {
        exercise = new Stamina(name);
        break;
      } else if (input.equals("k")) {
        exercise = new Strength(name);
        break;
      }
      System.out.println("Ugyldig input");
    }

    exercise.make();

    DBProxy.addExercise(exercise);

  }
}
