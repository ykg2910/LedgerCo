package commands;

import services.LoanService;
import services.PaymentService;

public abstract class CommandExecutor {

    protected LoanService loanService;
    protected PaymentService paymentService;

    public CommandExecutor(LoanService loanService, PaymentService paymentService) {
        this.loanService = loanService;
        this.paymentService = paymentService;
    }

    public abstract String getCommandName();

    public boolean validate(Command command)  {
        return true;
    }

    public abstract void execute(Command command) throws Exception;
}
