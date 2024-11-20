package tests.service.transaction;

import model.*;
import repository.*;
import service.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class WithdrawalTests {

    private AccountRepo accountRepo;
    private UserRepo userRepo;
    private ExchangeRateRepo exchangeRateRepo;
    private TransactionRepo transactionRepo;
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        accountRepo = new AccountRepoImpl();
        userRepo = new UserRepoImpl();
        exchangeRateRepo = new ExchangeRateRepoImpl();
        transactionRepo = new TransactionRepoImpl();

        transactionService = new TransactionServiceImpl(exchangeRateRepo, transactionRepo, accountRepo, userRepo);

        accountRepo.clear();
        exchangeRateRepo.clear();
        transactionRepo.clear();
        userRepo.clear();
    }

//    @Test
//    void testWithdraw_ValidData_Success() {
//        int userId = 1;
//        int accountId = 1;
//        double initialBalance = 200.0;
//        double withdrawAmount = 100.0;
//
//        User testUser = new User("test@example.com", "Test User", "Anna");
//        testUser.setId(userId);
//        userRepo.addUser(testUser);
//
//        Account account = new Account(new Currency("Euro", "EUR"), userId);
//        account.setId(accountId);
//        account.setBalance(initialBalance);
//        accountRepo.addAccount(account);
//
//        exchangeRateRepo.addExchangeRate(new Currency("Euro", "EUR"), new Currency("Euro", "EUR"), 1.0);
//
//        transactionService.withdraw(userId, accountId, withdrawAmount);
//
//        assertEquals(100.0, account.getBalance(), "Баланс після зняття має бути 100.0");
//
//        assertEquals(1, transactionRepo.findAllTransactions().size(), "Транзакція зняття не збережена");
//
//        Transaction transaction = transactionRepo.findAllTransactions().get(0);
//        assertNotNull(transaction, "Транзакція повинна бути знайдена");
//        assertEquals(accountId, transaction.getToAccount().getId(), "ID рахунку в транзакції має співпадати з ID рахунку");
//        assertEquals(withdrawAmount, transaction.getToAccount(), "Сума зняття в транзакції має бути правильною");
//        assertEquals(1.0, transaction.getExchangeRate(), "Курс обміну в транзакції має бути правильним");
//    }

    @Test
    void testWithdraw_InsufficientBalance_ThrowsException() {
        int userId = 1;
        int accountId = 1;
        double initialBalance = 50.0;
        double withdrawAmount = 100.0;

        User testUser = new User("test@example.com", "Test User", "Anna");
        testUser.setId(userId);
        userRepo.addUser(testUser);

        Account account = new Account(new Currency("Euro", "EUR"), userId);
        account.setId(accountId);
        account.setBalance(initialBalance);
        accountRepo.addAccount(account);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.withdraw(userId, accountId, withdrawAmount);
        }, "Недостаточно средств на счету.");
    }

    @Test
    void testWithdraw_InvalidUserId_ThrowsException() {
        int userId = 1;
        int accountId = 1;
        double withdrawAmount = 100.0;

        Account account = new Account(new Currency("Euro", "EUR"), userId);
        account.setId(accountId);
        accountRepo.addAccount(account);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.withdraw(999, accountId, withdrawAmount);
        }, "Пользователь с указанным идентификатором не существует.");
    }

    @Test
    void testWithdraw_AccountNotOwnedByUser_ThrowsException() {
        int userId = 1;
        int accountId = 1;
        double withdrawAmount = 100.0;

        User testUser = new User("test@example.com", "Test User", "Anna");
        testUser.setId(userId);
        userRepo.addUser(testUser);

        Account account = new Account(new Currency("Euro", "EUR"), 999); // Належить іншому користувачеві
        account.setId(accountId);
        account.setBalance(200.0);
        accountRepo.addAccount(account);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.withdraw(userId, accountId, withdrawAmount);
        }, "Счет с ID " + accountId + " не принадлежит пользователю с ID " + userId);
    }

    @Test
    void testWithdraw_NegativeAmount_ThrowsException() {
        int userId = 1;
        int accountId = 1;
        double withdrawAmount = -50.0;

        User testUser = new User("test@example.com", "Test User", "Anna");
        testUser.setId(userId);
        userRepo.addUser(testUser);

        Account account = new Account(new Currency("Euro", "EUR"), userId);
        account.setId(accountId);
        account.setBalance(200.0);
        accountRepo.addAccount(account);

        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.withdraw(userId, accountId, withdrawAmount);
        }, "Сумма снятия должна быть больше нуля.");
    }

}
