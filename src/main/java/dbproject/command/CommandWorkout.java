package dbproject.command;

import dbproject.Main;
import dbproject.command.core.CommandBase;
import dbproject.core.workout.Indoor;
import dbproject.core.workout.Outdoor;
import dbproject.core.workout.Workout;
import dbproject.database.DBProxy;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Vestein on 15.03.2016.
 */
public class CommandWorkout extends CommandBase {

  @Override
  public String getName() {
    return "trening";
  }

  @Override
  public String getUsage() {
    //TODO
    return null;
  }

  @Override
  public void processCommand(List<String> args) {

    Workout workout;
    String name;
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Øktnavn: ");
      String input = scanner.nextLine().trim();
      if (!DBProxy.containsWorkout(input)) {
        name = input;
        break;
      }
      System.out.println("Øktnavn eksisterer allerede.");
    }

    while (true) {
      System.out.print("Utendørs eller inne (i/u): ");
      String input = scanner.nextLine().toLowerCase().trim();
      if (input.equals("u")) {
        workout = new Outdoor(name);
        break;
      } else if (input.equals("i")) {
        workout = new Indoor(name);
        break;
      }
      System.out.println("Ugyldig input");
    }

    workout.make();

    DBProxy.addWorkout(workout, Main.user);

    workout.addExercises();

  }
}
