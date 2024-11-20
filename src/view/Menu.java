package view;

import exceptionsUtils.*;
import model.*;
import service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {


    private static int currentUserId;
    private final Scanner scanner = new Scanner(System.in);
    private User activeUser = new User();
    // private User registeredUser;
    private UserService userService;
    private CurrencyService currencyService;
    private AccountService accountService;
    private TransactionService transactionService;
    private ExchangeRateService exchangeRateService;

// конструктор, принимает "сервисы"

    public Menu(UserService userService,
                CurrencyService currencyService,
                AccountService accountService,
                TransactionService transactionService,
                ExchangeRateService exchangeRateService) {
        this.userService = userService;
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.exchangeRateService = exchangeRateService;
    }

    public void run() {
        // showGuestMenu();
        showMenuBasedOnRole(activeUser); // Вызов метода, который покажет меню в зависимости от роли

    }

    // Метод для вывода меню в зависимости от роли
    public void showMenuBasedOnRole(User user) {
        if (user.getRole() == Role.ADMIN) {
            showAdminMenu();
        } else if (user.getRole() == Role.USER) {
            showUserMenu();
        } else {
            showGuestMenu();
        }
    }

    // МЕНЮ ДЛЯ НЕ ЗАРЕГИСТРИРОВАННОГО ПОЛЬЗОВАТЕЛЯ
    private void showGuestMenu() {
        if (activeUser.getRole() == Role.GUEST) {
            while (true) {
                System.out.println(Color.YELLOW + "\033[1mДобро пожаловать!\n\033[0m" + Color.YELLOW +
                        "Для доступа ко всем функциям, пожалуйста, создайте учетную запись или войдите в существующую.\n" + Color.RESET);
                System.out.println("1. Войти");
                System.out.println("2. Зарегистрироваться");
                System.out.println("3. Посмотреть курс валют");
                System.out.println(Color.GREEN + "\n\033[1m\033[3mПожалуйста, выберите пункт меню:\033[0m" + Color.RESET);

                int choice = scanner.nextInt();
                scanner.nextLine();

                showGuestSubMenu(choice);
            }
        }
    } // showGuestMenu


    private void showGuestSubMenu(int choice) {

        switch (choice) {
            case 1:
                loginUser();  // Переход к процессу логина
                waitRead();
                break;
            case 2:
                registerUser();
                waitRead();
                break;
            case 3:
                showCurrencyRates();
                waitRead();
                break;
            default:
                System.out.println(Color.GREEN + "\033[3mСделайте корректный выбор\\033[0m\n");
        }
    }//


    // МЕНЮ ДЛЯ ЗАРЕГИСТРИРОВАННОГО ПОЛЬЗОВАТЕЛЯ
    private void showUserMenu() {
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
        switch (choice) {
            case 1:
                showCurrencyRates();
                waitRead();
                break;
            case 2:
                showBalance();
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
                logOut();
                waitRead();
                break;
            default:
                System.out.println(Color.GREEN + "\033[3mСделайте корректный выбор\033[0m\n");

        }
    } // showUserSubMenu


    public void showBalance() {
        try {
            // Получаем все аккаунты текущего пользователя
            List<Account> accounts = accountService.getAllAccountsByUserId(currentUserId);

            // Если у пользователя нет аккаунтов, сообщаем об этом
            if (accounts.isEmpty()) {
                System.out.println("У вас ещё нет аккаунтов.");
                return;
            }

            // Если у пользователя только один аккаунт, используем его напрямую
            if (accounts.size() == 1) {
                Account account = accounts.get(0);
                try {
                    double balance = accountService.checkBalance(currentUserId, account.getId());
                    System.out.println("Ваш баланс на аккаунте ID " + account.getId() + ": " + balance);
                } catch (AccountException e) {
                    System.err.println("Ошибка при получении баланса: " + e.getMessage());
                }
            } else {
                // Если аккаунтов несколько, просим пользователя выбрать
                System.out.println("Выберите аккаунт для просмотра баланса:");
                for (int i = 0; i < accounts.size(); i++) {
                    System.out.println((i + 1) + ". Аккаунт ID: " + accounts.get(i).getId());
                }

                int choice = scanner.nextInt();
                if (choice <= 0 || choice > accounts.size()) {
                    System.out.println("Неверный выбор.");
                    return;
                }

                Account selectedAccount = accounts.get(choice - 1);
                try {
                    double balance = accountService.checkBalance(currentUserId, selectedAccount.getId());
                    System.out.println("Ваш баланс на аккаунте ID " + selectedAccount.getId() + ": " + balance);
                } catch (AccountException e) {
                    System.err.println("Ошибка при получении баланса: " + e.getMessage());
                }
            }
        } catch (AccountException e) {
            // Обработка исключения, если пользователь не существует или другие проблемы с аккаунтами
            System.err.println("Ошибка при получении списка аккаунтов: " + e.getMessage());
        } catch (Exception e) {
            // Обработка других неожиданных исключений
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }




    // МЕНЮ АДМИНИСТРАТОРА
    private void showAdminMenu() {
        if (activeUser.getRole() == Role.ADMIN) {
            while (true) {
                System.out.println(Color.YELLOW + "\033[1mДобро пожаловать в меню администратора\033[0m");
                System.out.println("1. Просмотр списка пользователей");
                System.out.println("2. Просмотр доступных валют");
                System.out.println("3. Добавление новой валюты");
                System.out.println("4. Удаление валюты");
                System.out.println("5. Logout");
                System.out.println(Color.GREEN + "\n\033[3mСделайте ваш выбор:\033[0m" + Color.RESET);

                int choice = scanner.nextInt();
                scanner.nextLine();

                showAdminSubMenu(choice);
            }
        }
    }


    private void showAdminSubMenu(int choice) {
        switch (choice) {
            case 1:
                showAllUsersList();

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
            case 5:
                logOut();
                waitRead();
            default:
                System.out.println(Color.GREEN + "\033[3mСделайте корректный выбор\033[0m\n" + Color.RESET);
        }
    }


    // МЕТОДЫ

    public void loginUser() {
        boolean loginSuccess = false; // Флаг успешного логина
        while (!loginSuccess) {
            System.out.println("Введите ваш email (или '0' для выхода):");
            String email = scanner.nextLine();

            // Если пользователь захочет вернуться
            if (email.equals("0")) {
                System.out.println("Возвращаемся в предыдущее меню...");
                showGuestMenu(); // Возвращаемся в предыдущее меню
                return;
            }

            System.out.println("Введите ваш пароль:");
            String password = scanner.nextLine();

            try {
                // Попытка залогиниться через сервис
                if (userService.loginUser(email, password)) {
                    activeUser = userService.getActiveUser();  // Устанавливаем активного пользователя
                    currentUserId = activeUser.getId(); // Устанавливаем ID текущего пользователя
                    showMenuBasedOnRole(activeUser);  // Показываем меню в зависимости от роли
                    loginSuccess = true; // Логин успешен, выходим из цикла
                }
            } catch (UserException e) {
                System.out.println(Color.RED + "Ошибка: " + e.getMessage() + Color.RESET);
                System.out.println("Попробуйте снова или введите '0' для выхода в меню.");
            }
        }
    }


    // метод для регистрации нового пользователя
    public void registerUser() {
        System.out.println("Введите ваше имя:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();


        try {
            // Пытаемся зарегистрировать пользователя через сервис
            boolean isRegistered = userService.registerUser(email, password, name);

            if (isRegistered) {
                System.out.println(Color.GREEN + "Регистрация прошла успешно!" + Color.RESET);

                // Пытаемся залогинить пользователя сразу после регистрации
                // Логиним пользователя с новыми данными
                if (userService.loginUser(email, password)) {
                    activeUser = userService.getActiveUser();  // Устанавливаем активного пользователя
                    currentUserId = activeUser.getId(); // Устанавливаем ID текущего пользователя после регистрации
                    showMenuBasedOnRole(activeUser);  // Показываем меню в зависимости от роли
                }
            }
        } catch (UserException e) {
            System.out.println(Color.RED + "Ошибка: \n" + e.getMessage() + Color.RESET);
        } catch (EmailValidateException e) {
            System.out.println(Color.RED + "Ошибка: \n" + e.getMessage() + Color.RESET);
        } catch (PasswordValidateException e) {
            System.out.println(Color.RED + "Ошибка: \n" + e.getMessage() + Color.RESET);
        } catch (Exception e) {
            // Обработка всех других исключений
            System.out.println(Color.RED + "Произошла неизвестная ошибка: " + e.getMessage() + Color.RESET);
        }

        waitRead(); // Ожидаем ввода
    }

    public void showAllUsersList() {
        try {
            // Получаем список пользователей через сервис
            List<User> users = userService.showAllUsers();  // Это может вызвать исключение UserException

            System.out.println(Color.BLUE + "\t\t\t\033[1mСписок всех пользователей\033[0m" + Color.RESET);
            System.out.printf("%-10s%-20s%-30s%n", "Id", "Имя", "email");
            System.out.println(Color.BLUE + "__________________________________________________" + Color.RESET);

            // Выводим каждого пользователя
            for (User user : users) {
                System.out.println(user);
            }
        } catch (UserException e) {
            // Обрабатываем исключение и выводим сообщение пользователю
            System.out.println(Color.RED + "Ошибка: " + e.getMessage() + Color.RESET);

        }
    }

    // Метод для выхода
    public void logOut() {
        activeUser = new User();  // Сброс активного пользователя
        showGuestMenu();  // Показать меню для гостя
    }


    private void waitRead() {
        System.out.println(Color.CYAN + "\nНажмите Enter для продолжения" + Color.RESET);
        scanner.nextLine();
    }

    public void showCurrencyRates() {
        try {
            System.out.println(Color.BLUE + "\t\t\033[1mКурсы валют\033[0m" + Color.RESET);
            System.out.printf("%-17s%-15s%n", "Валюта", "Курс к EUR");
            System.out.println(Color.BLUE + "___________________________" + Color.RESET);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss (E. dd.MM.yy)");

            Map<Currency, ExchangeRate> rates = exchangeRateService.getAllExchangeRates();

            for (Map.Entry<Currency, ExchangeRate> entry : rates.entrySet()) {
                Currency currency = entry.getKey();
                ExchangeRate exchangeRate = entry.getValue();

                System.out.printf("%-20s%-15.2f%n",
                        currency.getCode(),
                        exchangeRate.getRate()
                );
            }

            System.out.println(Color.BLUE + "___________________________" + Color.RESET);

            LocalDateTime currentTime = LocalDateTime.now();
            String formattedDate = currentTime.format(formatter);
            System.out.println("Курсы валют обновлены на: \n\t" + formattedDate);

        } catch (Exception e) {
            System.out.println(Color.RED + "Произошла ошибка при получении курсов валют: " + e.getMessage() + Color.RESET);
        }

        System.out.println(Color.BLUE + "\n0. Вернуться в предыдущее меню" + Color.RESET);
        System.out.println(Color.GREEN + "\nСделайте выбор:" + Color.RESET);
        int input = scanner.nextInt();
        if (input == 0) {
            return;
        }
    }
}