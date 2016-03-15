package dbproject.command.core;

import java.util.List;

/**
 * Created by Vestein Dahl on 18.05.2015.
 */
public abstract class CommandBase {

    public abstract String getName();

    public abstract String getUsage();

    public abstract void processCommand(List<String> args);

}
