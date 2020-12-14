package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankingSystemImplementation implements BankingSystem {

    public static Connection connect() throws ClassNotFoundException {

        Connection con;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=BankingSystem;user=admin;password=123456";

        try {

            con = DriverManager.getConnection(connectionURL);
            //needs SQLExption

            return con;

        } catch (SQLException e) {

            System.out.println(e);

        }
        return null;
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        BigDecimal AccountBalance = null;
        try {
            Connection con = connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from InitialBalance");

            while (rs.next()) {
                int accountNumberInSystemInteger = rs.getInt("AccountNumber");
                int balanceInSystem = rs.getInt("Balance");
                String accountNumberInSystemString = String.valueOf(accountNumberInSystemInteger);
                if (accountNumberInSystemString.equalsIgnoreCase(accountNumber)) {

                    return BigDecimal.valueOf(balanceInSystem);

                }
            }
            throw new AccountNotFoundException(accountNumber);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(BankingSystemImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        BigDecimal balanceInSystemBigDecimal;
        try {
            Connection con = connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from InitialBalance");
            while (rs.next()) {
                int accountNumberInSystemInteger = rs.getInt("AccountNumber");
                int balanceInSystem = rs.getInt("Balance");
                String accountNumberInSystemString = String.valueOf(accountNumberInSystemInteger);
                if (accountNumberInSystemString.equalsIgnoreCase(accountNumber)) {
                    balanceInSystemBigDecimal = BigDecimal.valueOf(balanceInSystem);
                    if (amount.compareTo(balanceInSystemBigDecimal) == 1) {
                        throw new InsufficientFundsException();
                    }

                    balanceInSystemBigDecimal = balanceInSystemBigDecimal.subtract(amount);
                    int balanceInSystemNew = balanceInSystemBigDecimal.toBigInteger().intValueExact();

                    ///update: balanceInSystem=amount;
                    String query = "update InitialBalance set Balance = ? where AccountNumber = accountNumber";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    preparedStmt.setInt(2, balanceInSystemNew);

                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BankingSystemImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
