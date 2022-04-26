package commands;

import exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private final String commandName;
    private final List<String> params;


    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(String inputLine) throws InvalidCommandException {

        final List<String> tokenList = Arrays.stream(inputLine.trim().split(" ")).map(String::trim).
                filter(token -> token.length() > 0).collect(Collectors.toList());
        if(!(tokenList.size() > 0))
            throw new InvalidCommandException();

        commandName = tokenList.get(0);
        tokenList.remove(0);
        params = tokenList;
    }
}
