public class BankAccount
{
    private double       balance;
    private final String accountNumber;

    BankAccount(final String accountNumber,
                final double balance)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    private static double deposit(final double amount)
    {
        return 2.0;
    }

    public static double getBalanceUsd()
    {

        return 0;
    }

    private double withdraw(final double amount)
    {
         double newBalance = this.balance - amount;
         return newBalance;
    }

    public void transferToBank(final BankAccount account, final String accountNumber, final int i)
    {
    }
}
