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

    private static double depositMoney(final double amount)
    {
        return 2.0;
    }

    private static void checkBalance()
    {

    }

    private static double withdrawMoney(final double amount)
    {
         double newBalance = balance - amount;
         return newBalance;
    }

}
