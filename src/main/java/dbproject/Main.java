package dbproject;

import dbproject.command.*;
import dbproject.core.User;
import dbproject.database.DBProxy;

import java.util.Scanner;

public class Main {

  public static CommandHandler commandHandler;
  public static User user;

  public static void main(String[] args) {
    preInit();
    init();
    postInit();

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String[] input = scanner.nextLine().split(" ");
      try {
        commandHandler.checkInput(input);
      } catch (NullPointerException e) {
        System.out.println("Unknown command");
      }
    }
  }

  public static void preInit() {
    commandHandler = new CommandHandler();
    commandHandler.registerCommand(new CommandQuit());
    commandHandler.registerCommand(new CommandWorkout());
    commandHandler.registerCommand(new CommandExercise());
    commandHandler.registerCommand(new CommandLastWeek());
    commandHandler.registerCommand(new CommandTenLongest());
  }

  public static void init() {

    User user = DBProxy.isValidUser("ole");
    if (user != null) {
      Main.user = user;
      System.out.println("Hallo Ole.");
    }

  }

  public static void postInit() {

  }

  public static void shutdown() {

    System.exit(0);
  }

}
