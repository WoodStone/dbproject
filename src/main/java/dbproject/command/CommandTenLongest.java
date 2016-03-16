package dbproject.command;

import dbproject.command.core.CommandBase;
import dbproject.core.exercise.Exercise;
import dbproject.core.exercise.Stamina;
import dbproject.database.DBProxy;

import java.util.ArrayList;
import java.util.Comparator;
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
    List<Exercise> exercises = DBProxy.getExercises();
    List<Stamina> staminaList = new ArrayList<>();

    for (Exercise exercise : exercises) {
      if (exercise instanceof Stamina) {
        staminaList.add((Stamina) exercise);
      }
    }

    staminaList.sort(new Comparator<Stamina>() {
      @Override
      public int compare(Stamina o1, Stamina o2) {
        if (o1.getLength() < o2.getLength()) {
          return 1;
        } else if (o1.getLength() == o2.getLength()) {
          return 0;
        }
        return -1;
      }
    });

    staminaList = staminaList.subList(0, staminaList.size() < 10 ? staminaList.size() - 1 : 9);

    System.out.println("10 lengste utholdenhetsøvelser:");
    for (Stamina stamina : staminaList) {
      System.out.println(String.format("  %d. %dkm %s", staminaList.indexOf(stamina) + 1, stamina.getLength(), stamina.NAME));
    }
  }

}
