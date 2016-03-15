package dbproject.command.logg;

import dbproject.command.core.CommandBase;

public class LoggList extends CommandBase{

  @Override
  public String getName() {
    return "list";
  }

  @Override
  public String getUsage() {
    return null;
  }

  @Override
  public void processCommand(java.util.List<String> args) {
    System.out.println("liste med logger");
    //TODO logg list command
  }

}
