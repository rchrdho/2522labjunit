package ca.bcit.comp2522.lab10;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a bank that manages multiple bank accounts.
 * Allows adding accounts, retrieving accounts, and calculating total balance.
 *
 * @author Phyo Thu Kha
 * @author Richard Ho
 * @author Gems
 */
public class Bank
{
    private final Map<String, BankAccount> accounts;

    /**
     * Constructs a Bank object with an empty collection of accounts.
     */
    public Bank()
    {
        this.accounts = new HashMap<>();
    }

    /**
     * Adds a bank account to the bank's collection.
     *
     * @param bankAccount the bank account to add
     * @throws IllegalArgumentException if the account number is invalid
     */
    public void addAccount(final BankAccount bankAccount)
    {
        isValidAccountNumber(bankAccount.getAccountNumber());

        accounts.put(bankAccount.getAccountNumber(), bankAccount);
    }

    /**
     * Retrieves a bank account using the account number.
     *
     * @param accountNumber the account number of the desired account
     * @return the bank account corresponding to the account number
     * @throws IllegalArgumentException if the account number is invalid or not found
     */
    public BankAccount retrieveAccount(final String accountNumber)
    {
        isValidAccountNumber(accountNumber);

        isValidAccount(accountNumber);

        return accounts.get(accountNumber);
    }

    // Validates that the account number is not null or empty.
    private void isValidAccountNumber(final String accountNumber)
    {
        if(accountNumber == null)
        {
            throw new IllegalArgumentException("Account Number cannot be null");
        }

        final boolean emptyAccountNumber;

        emptyAccountNumber = accountNumber.isEmpty();

        if(emptyAccountNumber)
        {
            throw new IllegalArgumentException("Account Number cannot be empty");
        }
    }

    // Validates that an account exists for the given account number.
    private void isValidAccount(final String accountNumber)
    {
        isValidAccountNumber(accountNumber);

        if(!accounts.containsKey(accountNumber))
        {
            throw new IllegalArgumentException("Account not found");
        }
    }

    /**
     * Calculates the total balance of all bank accounts in USD.
     *
     * @return the total balance of all accounts
     */
    public double totalBalanceUsd()
    {
        return accounts.values().stream()
                .mapToDouble(BankAccount::getBalanceUsd)
                .sum();
    }
}
