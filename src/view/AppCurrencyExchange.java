package view;

import model.Account;
import repository.*;
import service.*;

import java.util.List;

public class AppCurrencyExchange {
    public static void main(String[] args) {


        AccountRepo accountRepo = new AccountRepoImpl();
        CurrencyRepo currencyRepo = new CurrencyRepoImpl();

        ExchangeRateRepo exchangeRateRepo = new ExchangeRateRepoImpl();
        TransactionRepo transactionRepo = new TransactionRepoImpl();
        UserRepo userRepo = new UserRepoImpl();


        AccountService accountService = new AccountServiceImpl(accountRepo, currencyRepo);
        CurrencyService currencyService = new CurrencyServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl();
        UserService userService = new UserServiceImpl();

        Menu menu = new Menu(userService, currencyService, accountService, transactionService);

           menu.run();
        }
    }


