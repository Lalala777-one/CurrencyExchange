package tests.service;

import model.*;
import repository.*;
import service.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DepositTests {

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
//    void testDeposit_ValidData_Success() {
//        // Ініціалізація даних для тесту
//        int userId = 1;
//        int accountId = 1;
//        double amount = 100.0;
//
//        User testUser = new User("test@example.com", "Test User", "Anna");
//        testUser.setId(userId);
//        userRepo.addUser(testUser);
//
//        Account account = new Account(new Currency("Euro", "EUR"), userId);
//        account.setId(accountId);
//        accountRepo.addAccount(account);
//
//        exchangeRateRepo.addExchangeRate(new Currency("Euro", "EUR"), new Currency("Japanese Yen", "JPY"), 0.0075); // EUR до JPY
//        exchangeRateRepo.addExchangeRate(new Currency("Japanese Yen", "JPY"), new Currency("Euro", "EUR"), 133.33); // JPY до EUR (зворотній курс)
//
//        transactionService.deposit(userId, accountId, amount);
//
//        assertEquals(100.0, account.getBalance(), "Баланс після поповнення повинен бути 100.0");
//
//        assertEquals(1, transactionRepo.findAllTransactions().size(), "Транзакція поповнення не збережена");
//
//        Transaction transaction = transactionRepo.findAllTransactions().get(0);
//        assertNotNull(transaction, "Транзакція повинна бути знайдена");
////        assertEquals(accountId, transaction.getAccount().getId(), "ID рахунку в транзакції має співпадати з ID рахунку");
////        assertEquals(amount, transaction.getAmount(), "Сума поповнення в транзакції має бути правильною");
//        assertEquals(0.75, transaction.getToAmount(), "Конвертована сума в транзакції повинна бути правильною (100 * 0.0075 = 0.75)");
//        assertEquals(0.0075, transaction.getExchangeRate(), "Курс обміну в транзакції має бути правильним");
//    }


    @Test
    void testDeposit_InvalidUserId_ThrowsException() {
        int invalidUserId = -1;
        int accountId = 123;
        double amount = 100.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(invalidUserId, accountId, amount);
        });
        assertEquals("Идентификатор пользователя должен быть положительным числом.", exception.getMessage());
    }

    @Test
    void testDeposit_UserNotFound_ThrowsException() {
        int userId = 133;
        int accountId = 1;
        double amount = 100.0;

        User testUser = new User("test@example.com", "Test User", "Anna");
        testUser.setId(userId);
        userRepo.addUser(testUser);
        userRepo.deleteUser(userId);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(userId, accountId, amount);
        });
        assertEquals("Пользователь с указанным идентификатором не существует.", exception.getMessage());
    }

    @Test
    void testDeposit_InvalidAccountId_ThrowsException() {
        int userId = 1;
        int invalidAccountId = -1;
        double amount = 100.0;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(userId, invalidAccountId, amount);
        });
        assertEquals("Идентификатор счета должен быть положительным числом.", exception.getMessage());
    }

    @Test
    void testDeposit_AccountNotFound_ThrowsException() {
        int userId = 1;
        int accountId = 1234;
        double amount = 100.0;

        User testUser = new User("test@example.com", "Test User", "Anna");
        testUser.setId(userId);
        userRepo.addUser(testUser);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(userId, accountId, amount);
        });
        assertEquals("Счет с указанным идентификатором не найден.", exception.getMessage());
    }

    @Test
    void testDeposit_AccountDoesNotBelongToUser_ThrowsException() {
        int userId = 1;
        int accountId = 124;
        double amount = 100.0;

        User testUser = new User("test@example.com", "Test User", "Anna");
        testUser.setId(userId);
        userRepo.addUser(testUser);

        User anotherUser = new User("another@example.com", "Another User", "Bob");
        anotherUser.setId(2);
        userRepo.addUser(anotherUser);

        Account testAccount = new Account(new Currency("Euro", "EUR"), 2);
        testAccount.setId(accountId);
        accountRepo.addAccount(testAccount);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(userId, accountId, amount);
        });
        assertEquals("Счет с ID " + accountId + " не принадлежит пользователю с ID " + userId, exception.getMessage());
    }

    @Test
    void testDeposit_AmountLessThanOrEqualToZero_ThrowsException() {
        int userId = 1;
        int accountId = 123;
        double amount = -100.0;

        User testUser = new User("test@example.com", "Test User", "Anna");
        testUser.setId(1);
        userRepo.addUser(testUser);

        Account testAccount = new Account(new Currency("Euro", "EUR"), userId);
        testAccount.setId(accountId);
        accountRepo.addAccount(testAccount);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.deposit(userId, accountId, amount);
        });
        assertEquals("Сумма пополнения должна быть больше нуля.", exception.getMessage());
    }
}
