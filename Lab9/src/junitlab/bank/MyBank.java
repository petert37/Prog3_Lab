package junitlab.bank;

import junitlab.bank.impl.GreatSavingsBank;

public class MyBank extends GreatSavingsBank {

    public void throwException() throws MyBankException{
        throw new MyBankException("Placeholder account number");
    }

}
