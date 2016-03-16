package dbproject.core.workout;

import dbproject.core.Util;
import dbproject.core.exercise.Exercise;
import dbproject.core.exercise.Stamina;
import dbproject.core.exercise.Strength;
import dbproject.database.DBProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vestein on 15.03.2016.
 */
public class Workout {

  public final String NAME;

  private int date;
  private int time;
  private int duration;
  private List<String> exercises = new ArrayList<>();

  public Workout(String NAME) {
    this.NAME = NAME;
  }

  public Workout(String NAME, int date, int time, int duration, List<String> exercises) {
    this(NAME);
    this.date = date;
    this.time = time;
    this.duration = duration;
    this.exercises = exercises;
  }

  public void make() {
    setDate();
    setTime();
    setDuration();
  }

  public void addExercises() {
    while (true) {
      String resp = Util.getStringInput("Legge til øvelser? (y/n)", "Ugyldig input");
      if (resp.equals("y")) {

        List<Stamina> staminaExercises = DBProxy.getStaminaExercises();
        List<Strength> strengthExercises = DBProxy.getStrenghtExercises();

        int modifier = staminaExercises.size() > 0 ? 1 : 0;

        System.out.println("Utholdenhetsøvelser:");
        for (Exercise exercise : staminaExercises) {
          System.out.print(String.format("%d: %s, ", staminaExercises.indexOf(exercise), exercise.NAME));
          if (staminaExercises.indexOf(exercise) % 5 == 0) {
            System.out.println();
          }
        }
        System.out.println("Styrkeøvelser:");
        for (Exercise exercise : strengthExercises) {
          System.out.print(String.format("%d: %s, ", strengthExercises.indexOf(exercise) + staminaExercises.size(), exercise.NAME));
          if (staminaExercises.indexOf(exercise) + 1 % 5 == 0) {
            System.out.println();
          }
        }
        System.out.println();

        while (true) {
          String input = Util.getStringInput("Legg til øvelser (0,2,4,9), 'q' for å avbryte", "Ugyldig input").replace(" ", "");
          if (input.equals("q")) {
            break;
          }
          String[] list = input.split(",");
          List<Integer> added = new ArrayList<>();

          for (String s : list) {
            try {
              int index = Integer.parseInt(s);
              added.add(index);

              if (index < staminaExercises.size()) {
                DBProxy.addExerciseToWorkout(this, staminaExercises.get(index));
              } else {
                DBProxy.addExerciseToWorkout(this, strengthExercises.get(index - staminaExercises.size() - modifier));
              }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
              if (added.size() > 0) {
                System.out.print("La til: ");
                for (int i : added) {
                  System.out.print(i + ",");
                }
                System.out.println();
              }
              System.out.println("Ugyldig input");
              break;
            }
          }
          System.out.println("La til øvelser i økt.");
        }
        break;
      } else if (resp.equals("n")) {
        break;
      }
    }
  }

  public List<String> getExercises() {
    return new ArrayList<>(exercises);
  }

  public void setDate() {
    date = Util.getIntInput("Dato (20160113)", "Ugyldig dato");
  }

  public int getDate() {
    return date;
  }

  public void setTime() {
    time = Util.getIntInput("Tidspunkt (1200)", "Ugyldig tidspunkt");
  }

  public int getTime() {
    return time;
  }

  public void setDuration() {
    duration = Util.getIntInput("Varighet i minutter (45)", "Ugyldig input");
  }

  public int getDuration() {
    return duration;
  }

}
