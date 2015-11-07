package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class BankTest {

    private Bank bank;

    @Before
    public void init(){
//        bank = new FirstNationalBank();
        bank = new GreatSavingsBank();
    }

    @Test
    public void testOpenAccount() throws AccountNotExistsException{
        String number = bank.openAccount();
        long money = bank.getBalance(number);
        Assert.assertEquals(0l, money);
    }

    @Test
    public void testUniqueAccount(){
        String account1 = bank.openAccount();
        String account2 = bank.openAccount();
        Assert.assertNotEquals(account1, account2);
    }

    @Test(expected = AccountNotExistsException.class)
    public void testInvalidAccount() throws AccountNotExistsException{
        bank.openAccount();
        bank.openAccount();
        long l = bank.getBalance("számlaszám");
//        Assert.assertEquals(-1l, l);
    }

    @Test
    public void testDeposit() throws AccountNotExistsException {
        String account = bank.openAccount();
        bank.deposit(account, 2000);
        long money = bank.getBalance(account);
        Assert.assertEquals(2000, money);
    }

}
