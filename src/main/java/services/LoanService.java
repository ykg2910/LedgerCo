package services;

import exceptions.LoanAlreadyExistsException;
import exceptions.LoanNotExistsException;
import helpers.LoanKeyProvider;
import models.Loan;

import java.util.HashMap;
import java.util.Map;

public class LoanService {

    private final Map<String, Loan> loans;
    private final LoanKeyProvider loanKeyProvider;

    public LoanService(LoanKeyProvider loanKeyProvider) {
        this.loans = new HashMap<>();
        this.loanKeyProvider = loanKeyProvider;
    }

    public void createLoan(String bankName, String borrowerName, Integer principal, Integer numOfYears, Float interestRate) {

        String loanIdentifier = loanKeyProvider.getLoanIdentifier(bankName, borrowerName);
        Loan loan = new Loan(loanIdentifier, bankName, borrowerName, principal, numOfYears, interestRate);
        if(loans.containsKey(loanIdentifier))
            throw new LoanAlreadyExistsException();
        loans.put(loanIdentifier, loan);
    }

    public Loan getLoanDetails(String bankName, String borrowerName) {
        String loanIdentifier = loanKeyProvider.getLoanIdentifier(bankName, borrowerName);
        if(!loans.containsKey(loanIdentifier))
            throw new LoanNotExistsException();
        return loans.get(loanIdentifier);
    }
}
