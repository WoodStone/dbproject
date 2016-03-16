package dbproject.command;

import dbproject.command.core.CommandBase;
import dbproject.core.exercise.Exercise;
import dbproject.core.exercise.Stamina;
import dbproject.database.DBProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vestein on 16.03.2016.
 */
public class CommandTenLongest extends CommandBase {

  @Override
  public String getName() {
    return "tilengste";
  }

  @Override
  public String getUsage() {
    return null;
  }

  @Override
  public void processCommand(List<String> args) {
    List<Exercise> exercises = DBProxy.getTenLongestStaminaExercises();
    List<Stamina> staminaList = new ArrayList<>();

    for (Exercise exercise : exercises) {
      if (exercise instanceof Stamina) {
        staminaList.add((Stamina) exercise);
      }
    }

    System.out.println("10 lengste utholdenhetsøvelser:");
    for (Stamina stamina : staminaList) {
      System.out.println(String.format("  %d. %dkm %s", staminaList.indexOf(stamina) + 1, stamina.getLength(), stamina.NAME));
    }
  }

}
