package com.progressoft.induction.atm.exceptions;

public class NotEnoughMoneyInATMException extends RuntimeException {

   public NotEnoughMoneyInATMException() {
        System.out.println("Not Enough Money In ATM ");
        System.exit(0);
    }
}
