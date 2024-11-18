import java.util.HashMap;
import java.util.Map;

public class Bank
{
    private final Map<String, BankAccount> accounts;

    public Bank()
    {
        this.accounts = new HashMap<>();
    }

    public void addAccount(final BankAccount bankAccount)
    {
        isValidAccountNumber(bankAccount.getAccountNumber());

        accounts.put(bankAccount.getAccountNumber(), bankAccount);
    }

    public BankAccount retrieveAccount(final String accountNumber)
    {
        isValidAccountNumber(accountNumber);

        isValidAccount(accountNumber);

        return accounts.get(accountNumber);
    }

    private void isValidAccountNumber(final String accountNumber)
    {
        if(accountNumber == null)
        {
            throw new IllegalArgumentException("Account Number cannot be null");
        }

        if(accountNumber.isEmpty())
        {
            throw new IllegalArgumentException("Account Number cannot be empty");
        }
    }

    private void isValidAccount(final String accountNumber)
    {
        isValidAccountNumber(accountNumber);

        if(!accounts.containsKey(accountNumber))
        {
            throw new IllegalArgumentException("Account not found");
        }
    }

    public double totalBalanceUsd()
    {
        return accounts.values().stream()
                                .mapToDouble(BankAccount::getBalanceUsd)
                                .sum();
    }
}
