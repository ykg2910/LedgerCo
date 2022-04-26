package commands;

import services.LoanService;
import services.PaymentService;

import java.util.List;

public class LoanCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "LOAN";

    public LoanCommandExecutor(LoanService loanService, PaymentService paymentService) {
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
        Integer principal = Integer.parseInt(params.get(2));
        Integer numYears = Integer.parseInt(params.get(3));
        Float rateOfInterest = Float.parseFloat(params.get(4));
        loanService.createLoan(bankName, borrowerName, principal, numYears, rateOfInterest);
    }
}
