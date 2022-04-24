package services;

import models.Balance;
import models.Loan;
import models.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentService {

    private final LoanService loanService;
    private final Map<String, List<Payment>> payments;

    public PaymentService(LoanService loanService) {
        this.payments = new HashMap<>();
        this.loanService = loanService;
    }

    public void createPayment(String bankName, String borrowerName, Integer lumpSumpAmount, Integer emiNumber) {

        Loan loan = this.loanService.getLoanDetails(bankName, borrowerName);
        Payment payment = new Payment(bankName, borrowerName, lumpSumpAmount, emiNumber);
        if(!payments.containsKey(loan.getLoanIdentifier()))
            payments.put(loan.getLoanIdentifier(), new ArrayList<>());
        payments.get(loan.getLoanIdentifier()).add(payment);
    }

    public List<Payment> getPaymentDetails(String bankName, String borrowerName) {

        Loan loan = this.loanService.getLoanDetails(bankName, borrowerName);
        if(!payments.containsKey(loan.getLoanIdentifier()))
            return new ArrayList<>();
        return payments.get(loan.getLoanIdentifier());
    }

    public Balance getBalance(String bankName, String borrowerName, Integer emiNumber) {

        Loan loan = this.loanService.getLoanDetails(bankName, borrowerName);
        List<Payment> payments = this.getPaymentDetails(bankName, borrowerName);

        Integer paidAmount = loan.getInitialEmiAmount() * emiNumber;
        for(Payment payment: payments) {
            if(payment.getEmiNumber() <= emiNumber)
                paidAmount += payment.getLumpsumpAmount();
        }

        int emiRemaining = (loan.getInitialEmiAmount() * loan.getInitialEmiCount() - paidAmount) / loan.getInitialEmiAmount();
        if((loan.getInitialEmiAmount() * loan.getInitialEmiCount() - paidAmount) % loan.getInitialEmiAmount() > 0)
            emiRemaining +=1;

        return new Balance(bankName, borrowerName, paidAmount, emiRemaining);
    }
}
