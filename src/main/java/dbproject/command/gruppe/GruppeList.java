package dbproject.command.gruppe;

import dbproject.command.core.CommandBase;

import java.util.List;

public class GruppeList extends CommandBase {

  @Override
  public String getName() {
    return "list";
  }

  @Override
  public String getUsage() {
    return null;
  }

  @Override
  public void processCommand(List<String> args) {
    //TODO print liste me grupper
  }

}
