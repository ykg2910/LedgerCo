package IntegrationTests;

import helpers.LoanKeyProvider;
import models.Balance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.LoanService;
import services.PaymentService;

public class ScenariosTest {

    LoanKeyProvider provider;
    LoanService loanService;
    PaymentService paymentService;

    @Before
    public void setup() {
        provider = new LoanKeyProvider();
        loanService = new LoanService(provider);
        paymentService = new PaymentService(loanService);
    }

    @Test
    public void testScenario1() {

        loanService.createLoan( "IDIDI","Dale", 10000, 5, 4.0f);
        loanService.createLoan( "MBI","Harry", 2000, 2, 2.0f);

        Balance balance = paymentService.getBalance("IDIDI", "Dale", 5);
        Assert.assertEquals(balance.getBankName(), "IDIDI");
        Assert.assertEquals(balance.getBorrowerName(), "Dale");
        Assert.assertEquals(balance.getAmountPaid().intValue(), 1000);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 55);

        balance = paymentService.getBalance("IDIDI", "Dale", 40);
        Assert.assertEquals(balance.getAmountPaid().intValue(), 8000);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 20);

        balance = paymentService.getBalance("MBI", "Harry", 12);
        Assert.assertEquals(balance.getAmountPaid().intValue(), 1044);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 12);

        balance = paymentService.getBalance("MBI", "Harry", 0);
        Assert.assertEquals(balance.getAmountPaid().intValue(), 0);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 24);
    }

    @Test
    public void testScenario2() {

        loanService.createLoan( "IDIDI","Dale", 5000, 1, 6.0f);
        loanService.createLoan( "MBI","Harry", 10000, 3, 7.0f);
        loanService.createLoan( "UON","Shelly", 15000, 2, 9.0f);

        paymentService.createPayment("IDIDI","Dale", 1000, 5);
        paymentService.createPayment("MBI","Harry", 5000, 10);
        paymentService.createPayment("UON","Shelly", 7000, 12);

        Balance balance = paymentService.getBalance("IDIDI", "Dale", 3);
        Assert.assertEquals(balance.getBankName(), "IDIDI");
        Assert.assertEquals(balance.getBorrowerName(), "Dale");
        Assert.assertEquals(balance.getAmountPaid().intValue(), 1326);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 9);

        balance = paymentService.getBalance("IDIDI", "Dale", 6);
        Assert.assertEquals(balance.getAmountPaid().intValue(), 3652);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 4);

        balance = paymentService.getBalance("UON", "Shelly", 12);
        Assert.assertEquals(balance.getAmountPaid().intValue(), 15856);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 3);

        balance = paymentService.getBalance("MBI", "Harry", 12);
        Assert.assertEquals(balance.getAmountPaid().intValue(), 9044);
        Assert.assertEquals(balance.getNumEmiLeft().intValue(), 10);
    }
}
