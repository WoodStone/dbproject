package dbproject.command.logg;

import dbproject.command.core.CommandSuperBase;

public class CommandLogg extends CommandSuperBase {

  @Override
  protected void init() {
    handler.registerCommand(new LoggList());
    handler.registerCommand(new LoggLes());
  }

  @Override
  public String getName() {
    return "logg";
  }

  @Override
  public String getUsage() {
    System.out.println("Usage: logg les <logg> | logg list");
    //TODO print usage
    return null;
  }

}
