package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class BankTestFixture {

    Bank bank;
    String account1, account2;

    @Before
    public void init() throws AccountNotExistsException {
//        bank = new FirstNationalBank();
        bank = new GreatSavingsBank();
        account1 = bank.openAccount();
        account2 = bank.openAccount();
        bank.deposit(account1, 1500);
        bank.deposit(account2, 12000);
    }

    @Test
    public void testTransfer() throws AccountNotExistsException, NotEnoughFundsException {
        bank.transfer(account2, account1, 3456l);
        Assert.assertEquals(4956, bank.getBalance(account1));
        Assert.assertEquals(8544, bank.getBalance(account2));
    }

    @Test(expected = NotEnoughFundsException.class)
    public void testTransferWithoutEnoughFunds() throws NotEnoughFundsException, AccountNotExistsException {
        bank.transfer(account1, account2, 3456l);
    }

}
