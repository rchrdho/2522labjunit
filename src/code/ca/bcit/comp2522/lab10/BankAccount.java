package ca.bcit.comp2522.lab10;

/**
 * Represents a bank account with an account number and balance.
 * Provides methods to deposit, withdraw, transfer funds, and check account details.
 *
 * @author Phyo Thu Kha
 * @author Richard Ho
 * @author Gems
 */
public class BankAccount {
    private static final int MIN_BALANCE_AMOUNT_USD = 0;

    private double balance;
    private final String accountNumber;

    /**
     * Constructs a BankAccount object with the specified account number and balance.
     *
     * @param accountNumber the unique identifier for the account
     * @param balance       the initial balance in the account
     * @throws IllegalArgumentException if the account number is invalid
     */
    BankAccount(final String accountNumber,
                final double balance)
    {
        isValidAccountNumber(accountNumber);

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param depositAmount the amount to deposit
     * @throws IllegalArgumentException if the deposit amount is invalid
     */
    public void deposit(final double depositAmount)
    {
        isValidDepositAmount(depositAmount);

        balance += depositAmount;
    }

    /**
     * Gets the account number of this bank account.
     *
     * @return the account number
     */
    public final String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Gets the current balance of the account in USD.
     *
     * @return the balance
     */
    public final double getBalanceUsd()
    {
        return balance;
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param withdrawAmount the amount to withdraw
     * @throws IllegalArgumentException if the withdrawal amount is invalid or insufficient funds
     */
    public final void withdraw(final double withdrawAmount)
    {
        isValidWithdrawAmount(withdrawAmount);

        balance -= withdrawAmount;
    }

    /**
     * Transfers a specified amount from this account to another account.
     *
     * @param account        the recipient bank account
     * @param accountNumber  the recipient account number
     * @param amount         the amount to transfer
     * @throws IllegalArgumentException if the account numbers do not match or the amount is invalid
     */
    public final void transferToBank(final BankAccount account,
                                     final String      accountNumber,
                                     final int         amount)
    {
        isMatchingAccountNumber(account, accountNumber);

        account.deposit(amount);

        this.withdraw(amount);
    }

    // Validates that the account number is not null, empty, or blank.
    private void isValidAccountNumber(final String accountNumber)
    {
        if (accountNumber == null)
        {
            throw new IllegalArgumentException("Account Number cannot be null");
        }

        boolean emptyAccountNum;
        emptyAccountNum = accountNumber.isBlank();

        if (accountNumber.isEmpty() && !emptyAccountNum)
        {
            throw new IllegalArgumentException("Account Number cannot be empty");
        }
    }

    // Validates that the account numbers match correctly for a transfer operation.
    private void isMatchingAccountNumber(final BankAccount account,
                                         final String      accountNumber)
    {
        isValidAccountNumber(accountNumber);

        if (account.getAccountNumber().equals(accountNumber))
        {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if (!accountNumber.equalsIgnoreCase(this.accountNumber))
        {
            throw new IllegalArgumentException("Account not found");
        }
    }

    // Validates that the deposit amount is positive.
    private void isValidDepositAmount(final double depositAmount)
    {
        if (depositAmount < MIN_BALANCE_AMOUNT_USD)
        {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    // Validates that the withdrawal amount is positive and that there are sufficient funds.
    private void isValidWithdrawAmount(final double withdrawAmount)
    {
        if (withdrawAmount < MIN_BALANCE_AMOUNT_USD)
        {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (balance < withdrawAmount)
        {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }
}
