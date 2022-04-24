import helpers.LoanKeyProvider;
import services.LoanService;
import services.PaymentService;

public class Main {

    public static void main(String[] args) {

        LoanKeyProvider provider = new LoanKeyProvider();
        LoanService loanService = new LoanService(provider);
        PaymentService paymentService = new PaymentService(loanService);

//        loanService.createLoan( "IDIDI","Dale", 10000, 5, 4.0f);
//        loanService.createLoan( "MBI","Harry", 2000, 2, 2.0f);
//
//        System.out.println(paymentService.getBalance("IDIDI", "Dale", 5));
//        System.out.println(paymentService.getBalance("IDIDI", "Dale", 40));
//        System.out.println(paymentService.getBalance("MBI", "Harry", 12));
//        System.out.println(paymentService.getBalance("MBI", "Harry", 0));


        loanService.createLoan( "IDIDI","Dale", 5000, 1, 6.0f);
        loanService.createLoan( "MBI","Harry", 10000, 3, 7.0f);
        loanService.createLoan( "UON","Shelly", 15000, 2, 9.0f);

        paymentService.createPayment("IDIDI","Dale", 1000, 5);
        paymentService.createPayment("MBI","Harry", 5000, 10);
        paymentService.createPayment("UON","Shelly", 7000, 12);

        System.out.println(paymentService.getBalance("IDIDI", "Dale", 3));
        System.out.println(paymentService.getBalance("IDIDI", "Dale", 6));
        System.out.println(paymentService.getBalance("UON", "Shelly", 12));
        System.out.println(paymentService.getBalance("MBI", "Harry", 12));
    }
}
