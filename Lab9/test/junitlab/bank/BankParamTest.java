package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class BankParamTest {

    int amount, rounded;
    Bank bank;

    public BankParamTest(int amount, int rounded) {
        this.amount = amount;
        this.rounded = rounded;
    }

    @Before
    public void init(){
        bank = new GreatSavingsBank();
//        bank = new FirstNationalBank();
    }

    @Parameters
    public static List<Object[]> parameters(){
        List<Object[]> params = new ArrayList<>(7);
        params.add(new Object[] {1100, 1100});
        params.add(new Object[] {1101, 1100});
        params.add(new Object[] {1149, 1100});
        params.add(new Object[] {1150, 1200});
        params.add(new Object[] {1151, 1200});
        params.add(new Object[] {1199, 1200});
        params.add(new Object[] {1200, 1200});
        return params;
    }

    @Test
    public void testWithdrawRounding() throws AccountNotExistsException, NotEnoughFundsException {
        String account = bank.openAccount();
        bank.deposit(account, 10000);
        bank.withdraw(account, amount);
        long money = bank.getBalance(account);
        Assert.assertEquals(10000 - rounded, money);
    }


}
