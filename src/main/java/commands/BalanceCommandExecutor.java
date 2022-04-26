package commands;

import models.Balance;
import services.LoanService;
import services.PaymentService;

import java.util.List;

public class BalanceCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "BALANCE";

    public BalanceCommandExecutor(LoanService loanService, PaymentService paymentService) {
        super(loanService, paymentService);
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void execute(Command command) throws Exception {
        List<String> params = command.getParams();
        String bankName = params.get(0);
        String borrowerName = params.get(1);
        Integer emiNumber = Integer.parseInt(params.get(2));
        Balance balance = paymentService.getBalance(bankName, borrowerName, emiNumber);
        System.out.println(balance);
    }
}
