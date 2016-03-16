package dbproject.command;

import dbproject.Main;
import dbproject.core.User;
import dbproject.command.core.CommandBase;
import dbproject.database.DBProxy;

import java.util.List;

public class CommandLogin extends CommandBase {

  @Override
  public String getName() {
    return "login";
  }

  @Override
  public String getUsage() {
    return null;
  }

  @Override
  public void processCommand(List<String> args) {
    //TODO login
    User user = DBProxy.isValidUser(args.get(0));
    if (user != null) {
      Main.user = user;
      System.out.println("Logged in! :O");
    }
  }

}
