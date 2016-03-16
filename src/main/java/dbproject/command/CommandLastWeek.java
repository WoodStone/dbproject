package dbproject.command;

import dbproject.command.core.CommandBase;
import dbproject.core.workout.Workout;
import dbproject.database.DBProxy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vestein on 15.03.2016.
 */
public class CommandLastWeek extends CommandBase {

  @Override
  public String getName() {
    return "sistuke";
  }

  @Override
  public String getUsage() {
    return null;
  }

  @Override
  public void processCommand(List<String> args) {
    List<Workout> workouts = DBProxy.getLastWeekWorkouts();
    int nDifferentExercises = 0;
    int nExercises = 0;
    int nHours = 0;
    int nWorkouts = workouts.size();
    Set<String> uniqueExercises = new HashSet<>();

    for (Workout workout : workouts) {
      nExercises += workout.getExercises().size();
      uniqueExercises.addAll(workout.getExercises());
      nHours += workout.getDuration();
    }
    nDifferentExercises = uniqueExercises.size();

    System.out.println("Sammendrag for uken som var:");
    System.out.println("  Økter:          " + nWorkouts);
    System.out.println("  Øvelser:        " + nExercises);
    System.out.println("  Unike øvelser:  " + nDifferentExercises);
    System.out.println("  Minutt:         " + nHours);
  }

}
