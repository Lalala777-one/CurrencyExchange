package view;

import model.Role;
import model.User;
import service.AccountService;
import service.CurrencyService;
import service.TransactionService;
import service.UserService;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private User activeUser = new User();
   // private User registeredUser;
    private UserService userService;
    private CurrencyService currencyService;
    private AccountService accountService;
    private TransactionService transactionService;


   //todo
  //   private ExchangeRateService exchangeRateService;

// конструктор, принимает "сервисы"

    public Menu( UserService userService,
                CurrencyService currencyService,
                AccountService accountService,
                TransactionService transactionService) {
        this.userService = userService;
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public void run(){
        showGuestMenu();
    }

    // МЕНЮ ДЛЯ НЕ ЗАРЕГИСТРИРОВАННОГО ПОЛЬЗОВАТЕЛЯ
private void showGuestMenu(){
    if (activeUser.getRole() == Role.GUEST ) {
        while (true) {
            System.out.println(Color.YELLOW + "\033[1mДобро пожаловать!\n\033[0m" + Color.YELLOW +
                    "Для доступа ко всем функциям, пожалуйста, создайте учетную запись или войдите в существующую.\n" + Color.RESET);
            System.out.println("1. Войти");
            System.out.println("2. Зарегистрироваться");
            System.out.println("3. Посмотреть курс валют");
            System.out.println(Color.GREEN + "\n\033[1m\033[3mПожалуйста, выберите пункт меню:\033[0m");

            int choice = scanner.nextInt();
            scanner.nextLine();

            showGuestSubMenu(choice);
        }
    }
} // showGuestMenu


    private void showGuestSubMenu(int choice){

    switch (choice){
        case 1:
            // Todo
            //login();
            waitRead();
            break;
        case 2:
            // Todo
            //register();
            waitRead();
            break;
        case  3:
            // Todo
            // showCurrencyRates();
            waitRead();
            break;
        default:
            System.out.println(Color.GREEN + "\033[3mСделайте корректный выбор\\033[0m\n");
    }
    }//


   // МЕНЮ ДЛЯ ЗАРЕГИСТРИРОВАННОГО ПОЛЬЗОВАТЕЛЯ
    private void showUserMenu(){
        if (activeUser.getRole() == Role.USER) {
            while (true) {
                System.out.println(Color.YELLOW + "\033[1mДобро пожаловать в главное меню пользователя\033[0m");
                System.out.println("1. Посмотреть курс валют");
                System.out.println("2. Посмотреть баланс");
                System.out.println("3. Открыть новый счет");
                System.out.println("4. Пополнить счет");
                System.out.println("5. Снять деньги со счета");
                System.out.println("6. Закрыть счет");
                System.out.println("7. Осуществить обмен валюты");
                System.out.println("8. Посмотреть историю всех транзакций");
                System.out.println("9. Logout");

                System.out.println(Color.GREEN + "\n\033[3mСделайте ваш выбор:\033[0m");

                int choice = scanner.nextInt();
                scanner.nextLine();

                showUserSubMenu(choice);
            }
        }
    } // showUserMenu

    private void showUserSubMenu(int choice) {
   switch (choice){
       case 1:
           // Todo
           // showCurrencyRates();
           waitRead();
           break;
       case 2:
           // Todo
           //showBalance();
           waitRead();
           break;
       case 3:
           // Todo
           // openAccount();
           waitRead();
           break;
       case 4:
           // Todo
           // depositMoney(); пополнить счет
           waitRead();
           break;
       case 5:
           // Todo
           //  withdrawMoney(); снять со счета
           waitRead();
           break;
       case 6:
           // Todo
           // closeAccount(); закрыть счет
           waitRead();
           break;
       case 7:
           // Todo
           // exchangeCurrency(); обменять валюту
           waitRead();
           break;
       case 8:
           // Todo
           // showTransactionHistory();
           waitRead();
           break;
       case 9:
           // Todo
           // logout();
           waitRead();
           break;
       default:
           System.out.println(Color.GREEN + "\033[3mСделайте корректный выбор\033[0m\n");

   }
    } // showUserSubMenu



// МЕНЮ АДМИНИСТРАТОРА
    private void showAdminMenu(){
        if (activeUser.getRole() == Role.ADMIN) {
            while (true) {
                System.out.println(Color.YELLOW + "\033[1mДобро пожаловать в меню администратора\\033[0m");
                System.out.println("1. Просмотр списка пользователей");
                System.out.println("2. Просмотр доступных валют");
                System.out.println("3. Добавление новой валюты");
                System.out.println("4. Удаление валюты");
                System.out.println(Color.GREEN + "\n\033[3mСделайте ваш выбор:\033[0m");

                int choice = scanner.nextInt();
                scanner.nextLine();

                showAdminSubMenu(choice);
            }
        }
    }

    private void showAdminSubMenu(int choice) {
    switch (choice){
        case 1:
            // Todo
            // method
            /*

      /* Метод показать всех юзеров

        List<User> users = userRepo.showAllUsers();
        System.out.println(Color.BLUE + "\t\t\t\033[1mСписок всех пользователей\033[0m" + Color.RESET);
        System.out.printf( "%-10s%-20s%-30s%n", "Id", "Имя", "email");
        System.out.println(Color.BLUE + "__________________________________________________" + Color.RESET);
        for (User user : users) {
            System.out.println(user);
        }
        */
            waitRead();
            break;
        case 2:
            // Todo
            // method
            waitRead();
            break;
        case 3:
            // Todo
            // method
            waitRead();
            break;
        case 4:
            // Todo
            // method
            waitRead();
            break;
    }
    }


    private void waitRead() {
        System.out.println(Color.CYAN + "\nНажмите Enter для продолжения" + Color.RESET);
        scanner.nextLine();
    }


}
