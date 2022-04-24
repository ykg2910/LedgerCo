package helpers;

public class LoanKeyProvider {

    public LoanKeyProvider() {

    }

    public String getLoanIdentifier(String bankName, String borrowerName) {
        return String.format("%s-***-%s", bankName, borrowerName);
    }
}
