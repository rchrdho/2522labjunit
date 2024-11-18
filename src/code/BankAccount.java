public class BankAccount
{
    private static final int MIN_BALANCE_AMOUNT_USD = 0;

    private double       balance;
    private final String accountNumber;

    BankAccount(final String accountNumber,
                final double balance)
    {
        isValidAccountNumber(accountNumber);

        this.accountNumber = accountNumber;
        this.balance       = balance;
    }

    public void deposit(final double depositAmount)
    {
        isValidDepositAmount(depositAmount);

        balance += depositAmount;
    }

    public final String getAccountNumber()
    {
        return accountNumber;
    }

    public final double getBalanceUsd()
    {
        return balance;
    }

    public final void withdraw(final double withdrawAmount)
    {
        isValidWithdrawAmount(withdrawAmount);

        balance -= withdrawAmount;
    }

    public final void transferToBank(final BankAccount account,
                                     final String      accountNumber,
                                     final int         amount)
    {
        isMatchingAccountNumber(account, accountNumber);

        account.deposit(amount);

        this.withdraw(amount);
    }

    private void isValidAccountNumber(final String accountNumber)
    {
        if(accountNumber == null)
        {
            throw new IllegalArgumentException("Account Number cannot be null");
        }

        boolean emptyAccountNum = accountNumber.isBlank();

        if(accountNumber.isEmpty() && !emptyAccountNum)
        {
            throw new IllegalArgumentException("Account Number cannot be empty");
        }
    }

    private void isMatchingAccountNumber(final BankAccount account,
                                         final String accountNumber)
    {
        isValidAccountNumber(accountNumber);

        if(account.getAccountNumber().equals(accountNumber))
        {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        if(!accountNumber.equalsIgnoreCase(this.accountNumber))
        {
            throw new IllegalArgumentException("Account not found");
        }
    }

    private void isValidDepositAmount(final double depositAmount)
    {
        if(depositAmount < MIN_BALANCE_AMOUNT_USD)
        {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    private void isValidWithdrawAmount(final double withdrawAmount)
    {
        if(withdrawAmount < MIN_BALANCE_AMOUNT_USD)
        {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if(balance < withdrawAmount)
        {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    public static void main(final String[] args)
    {
        BankAccount account;

        account = new BankAccount("12312", 23);
    }
}
