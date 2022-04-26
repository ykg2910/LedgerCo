package io;

import commands.Command;
import commands.CommandExecutor;
import commands.CommandExecutorFactory;
import exceptions.InvalidCommandException;

public abstract class InputProcessor {

    CommandExecutorFactory commandExecutorFactory;

    public InputProcessor(CommandExecutorFactory commandExecutorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
    }

    public void processCommand(Command command) throws Exception {
        CommandExecutor executor = commandExecutorFactory.getCommandExecutor(command);
        if(executor.validate(command))
            executor.execute(command);
        else
            throw new InvalidCommandException();
    }

    public abstract void run() throws Exception;
}
