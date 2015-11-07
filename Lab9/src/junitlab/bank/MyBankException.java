package junitlab.bank;

public class MyBankException extends BankException {

    public MyBankException(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public String getMessage() {
        return "My Bank Exception " + getAccountNumber();
    }
}
