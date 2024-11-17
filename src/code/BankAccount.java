public class BankAccount
{
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
        isMatchingAccountNumber(accountNumber);

        this.deposit(amount);
    }

    private void isValidAccountNumber(final String accountNumber)
    {
        boolean emptyAccountNum = accountNumber.isBlank();

        if(accountNumber.isEmpty() && !emptyAccountNum)
        {
            throw new IllegalArgumentException("Invalid Account Number");
        }
    }

    private void isMatchingAccountNumber(final String accountNumber)
    {
        isValidAccountNumber(accountNumber);

        if(!accountNumber.equalsIgnoreCase(this.accountNumber))
        {
            throw new IllegalArgumentException("Account not found");
        }
    }

    private void isValidDepositAmount(final double depositAmount)
    {
        if(depositAmount < 0)
        {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    private void isValidWithdrawAmount(final double withdrawAmount)
    {
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
