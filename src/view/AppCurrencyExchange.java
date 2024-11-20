package view;

import model.Account;
import model.User;
import repository.*;
import service.*;

import java.util.List;

/*
\033[1m — активирует жирный шрифт.
\033[0m — сбрасывает все стили, включая жирный шрифт.
\033[3m — включает курсивный шрифт
\033[1m\033[3m жирный и курсив
 */

public class AppCurrencyExchange {
    public static void main(String[] args) {

        AccountRepo accountRepo = new AccountRepoImpl();
        CurrencyRepo currencyRepo = new CurrencyRepoImpl();
        ExchangeRateRepo exchangeRateRepo = new ExchangeRateRepoImpl();
        TransactionRepo transactionRepo = new TransactionRepoImpl();
        UserRepo userRepo = new UserRepoImpl();

        AccountService accountService = new AccountServiceImpl(accountRepo, currencyRepo, userRepo);

        CurrencyService currencyService = new CurrencyServiceImpl(currencyRepo);

        TransactionService transactionService = new TransactionServiceImpl(exchangeRateRepo, transactionRepo, accountRepo, userRepo);

        ExchangeRateServiceImpl exchangeRateService = new ExchangeRateServiceImpl(exchangeRateRepo);

        UserService userService = new UserServiceImpl(userRepo);

        userRepo.addAdminUser("adminemail@gmail.com", "Admin1234!", "Админ");


        Menu menu = new Menu(userService, currencyService, accountService, transactionService, exchangeRateService, currencyRepo);

        menu.run();

    }
}

