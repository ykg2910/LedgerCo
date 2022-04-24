package models;

import exceptions.LumpSumExceedTotalPayable;
import lombok.Getter;

@Getter
public class Loan {

    private final String loanIdentifier;
    private final String bankName;
    private final String borrowerName;
    private final Integer principal;
    private final Integer numOfYears;
    private final Float interestRate;
    private final Integer initialEmiAmount;
    private final Integer initialEmiCount;


    public Loan(String loanIdentifier, String bankName, String borrowerName, Integer principal, Integer numOfYears, Float interestRate) {

        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.numOfYears = numOfYears;
        this.interestRate = interestRate;
        this.loanIdentifier = loanIdentifier;
        this.initialEmiCount = this.numOfYears * 12;
        if(this.principal * (1 + numOfYears * interestRate / 100) % this.initialEmiCount == 0)
            this.initialEmiAmount = (int)(this.principal * (1 + numOfYears * interestRate / 100) / this.initialEmiCount) ;
        else
            this.initialEmiAmount = (int)(this.principal * (1 + numOfYears * interestRate / 100) / this.initialEmiCount)  + 1;
    }
}
