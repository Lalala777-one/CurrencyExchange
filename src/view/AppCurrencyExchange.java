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
        ExchangeRateRepo exchangeRateRepo = new ExchangeRateRepoImpl();
        TransactionRepo transactionRepo = new TransactionRepoImpl();
        UserRepo userRepo = new UserRepoImpl();

        AccountService accountService = new AccountServiceImpl();
        CurrencyService currencyService = new CurrencyServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl();
        UserService userService = new UserServiceImpl(userRepo);


        /*
        List<User> users = userRepo.showAllUsers();
        System.out.println(Color.BLUE + "\t\t\t\033[1mСписок всех пользователей\033[0m" + Color.RESET);
        users.forEach(System.out::println);
         */


        Menu menu = new Menu(userService, currencyService, accountService, transactionService);

        menu.run();

        }
    }


