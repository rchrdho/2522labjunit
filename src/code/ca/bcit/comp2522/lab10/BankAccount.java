package ca.bcit.comp2522.lab10;

/**
 * Represents a bank account with an account number and balance.
 * Provides methods to deposit, withdraw, transfer funds, and check account details.
 *
 * @author Phyo Thu Kha
 * @author Richard Ho
 * @author Gems
 */
public class BankAccount
{
    private static final int MIN_BALANCE_AMOUNT_USD = 0;

    private double       balanceUsd;
    private final String accountNumber;

    /**
     * Constructs a BankAccount object with the specified account number and balance.
     *
     * @param accountNumber the unique identifier for the account
     * @param balanceUsd       the initial balance in the account in USD
     * @throws IllegalArgumentException if the account number is invalid
     */
    BankAccount(final String accountNumber,
                final double balanceUsd)
    {
        isValidAccountNumber(accountNumber);

        this.accountNumber = accountNumber;
        this.balanceUsd    = balanceUsd;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param depositAmountUsd the amount to deposit
     * @throws IllegalArgumentException if the deposit amount is invalid
     */
    public void deposit(final double depositAmountUsd)
    {
        isValidDepositAmount(depositAmountUsd);

        balanceUsd += depositAmountUsd;
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
        return balanceUsd;
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param withdrawAmountUsd the amount to withdraw
     * @throws IllegalArgumentException if the withdrawal amount is invalid or insufficient funds
     */
    public final void withdraw(final double withdrawAmountUsd)
    {
        isValidWithdrawAmount(withdrawAmountUsd);

        balanceUsd -= withdrawAmountUsd;
    }

    /**
     * Transfers a specified amount from this account to another account.
     *
     * @param account        the recipient bank account
     * @param accountNumber  the recipient account number
     * @param amountUsd         the amount to transfer
     * @throws IllegalArgumentException if the account numbers do not match or the amount is invalid
     */
    public final void transferToBank(final BankAccount account,
                                     final String      accountNumber,
                                     final int         amountUsd)
    {
        isMatchingAccountNumber(account, accountNumber);

        account.deposit(amountUsd);

        this.withdraw(amountUsd);
    }

    /*
     * Validates that the account number is not null, empty, or blank.
     * @param accountNumber the account number for the bank
     */
    private void isValidAccountNumber(final String accountNumber)
    {
        if (accountNumber == null)
        {
            throw new IllegalArgumentException("Account Number cannot be null");
        }

        final boolean emptyAccountNum;
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

    /*
     * Validates that the deposit amount is positive.
     * @param depositAmountUsd deposited amount of money in US Dollars
     */
    private void isValidDepositAmount(final double depositAmountUsd)
    {
        if (depositAmountUsd < MIN_BALANCE_AMOUNT_USD)
        {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    /*
     * Validates that the withdrawal amount is positive and that there are sufficient funds.
     * @param withdrawAmountUsd amount withdrawn in US Dollars
     */
    private void isValidWithdrawAmount(final double withdrawAmountUsd)
    {
        if (withdrawAmountUsd < MIN_BALANCE_AMOUNT_USD)
        {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (balanceUsd < withdrawAmountUsd)
        {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }
}
