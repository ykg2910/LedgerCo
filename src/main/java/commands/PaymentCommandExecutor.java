package commands;

import services.LoanService;
import services.PaymentService;

import java.util.List;

public class PaymentCommandExecutor extends CommandExecutor{

    public static String COMMAND_NAME = "PAYMENT";

    public PaymentCommandExecutor(LoanService loanService, PaymentService paymentService) {
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
        Integer lumpsumpAmount = Integer.parseInt(params.get(2));
        Integer emiNumber = Integer.parseInt(params.get(3));
        paymentService.createPayment(bankName, borrowerName, lumpsumpAmount, emiNumber);
    }
}
