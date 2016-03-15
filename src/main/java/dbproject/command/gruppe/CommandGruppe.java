package dbproject.command.gruppe;

import dbproject.command.core.CommandSuperBase;


public class CommandGruppe extends CommandSuperBase {

  @Override
  protected void init() {
    handler.registerCommand(new GruppeList());
  }

  @Override
  public String getName() {
    return "gruppe";
  }

  @Override
  public String getUsage() {
    //TODO gruppe usage
    return null;
  }
}
