import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

public class BankApplicationTests
{
    private Bank bank1;
    private Bank bank2;
    private BankAccount account1;
    private BankAccount account2;
    private BankAccount account3;
    private BankAccount account4;
    private BankAccount emptyAccount;

    @BeforeEach
    void setUp()
    {
        bank1 = new Bank();
        bank2 = new Bank();
        account1 = new BankAccount("12345", 1000);
        account2 = new BankAccount("67890", 500);
        account3 = new BankAccount("54321", 300);
        account4 = new BankAccount("98765", 700);
        emptyAccount = new BankAccount("98787", 0);
        bank1.addAccount(account1);
        bank2.addAccount(account2);
        bank1.addAccount(account1);
        bank2.addAccount(account2);
        bank1.addAccount(account3);
        bank1.addAccount(account4);
    }

    @Test
    void depositIncreasesBalanceAndVerify()
    {
        account1.deposit(200);
        assertEquals(1200, account1.getBalanceUsd());
        account2.deposit(300);
        assertEquals(800, account2.getBalanceUsd());
    }

    @Test
    void withdrawDecreasesBalanceAndVerify()
    {
        account1.withdraw(200);
        assertEquals(800, account1.getBalanceUsd());
        account2.withdraw(100);
        assertEquals(400, account2.getBalanceUsd());
    }

    @Test
    void cannotWithdrawMoreThanBalanceAndHandleException()
    {
        IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () -> account1.withdraw(1200));
        assertEquals("Insufficient funds", exception1.getMessage());
        IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () -> account2.withdraw(600));
        assertEquals("Insufficient funds", exception2.getMessage());
    }

    @Test
    void addingAndRetrievingAccountFromBank()
    {
        BankAccount newAccount = new BankAccount("54321", 100);
        bank2.addAccount(newAccount);
        assertEquals(newAccount, bank2.retrieveAccount("54321"));
        BankAccount newAccount2 = new BankAccount("11111", 300);
        bank1.addAccount(newAccount2);
        assertEquals(newAccount2, bank1.retrieveAccount("11111"));
    }

    @Test
    void transferBetweenBankAccountsAndVerifyBalances()
    {
        account1.transferToBank(account2, "12345", 200);
        assertEquals(800, account1.getBalanceUsd());
        assertEquals(700, account2.getBalanceUsd());
        account2.transferToBank(account1, "67890", 100);
        assertEquals(900, account1.getBalanceUsd());
        assertEquals(600, account2.getBalanceUsd());
    }

    @Test
    void totalBalanceCalculationForBanks()
    {
        assertEquals(1000, bank1.totalBalanceUsd());
        assertEquals(500, bank2.totalBalanceUsd());
        bank1.addAccount(new BankAccount("33333", 200));
        assertEquals(1200, bank1.totalBalanceUsd());
    }

    @Test
    void handlingInvalidAccountRetrieval()
    {
        IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () ->
                        bank1.retrieveAccount("99999"));
        assertEquals("Account not found", exception1.getMessage());
        IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () ->
                        bank2.retrieveAccount("00000"));
        assertEquals("Account not found", exception2.getMessage());
    }

// Additional tests can include:
// - Checking the initial balance correctness.
// - Handling invalid operations.
// - Summing balances from multiple accounts in a single bank.

    @Test
    void checkInitialBalanceCorrectness()
    {
        assertEquals(1000, account1.getBalanceUsd(), "Initial balance for account1 should be 1000");
        assertEquals(500, account2.getBalanceUsd(), "Initial balance for account2 should be 500");
    }

    @Test
    void cannotWithdrawFromEmptyAccount()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> emptyAccount.withdraw(100));
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void cannotTransferToSelf()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> account1.transferToBank(account1, "12345", 100));
        assertEquals("Cannot transfer to the same account", exception.getMessage());
    }

    @Test
    void totalBalanceCalculationForMultipleAccounts()
    {
        double expectedTotal = account1.getBalanceUsd() + account3.getBalanceUsd() + account4.getBalanceUsd();
        assertEquals(expectedTotal, bank1.totalBalanceUsd(),
                "Total balance for bank1 should match the sum of all accounts");
    }

    @Test
    void totalBalanceCalculationAfterMultipleDeposits()
    {
        account1.deposit(500);
        account2.deposit(300);
        assertEquals(1800, bank1.totalBalanceUsd() + bank2.totalBalanceUsd());
    }

    @Test
    void cannotAddAccountWithNullId()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bank1.addAccount(new BankAccount(null, 100)));
        assertEquals("Account ID cannot be null", exception.getMessage());
    }

    @Test
    void cannotAddAccountWithEmptyId()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bank1.addAccount(new BankAccount("", 100)));
        assertEquals("Account ID cannot be empty", exception.getMessage());
    }

    @Test
    void cannotDepositNegativeAmount()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> account1.deposit(-100));
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }

    @Test
    void cannotWithdrawNegativeAmount()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> account1.withdraw(-100));
        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }
}