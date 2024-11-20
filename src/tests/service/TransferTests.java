package tests.service;

import org.junit.jupiter.api.*;
import model.*;
import repository.*;
import service.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TransferTests {

    private AccountRepo accountRepo;
    private ExchangeRateRepo exchangeRateRepo;
    private TransactionRepo transactionRepo;
    private TransactionServiceImpl transactionService;
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {

        accountRepo = new AccountRepoImpl();
        exchangeRateRepo = new ExchangeRateRepoImpl();
        transactionRepo = new TransactionRepoImpl();
        userRepo = new UserRepoImpl();

        transactionService = new TransactionServiceImpl(exchangeRateRepo, transactionRepo, accountRepo, userRepo);

        accountRepo.clear();
        exchangeRateRepo.clear();
        transactionRepo.clear();
        userRepo.clear();

        Account euroAccount = new Account(new Currency("Euro", "EUR"), 1001);
        Account usdAccount = new Account(new Currency("US Dollar", "USD"), 1001);
        Account yenAccount = new Account(new Currency("Japanese Yen", "JPY"), 1001);

        accountRepo.addAccount(euroAccount);
        accountRepo.addAccount(usdAccount);
        accountRepo.addAccount(yenAccount);

        // Перевіряємо, чи акаунти успішно додані
        List<Account> userAccounts = accountRepo.getAccountsByUserId(1001);
        assertNotNull(userAccounts, "Користувача з ID 1001 не знайдено");
        assertEquals(3, userAccounts.size(), "Неправильна кількість акаунтів для користувача 1001");

        // Перевіряємо конкретні акаунти за ID
        Account account1 = userAccounts.stream().filter(a -> a.getCurrency().getCode().equals("EUR")).findFirst().orElse(null);
        Account account2 = userAccounts.stream().filter(a -> a.getCurrency().getCode().equals("USD")).findFirst().orElse(null);
        Account account3 = userAccounts.stream().filter(a -> a.getCurrency().getCode().equals("JPY")).findFirst().orElse(null);

        assertNotNull(account1, "Акаунт з валютою EUR не знайдено");
        assertNotNull(account2, "Акаунт з валютою USD не знайдено");
        assertNotNull(account3, "Акаунт з валютою JPY не знайдено");

        assertNotNull(accountRepo.getAccountById(account1.getId()), "Акаунт 1 не додано до репозиторія");
        assertNotNull(accountRepo.getAccountById(account2.getId()), "Акаунт 2 не додано до репозиторія");
        assertNotNull(accountRepo.getAccountById(account3.getId()), "Акаунт 3 не додано до репозиторія");
    }

    @Test
    void testTransferEuroToDollar() {
//        Account fromAccount = accountRepo.getAccountById(1);
//        Account toAccount = accountRepo.getAccountById(2);
        Account fromAccount = accountRepo.getAccountsByUserId(1001).stream()
                .filter(a -> a.getCurrency().getCode().equals("EUR")).findFirst().orElse(null);
        Account toAccount = accountRepo.getAccountsByUserId(1001).stream()
                .filter(a -> a.getCurrency().getCode().equals("USD")).findFirst().orElse(null);

        assertNotNull(fromAccount, "Аккаунт відправника не знайдено (EUR)");
        assertNotNull(toAccount, "Аккаунт отримувача не знайдено (USD)");

        fromAccount.setBalance(100.0);
        toAccount.setBalance(50.0);

        System.out.println("From Account ID: " + fromAccount.getId());
        System.out.println("From Account Balance: " + fromAccount.getBalance());
        System.out.println("To Account ID: " + toAccount.getId());
        System.out.println("To Account Balance: " + toAccount.getBalance());

        exchangeRateRepo.addExchangeRate(new Currency("Euro", "EUR"), new Currency("US Dollar", "USD"), 0.92);

        transactionService.transfer(fromAccount.getId(), toAccount.getId(), 10.0);

        assertEquals(90.0, fromAccount.getBalance(), 0.001);  // 100 - 10 = 90
        assertEquals(59.2, toAccount.getBalance(), 0.001);   // 50 + (10 * 0.92) = 59.2

        assertEquals(1, transactionRepo.findAllTransactions().size());
    }

    @Test
    void testTransferYenToDollarThroughEuro() {
//        Account fromAccount = accountRepo.getAccountById(3);
//        Account toAccount = accountRepo.getAccountById(2);
        Account fromAccount = accountRepo.getAccountsByUserId(1001).stream()
                .filter(a -> a.getCurrency().getCode().equals("JPY")).findFirst().orElse(null);
        Account toAccount = accountRepo.getAccountsByUserId(1001).stream()
                .filter(a -> a.getCurrency().getCode().equals("USD")).findFirst().orElse(null);

        assertNotNull(fromAccount, "Аккаунт відправника не знайдено (JPY)");
        assertNotNull(toAccount, "Аккаунт отримувача не знайдено (USD)");

        fromAccount.setBalance(1000.0);
        toAccount.setBalance(50.0);

        System.out.println("From Account ID: " + fromAccount.getId());
        System.out.println("From Account Balance: " + fromAccount.getBalance());
        System.out.println("To Account ID: " + toAccount.getId());
        System.out.println("To Account Balance: " + toAccount.getBalance());

        exchangeRateRepo.addExchangeRate(new Currency("Japanese Yen", "JPY"), new Currency("Euro", "EUR"), 0.0075);
        exchangeRateRepo.addExchangeRate(new Currency("Euro", "EUR"), new Currency("US Dollar", "USD"), 0.92);

        transactionService.transfer(fromAccount.getId(), toAccount.getId(), 1000.0);  // 1000 JPY to USD

        assertEquals(0.0, fromAccount.getBalance(), 0.001);  // 1000 - 1000 = 0
        assertEquals(56.9, toAccount.getBalance(), 0.001);   // 50 + (1000 * 0.0075 * 0.92) = 56.9

        assertEquals(1, transactionRepo.findAllTransactions().size());
    }

    @AfterEach
    void tearDown() {
        accountRepo = null;
        exchangeRateRepo = null;
        transactionRepo = null;
        transactionService = null;
        userRepo = null;
    }
}