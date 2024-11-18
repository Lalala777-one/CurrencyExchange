package view;

import model.Account;
import repository.AccountRepo;
import repository.ExchangeRateRepo;
import repository.TransactionRepo;
import repository.UserRepo;
import service.*;

import java.util.List;

public class AppCurrencyExchange {
    public static void main(String[] args) {


        AccountRepo accountRepo = new AccountRepo();
        ExchangeRateRepo exchangeRateRepo = new ExchangeRateRepo();
        TransactionRepo transactionRepo = new TransactionRepo();
        UserRepo userRepo = new UserRepo();

        AccountService accountService = new AccountServiceImpl();
        CurrencyService currencyService = new CurrencyServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl();
        UserService userService = new UserServiceImpl();

        Menu menu = new Menu(userService, currencyService, accountService, transactionService);

           menu.run();
        }
    }


