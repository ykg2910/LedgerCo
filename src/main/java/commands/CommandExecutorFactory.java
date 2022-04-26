package commands;

import exceptions.InvalidCommandException;
import services.LoanService;
import services.PaymentService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {

    private final Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(LoanService loanService, PaymentService paymentService) {
        commands.put(BalanceCommandExecutor.COMMAND_NAME, new BalanceCommandExecutor(loanService, paymentService));
        commands.put(LoanCommandExecutor.COMMAND_NAME, new LoanCommandExecutor(loanService, paymentService));
        commands.put(PaymentCommandExecutor.COMMAND_NAME, new PaymentCommandExecutor(loanService, paymentService));
    }

    public CommandExecutor getCommandExecutor(final Command command) throws InvalidCommandException {
        CommandExecutor executor = commands.get(command.getCommandName());
        if(executor == null || !command.getCommandName().equals(executor.getCommandName()))
            throw new InvalidCommandException();
        return executor;
    }
}
