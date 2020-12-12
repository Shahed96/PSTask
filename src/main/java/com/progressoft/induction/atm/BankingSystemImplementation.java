package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankingSystemImplementation implements BankingSystem {

    public static String host = "jdbc:derby://localhost:1527/Initial Balances"; 
    public static String userName = "User1";
    public static String passward = "123456";

    
    @Override
    public BigDecimal getAccountBalance(String accountNumber) {

        try {

            Connection connectionObject;
            connectionObject = DriverManager.getConnection(host, userName, passward);
            Statement stm = connectionObject.createStatement();
            String SQL = "SELECT * FROM Accounts";
            ResultSet result = stm.executeQuery(SQL);

            while (result.next()) {

                int accountNumberInSystemInteger = result.getInt("Account_Number");
                String accountNumberInSystemString = Integer.toString(accountNumberInSystemInteger);
                BigDecimal balanceInSystem = result.getBigDecimal("Balance");

                if (accountNumber.equals(accountNumberInSystemString)) {
                    return balanceInSystem;
                }

            }
            throw new AccountNotFoundException(accountNumber);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return null;
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {

        try {

            Connection connectionObject;
            connectionObject = DriverManager.getConnection(host, userName, passward);
            Statement stm = connectionObject.createStatement();
            String SQL = "SELECT * FROM Accounts";
            ResultSet result = stm.executeQuery(SQL);

            while (result.next()) {

                int accountNumberInSystemInteger = result.getInt("Account_Number");
                String accountNumberInSystemString = Integer.toString(accountNumberInSystemInteger);
                BigDecimal balanceInSystem = result.getBigDecimal("Balance");

                if (accountNumber.equals(accountNumberInSystemString)) {
                   /* BigDecimal subtract = balanceInSystem.subtract(amount);
                    String query = "update Accounts set Balance = subtract where accountNumber.equals(accountNumberInSystemString)";
                    PreparedStatement prepare = connectionObject.prepareStatement(query);
                    prepare.execute();*/
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

}
