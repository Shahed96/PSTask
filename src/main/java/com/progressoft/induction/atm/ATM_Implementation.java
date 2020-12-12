package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;


import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class ATM_Implementation implements ATM {

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {

        int accountNumberIntegerInput = Integer.parseInt(accountNumber);
       

        BankingSystemImplementation object = new BankingSystemImplementation();

        BigDecimal balanceInSystem = object.getAccountBalance(accountNumber);
        int equals = balanceInSystem.compareTo(amount); //compare between two bigDecimal objects 

        if ((equals == 0 || equals == 1)) {
            object.debitAccount(accountNumber, amount); //subtact two bigdacimal objects
        } else if (equals == -1) {
            throw new InsufficientFundsException();

        }

        //withdraw:
        if (amount.compareTo (new BigDecimal("230.0"))==1)
        {
        throw new NotEnoughMoneyInATMException();
        }
       
        List<Banknote> banknotes = new ArrayList<>();
        BigDecimal remains = amount;

        BigDecimal count50 = new BigDecimal("0.0");
        BigDecimal count20 = new BigDecimal("0.0");
        BigDecimal count10 = new BigDecimal("0.0");
        BigDecimal count5 = new BigDecimal("0.0");

      // Evaluate each nominal count
        while ((amount.compareTo(new BigDecimal("0.0")) == 0) || (amount.compareTo(new BigDecimal("0.0")) == 1)) {
            if (remains.compareTo(new BigDecimal("50.0")) == 0 || remains.compareTo(new BigDecimal("50.0")) == 1) //>=50
            {
                count50 = (remains.divide(new BigDecimal("50.0")));
                remains = remains.subtract(count50.multiply(new BigDecimal(50.0)));
            }

            if (remains.compareTo(new BigDecimal("20.0")) == 0 || remains.compareTo(new BigDecimal("20.0")) == 1) //>=20
            {
                count20 = (remains.divide(new BigDecimal("20.0")));
                remains = remains.subtract(count20.multiply(new BigDecimal(20.0)));
            }

            if (remains.compareTo(new BigDecimal("10.0")) == 0 || remains.compareTo(new BigDecimal("10.0")) == 1) //>=10
            {
                count10 = (remains.divide(new BigDecimal("10.0")));
                remains = remains.subtract(count10.multiply(new BigDecimal(10.0)));
            }

            if (remains.compareTo(new BigDecimal("5.0")) == 0 || remains.compareTo(new BigDecimal("5.0")) == 1) //>=5
            {
                count5 = (remains.divide(new BigDecimal("5.0")));
                remains = remains.subtract(count5.multiply(new BigDecimal(5.0)));
            }

        }
// Fill banknote list

        for (int i = 0; i < count50.intValue(); i++) {
            banknotes.add(Banknote.FIFTY_JOD);
        }

        for (int i = 0; i < count20.intValue(); i++) {
            banknotes.add(Banknote.TWENTY_JOD);
        }

        for (int i = 0; i < count10.intValue(); i++) {
            banknotes.add(Banknote.TEN_JOD);
        }

        for (int i = 0; i < count5.intValue(); i++) {
            banknotes.add(Banknote.FIVE_JOD);
        }

        return banknotes;

    }
}
