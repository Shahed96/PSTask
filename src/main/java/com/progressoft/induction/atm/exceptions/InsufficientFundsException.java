package com.progressoft.induction.atm.exceptions;

public class InsufficientFundsException extends RuntimeException {
    //constructor:   

    public InsufficientFundsException() {
        System.out.println("There is not enough balance");
        System.exit(0);
    }
    
}
