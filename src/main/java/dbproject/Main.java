package dbproject;

import dbproject.command.CommandHandler;
import dbproject.command.CommandKategori;
import dbproject.command.CommandLogin;
import dbproject.command.CommandQuit;
import dbproject.command.gruppe.CommandGruppe;
import dbproject.command.logg.CommandLogg;

import java.util.Scanner;

public class Main {

  public static CommandHandler commandHandler;
  public static CommandHandler preLoginHandler;
  public static boolean loggedin = false;
  public static User user;

  public static void main(String[] args) {
    preInit();

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String[] input = scanner.nextLine().split(" ");
      try {
        if (loggedin) {
          commandHandler.checkInput(input);
        } else {
          preLoginHandler.checkInput(input);
        }
      } catch (NullPointerException e) {
        System.out.println("Unknown command");
      }
    }
    //scanner.close();

  }

  public static void preInit() {
    preLoginHandler = new CommandHandler();
    preLoginHandler.registerCommand(new CommandLogin());

    commandHandler = new CommandHandler();
    commandHandler.registerCommand(new CommandQuit());
    commandHandler.registerCommand(new CommandLogg());
    commandHandler.registerCommand(new CommandGruppe());
    commandHandler.registerCommand(new CommandKategori());




  }

  public static void init() {

  }

  public static void postInit() {

  }

  public static void shutdown() {

    System.exit(0);
  }

}
