package junitlab.bank;

import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class FullTest {

    Bank bank;

    @Before
    public void setUp() throws Exception {
        bank = new GreatSavingsBank();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRoundNegative() throws Exception {
        String account = bank.openAccount();
        bank.deposit(account, 10000);
        bank.withdraw(account, -1);
    }

    @Test
    public void testCloseAccountEmpty() throws Exception {
        String account = bank.openAccount();
        boolean result = bank.closeAccount(account);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testCloseAccountNotEmpty() throws Exception {
        String account = bank.openAccount();
        bank.deposit(account, 10000);
        boolean result = bank.closeAccount(account);
        Assert.assertEquals(false, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransferNegative() throws Exception {
        String account1 = bank.openAccount();
        String account2 = bank.openAccount();
        bank.deposit(account1, 10000);
        bank.transfer(account1, account2, -1);
    }

    @Test(expected = MyBankException.class)
    public void testBankException() throws Exception {
        MyBank myBank = new MyBank();
        try {
            myBank.throwException();
        } catch (MyBankException e) {
            System.err.println(e.getMessage());
        }
        myBank.throwException();
    }
}
