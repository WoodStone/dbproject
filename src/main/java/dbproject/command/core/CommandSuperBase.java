package dbproject.command.core;

import dbproject.command.CommandHandler;

import java.util.List;

public abstract class CommandSuperBase extends CommandBase {

  protected CommandHandler handler = new CommandHandler();

  public CommandSuperBase() {
    super();
    init();
  }

  protected abstract void init();

  @Override
  public void processCommand(List<String> args) {
    try {
      String[] array = new String[args.size()];
      array = args.toArray(array);
      handler.checkInput(array);
    } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
      getUsage();
    }
  }

}
