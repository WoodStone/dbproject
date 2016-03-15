package dbproject.command;

import dbproject.Main;
import dbproject.command.core.CommandBase;

import java.util.List;

/**
 * Created by Vestein Dahl on 18.05.2015.
 */
public class CommandQuit extends CommandBase {

    @Override
    public String getName() {
        return "quit";
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public void processCommand(List<String> args) {
        Main.shutdown();
    }

}
