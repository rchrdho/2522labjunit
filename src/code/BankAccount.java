public class BankAccount
{
    private double       balance;
    private final String accountNumber;

    BankAccount(final String accountNumber,
                final double balance)
    {
        this.accountNumber = accountNumber;
        this.balance       = balance;
    }

    public void deposit(final double depositAmount)
    {
        balance += depositAmount;
    }

    public final double getBalanceUsd()
    {
        return balance;
    }

    public final void withdraw(final double withdrawAmount)
    {
        balance -= withdrawAmount;
    }

    public void transferToBank(final BankAccount account,
                               final String accountNumber,
                               final int bankId)
    {

    }

    public static void main(final String[] args)
    {

    }
}
