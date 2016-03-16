package dbproject.database;

import dbproject.core.User;
import dbproject.core.exercise.Exercise;
import dbproject.core.exercise.Stamina;
import dbproject.core.exercise.Strength;
import dbproject.core.workout.Indoor;
import dbproject.core.workout.Outdoor;
import dbproject.core.workout.Workout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class DBProxy {

  public DBProxy() {

  }

  public static User isValidUser(String name) {
    try {
      ResultSet rs = DBConnector.makeQuery("select * from person;").getResultSet();
      while (rs.next()) {
        int id = rs.getInt("personNr");
        String navn = rs.getString("navn");
        if (name.equals(navn)) {
          return new User(id, navn);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static void addKategori(String kategori, User user) {
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM kategori WHERE personNr=" + user.NR + " AND katNavn='" + kategori + "';").getResultSet();
      if (!rs.isBeforeFirst()) {
        DBConnector.makeStatement("insert into kategori(personNr,katNavn) values(" + user.NR + "," + "'" + kategori + "'" + ");");
      } else {
        throw new IllegalArgumentException();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<List<String>> getKategorier() {
    List<List<String>> kategorier = new ArrayList<>();
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM kategori;").getResultSet();
      while (rs.next()) {
        int nr = rs.getInt("personNr");
        String name = rs.getString("katNavn");
        kategorier.add(Arrays.asList(Integer.toString(nr), name));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return kategorier;
  }

  public static void addExercise(Exercise exercise) {
    String v1 = "values('" + exercise.NAME + "')";
    DBConnector.makeStatement("INSERT INTO oevelse(oevelseNavn) " + v1 + ";");
    if (exercise instanceof Stamina) {
      Stamina s = (Stamina) exercise;
      //String v2 = "values('" + stamina.NAME + "'," + stamina.getLength() + "," + stamina.getTime() + ")";
      String v2 = String.format("values('%s',%d,%d)", s.NAME, s.getLength(), s.getTime());
      DBConnector.makeStatement("INSERT INTO utholdenhetOevelse(oevelseNavn,distanse,tid) " + v2 + ";");
    } else {
      Strength s = (Strength) exercise;
      String v3 = String.format("values('%s',%d,%d,%d,'%s')", s.NAME, s.getWeight(), s.getRepetitions(), s.getSets(), s.getType());
      DBConnector.makeStatement("INSERT INTO kondisjonStyrkeOevelse(oevelseNavn,belastning,reps,sets,oevelseTyp) " + v3 + ";");
    }
  }

  public static boolean containsWorkout(String name) {
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM treningsOekt WHERE oektNavn='" + name + "';").getResultSet();
      if (!rs.isBeforeFirst()) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  public static boolean containsExercise(String name) {
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM oevelse WHERE oevelseNavn='" + name + "';").getResultSet();
      if (!rs.isBeforeFirst()) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  public static boolean workoutContainExercise(Workout workout, Exercise exercise) {
    try {
      ResultSet rs = DBConnector.makeQuery(String.format("SELECT * FROM oevelser WHERE oevelseNavn='%s' AND oektNavn='%s';", exercise.NAME, workout.NAME)).getResultSet();
      if (!rs.isBeforeFirst()) {
        return false;
      }
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  public static void addWorkout(Workout workout, User user) {
    String v1 = "values(" + user.NR + ",'" + workout.NAME + "'," + workout.getDate() + "," + workout.getTime() + "," + workout.getDuration() + ")";
    DBConnector.makeStatement("INSERT INTO treningsOekt(personNr, oektNavn, dato, tidspunkt, varighhet) " + v1 + ";");
    if (workout instanceof Indoor) {
      String v2 = "values('" + workout.NAME + "'," + ((Indoor) workout).getSpectators() + ")";
      DBConnector.makeStatement("INSERT INTO innendoersOekt(oektNavn, tilskuerer) " + v2 + ";");
    } else {
      String v3 = "values('" + workout.NAME + "','" + ((Outdoor) workout).getWeather() + "'," + ((Outdoor) workout).getTemp() + ")";
      DBConnector.makeStatement("INSERT INTO utendoersOekt(oektNavn, vaerforhold, temperatur) " + v3 + ";");
    }
  }

  public static void addExerciseToWorkout(Workout workout, Exercise exercise) {
    if (!workoutContainExercise(workout, exercise)) {
      String v1 = String.format("values('%s','%s')", exercise.NAME, workout.NAME);
      DBConnector.makeStatement("INSERT INTO oevelser(oevelseNavn,oektNavn) " + v1 + ";");
    }
  }

  public static List<Stamina> getStaminaExercises() {
    List<Stamina> exercises = new ArrayList<>();
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM oevelse JOIN utholdenhetOevelse ON oevelse.oevelseNavn=utholdenhetOevelse.oevelseNavn;").getResultSet();
      while (rs.next()) {
        Stamina stamina = new Stamina(rs.getString("oevelseNavn"), rs.getInt("distanse"), rs.getInt("tid"));
        exercises.add(stamina);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return exercises;
  }

  public static List<Strength> getStrenghtExercises() {
    List<Strength> exercises = new ArrayList<>();
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM oevelse JOIN kondisjonStyrkeOevelse ON oevelse.oevelseNavn=kondisjonStyrkeOevelse.oevelseNavn;").getResultSet();
      while (rs.next()) {
        Strength strength = new Strength(rs.getString("oevelseNavn"), rs.getString("oevelseTyp"), rs.getInt("belastning"), rs.getInt("reps"), rs.getInt("sets"));
        exercises.add(strength);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return exercises;
  }

  public static List<Workout> getLastWeekWorkouts() {
    List<Workout> workouts = new ArrayList<>();
    LocalDateTime currentDate = LocalDateTime.now();
    LocalDateTime lastWeekDate = currentDate.minusDays(7);
    int cDate = Integer.parseInt(String.format("%04d%02d%02d", currentDate.getYear(), currentDate.getMonth().getValue(), currentDate.getDayOfMonth()));
    int wDate = Integer.parseInt(String.format("%04d%02d%02d", lastWeekDate.getYear(), lastWeekDate.getMonth().getValue(), lastWeekDate.getDayOfMonth()));
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM treningsOekt;").getResultSet();
      while (rs.next()) {
        String name = rs.getString("oektNavn");
        int oDate = rs.getInt("dato");
        int time = rs.getInt("tidspunkt");
        int duration = rs.getInt("varighhet");
        if (oDate > wDate && oDate < cDate) {
          Workout workout = new Workout(name, oDate, time, duration, getExerciseInWorkout(name));
          workouts.add(workout);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return workouts;
  }

  public static List<String> getExerciseInWorkout(String workoutName) {
    List<String> exercises = new ArrayList<>();
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT oevelseNavn FROM oevelser WHERE oektNavn='" + workoutName + "';").getResultSet();
      while (rs.next()) {
        exercises.add(getExercise(rs.getString("oevelseNavn")).NAME);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return exercises;
  }

  public static List<Exercise> getExercises() {
    List<Exercise> exercises = new ArrayList<>();
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM oevelse").getResultSet();
      while (rs.next()) {
        exercises.add(getExercise(rs.getString("oevelseNavn")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return exercises;
  }

  public static Exercise getExercise(String name) {
    Exercise exercise;
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM oevelse JOIN utholdenhetOevelse ON oevelse.oevelseNavn=utholdenhetOevelse.oevelseNavn WHERE oevelse.oevelseNavn='" + name + "';").getResultSet();
      if (!rs.isBeforeFirst()) {
        rs = DBConnector.makeQuery("SELECT * FROM oevelse JOIN kondisjonStyrkeOevelse ON oevelse.oevelseNavn=kondisjonStyrkeOevelse.oevelseNavn WHERE oevelse.oevelseNavn='" + name + "';").getResultSet();
        rs.next();
        exercise = new Strength(rs.getString("oevelseNavn"), rs.getString("oevelseTyp"), rs.getInt("belastning"), rs.getInt("reps"), rs.getInt("sets"));
        return exercise;
      }
      rs.next();
      exercise = new Stamina(rs.getString("oevelseNavn"), rs.getInt("distanse"), rs.getInt("tid"));
      return exercise;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static List<Exercise> getTenLongestStaminaExercises() {
    List<Exercise> exercises = new ArrayList<>();
    try {
      ResultSet rs = DBConnector.makeQuery("SELECT * FROM oevelse JOIN utholdenhetOevelse ON oevelse.oevelseNavn = utholdenhetOevelse.oevelseNavn ORDER BY distanse DESC LIMIT 10;").getResultSet();
      while (rs.next()) {
        exercises.add(getExercise(rs.getString("oevelseNavn")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return exercises;
  }

}
