package view;

import exceptionsUtils.*;
import model.*;
import service.*;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
                TransactionService transactionService, ExchangeRateService exchangeRateService) {



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

    // МЕТОДЫ ДЛЯ ОБМЕНА ВАЛЮТЫ

    public void convertCurrency() {
        // Отображаем доступные валюты для конвертации
        System.out.println("Доступные валюты для конвертации:");
        System.out.println("1. Евро (EUR)");

        // Добавляем другие валюты
        System.out.println("2. US Dollar (USD)");
        System.out.println("3. Swedish Krona (SEK)");
        System.out.println("4. British Pound (GBP)");
        System.out.println("5. Japanese Yen (JPY)");
        System.out.println("6. Swiss Franc (CHF)");

        // Запрашиваем валюту, из которой пользователь хочет конвертировать
        Currency fromCurrency = getCurrencyFromUser();

        // Запрашиваем валюту, в которую пользователь хочет конвертировать
        Currency toCurrency = getCurrencyToUser(fromCurrency);

        // Запрашиваем сумму для конвертации
        double amount = getAmountFromUser();

        try {
            // Выполняем конвертацию через сервис
            double convertedAmount = exchangeRateService.convertCurrency(fromCurrency, toCurrency, amount);

            // Показываем результат
           // System.out.println("Конвертированная сумма: " + convertedAmount + " " + toCurrency.getCode());
        } catch (ExchangeRateException e) {
            System.out.println("Ошибка конвертации: " + e.getMessage());
        }

        // Ждем нажатие Enter для возвращения в меню
        System.out.println(" ");
        scanner.nextLine(); // Считываем пустую строку после предыдущего ввода
        // scanner.nextLine(); // Ожидаем, пока пользователь нажмет Enter
    }

    private Currency getCurrencyFromUser() {
        // Запрос валюты, из которой будем конвертировать
        Currency selectedCurrency = null;
        while (selectedCurrency == null) {
            System.out.print("Введите код валюты для конвертации из (например, EUR, USD, SEK): ");
            String fromCurrencyCode = scanner.nextLine().toUpperCase();

            // Получаем валюту по коду
            switch (fromCurrencyCode) {
                case "EUR":
                    selectedCurrency = new Currency("Euro", "EUR");
                    break;
                case "USD":
                    selectedCurrency = new Currency("US Dollar", "USD");
                    break;
                case "SEK":
                    selectedCurrency = new Currency("Swedish Krona", "SEK");
                    break;
                case "GBP":
                    selectedCurrency = new Currency("British Pound", "GBP");
                    break;
                case "JPY":
                    selectedCurrency = new Currency("Japanese Yen", "JPY");
                    break;
                case "CHF":
                    selectedCurrency = new Currency("Swiss Franc", "CHF");
                    break;
                default:
                    System.out.println("Неверный код валюты. Пожалуйста, попробуйте снова.");
                    break;
            }
        }
        return selectedCurrency;
    }

    private Currency getCurrencyToUser(Currency fromCurrency) {
        // Запрос валюты, в которую будем конвертировать
        Currency selectedCurrency = null;
        while (selectedCurrency == null) {
            System.out.print("Введите код валюты для конвертации в (например, EUR, USD, SEK): ");
            String toCurrencyCode = scanner.nextLine().toUpperCase();

            // Проверяем, что это не та же валюта, из которой мы конвертируем
            if (toCurrencyCode.equals(fromCurrency.getCode())) {
                System.out.println("Невозможно конвертировать в ту же валюту. Пожалуйста, выберите другую валюту.");
                continue;
            }

            // Получаем валюту по коду
            switch (toCurrencyCode) {
                case "EUR":
                    selectedCurrency = new Currency("Euro", "EUR");
                    break;
                case "USD":
                    selectedCurrency = new Currency("US Dollar", "USD");
                    break;
                case "SEK":
                    selectedCurrency = new Currency("Swedish Krona", "SEK");
                    break;
                case "GBP":
                    selectedCurrency = new Currency("British Pound", "GBP");
                    break;
                case "JPY":
                    selectedCurrency = new Currency("Japanese Yen", "JPY");
                    break;
                case "CHF":
                    selectedCurrency = new Currency("Swiss Franc", "CHF");
                    break;
                default:
                    System.out.println("Неверный код валюты. Пожалуйста, попробуйте снова.");
                    break;
            }
        }
        return selectedCurrency;
    }

    private double getAmountFromUser() {
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Введите сумму для конвертации: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Сумма конвертации должна быть больше нуля.");
                } else {
                    validAmount = true;
                }
            } else {
                System.out.println("Пожалуйста, введите корректную сумму.");
                scanner.next(); // Очищаем ввод
            }
        }
        return amount;
    } // Методы для обмена валюты конец

    private int getIntInput() {
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.println("Это не число! Пожалуйста, введите целое число.");
        }
        return scanner.nextInt();  // Повертаємо правильне число
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

                int choice = getIntInput();
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
                System.out.println(Color.GREEN + "\033[3mСделайте корректный выбор\033[0m\n");
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
                System.out.println("9. Перевести между своими счетами");
                System.out.println("10. Logout");

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
                openAccount();
                waitRead();
                break;
            case 4:
                depositMoney();
                waitRead();
                break;
            case 5:
                withdrawMoney();
                 waitRead();
                break;
            case 6:
                closeAccount();
                waitRead();
                break;
            case 7:
                convertCurrency();
                waitRead();
                break;
            case 8:
                showTransactionHistory();
                waitRead();
                break;
            case 9:
                transferBetweenAccounts();
                waitRead();
                break;
            case 10:
                logOut();
                waitRead();
                break;
            default:
                System.out.println(Color.GREEN + "\033[3mСделайте корректный выбор\033[0m\n");

        }
    } // showUserSubMenu

    public void withdrawMoney() {
        // Получаем список всех аккаунтов текущего пользователя
        List<Account> accounts = null;

        try {
            accounts = accountService.getAllAccountsByUserId(currentUserId);
        } catch (AccountException e) {
            System.out.println("Ошибка при получении аккаунтов: " + e.getMessage());
            return; // Прерываем выполнение метода, если произошла ошибка
        }

        // Проверяем, есть ли у пользователя аккаунты
        if (accounts.isEmpty()) {
            System.out.println("У вас нет доступных аккаунтов.");
            return;
        }

        // Выводим все аккаунты
        System.out.println("Ваши доступные аккаунты:");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println((i + 1) + ". ID: " + account.getId() + ", (" + account.getCurrency().getCode() + ") , Баланс: " + account.getBalance());
        }

        // Запрос ID аккаунта для снятия средств
        int accountId = 0;
        boolean validAccountId = false;
        while (!validAccountId) {
            System.out.print("Введите номер аккаунта для снятия средств (например, 1): ");
            if (scanner.hasNextInt()) {
                int accountIndex = scanner.nextInt() - 1;
                if (accountIndex >= 0 && accountIndex < accounts.size()) {
                    accountId = accounts.get(accountIndex).getId();
                    validAccountId = true;
                } else {
                    System.out.println("Некорректный номер аккаунта. Пожалуйста, выберите правильный номер.");
                }
            } else {
                System.out.println("Пожалуйста, введите корректный номер аккаунта.");
                scanner.next(); // Очищаем ввод
            }
        }

        // Запрос суммы для снятия
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Введите сумму для снятия: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Сумма снятия должна быть больше нуля.");
                } else {
                    validAmount = true;
                }
            } else {
                System.out.println("Пожалуйста, введите корректную сумму.");
                scanner.next(); // Очищаем ввод
            }
        }

        try {
            // снимаем средства с выбранного аккаунта
            accountService.withdraw(accountId, amount);
            System.out.println("Счет успешно обновлен: сумма снята: " + amount);

            // Обновляем баланс выбранного аккаунта
            Account updatedAccount = accountService.getAccountById(accountId);
            System.out.println("Ваш обновленный баланс для аккаунта ID " + updatedAccount.getId() + ": " + updatedAccount.getBalance());

        } catch (AccountException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println(" ");
        //System.out.println(Color.GREEN + "\nНажмите Enter для возвращения в меню..." + Color.RESET);
        scanner.nextLine(); // Считываем пустую строку после предыдущего ввода
        //1scanner.nextLine();
    }


    public void depositMoney() {
        // Получаем список всех аккаунтов текущего пользователя
        List<Account> accounts = null;

        try {
            accounts = accountService.getAllAccountsByUserId(currentUserId);
        } catch (AccountException e) {
            System.out.println("Ошибка при получении аккаунтов: " + e.getMessage());
            return; // Прерываем выполнение метода, если произошла ошибка
        }

        // Проверяем, есть ли у пользователя аккаунты
        if (accounts.isEmpty()) {
            System.out.println("У вас нет доступных аккаунтов.");
            return;
        }

        // Выводим все аккаунты
        System.out.println("Ваши доступные аккаунты:");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            System.out.println((i + 1) + ". ID: " + account.getId() + ", (" + account.getCurrency().getCode() + "), Баланс: " + account.getBalance());
        }

        // Запрос ID аккаунта
        int accountId = 0;
        boolean validAccountId = false;
        while (!validAccountId) {
            System.out.print("Введите номер аккаунта для пополнения (например, 1): ");
            if (scanner.hasNextInt()) {
                int accountIndex = scanner.nextInt() - 1;
                if (accountIndex >= 0 && accountIndex < accounts.size()) {
                    accountId = accounts.get(accountIndex).getId();
                    validAccountId = true;
                } else {
                    System.out.println("Некорректный номер аккаунта. Пожалуйста, выберите правильный номер.");
                }
            } else {
                System.out.println("Пожалуйста, введите корректный номер аккаунта.");
                scanner.next(); // Очищаем ввод
            }
        }

        // Запрос суммы депозита
        double amount = 0;
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Введите сумму для пополнения: ");
            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                if (amount <= 0) {
                    System.out.println("Сумма депозита должна быть больше нуля.");
                } else {
                    validAmount = true;
                }
            } else {
                System.out.println("Пожалуйста, введите корректную сумму.");
                scanner.next(); // Очищаем ввод
            }
        }

        try {
            // Вызов сервиса для пополнения счета
            accountService.deposit(accountId, amount);
            System.out.println(Color.YELLOW + "Счет успешно пополнен на сумму: " + Color.RESET + amount);

            // Обновляем баланс выбранного аккаунта
            Account updatedAccount = accountService.getAccountById(accountId);
            System.out.println(Color.YELLOW + "Ваш обновленный баланс для аккаунта ID " + Color.RESET  + updatedAccount.getId() + ": " + updatedAccount.getBalance());

        } catch (AccountException e) {

            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println(" ");
        //System.out.println(Color.GREEN + "\nНажмите Enter для возвращения в меню..." + Color.RESET);
        scanner.nextLine(); // Считываем пустую строку после предыдущего ввода
    }



    public void closeAccount() {
        if (currentUserId == 0) {
            System.out.println("Ошибка: пользователь не авторизован.");
            return;
        }

        try {
            // Получаем список всех счетов пользователя
            List<Account> userAccounts = accountService.getAllAccountsByUserId(currentUserId);

            if (userAccounts.isEmpty()) {
                System.out.println(Color.GREEN + "У вас нет счетов для закрытия." + Color.RESET);
                return;
            }

            // Показываем список счетов
            System.out.println( "Ваши счета:");
            for (int i = 0; i < userAccounts.size(); i++) {
                Account account = userAccounts.get(i);
                System.out.println((i + 1) + Color.YELLOW + ". Счет ID: " + Color.RESET + account.getId() + Color.YELLOW + ", Валюта: " + Color.RESET + account.getCurrency().getName());
            }

            // Спросим пользователя, какой счет он хочет закрыть
            while (true) {
                System.out.println(Color.GREEN + "Введите номер счета, который вы хотите закрыть (или 0 для выхода):" + Color.RESET);
                int choice = scanner.nextInt();
                scanner.nextLine(); // Читаем лишний символ после ввода числа

                if (choice == 0) {
                   // System.out.println("Выход из меню закрытия счетов.");
                    return; // Выход из метода
                }

                if (choice < 1 || choice > userAccounts.size()) {
                    System.out.println(Color.RED + "Ошибка:" + Color.RESET + " неверный выбор счета.");
                    continue; // Повторный запрос на выбор счета
                }

                Account selectedAccount = userAccounts.get(choice - 1);  // Выбираем счет по номеру

                // Попытка удалить выбранный счет
                accountService.deleteAccount(currentUserId, selectedAccount.getId());
                System.out.println("Счет с ID " + selectedAccount.getId() + " успешно закрыт.");

                // Спросим пользователя, хочет ли он закрыть еще один счет
                System.out.println(Color.GREEN + "Хотите ли вы закрыть еще один счет? (да/нет):" + Color.RESET);
                String answer = scanner.nextLine().trim().toLowerCase();

                if (!answer.equals("да")) {
                  //  System.out.println("Выход из меню закрытия счетов.");
                    break;  // Завершаем цикл, если пользователь не хочет закрывать еще один счет
                }
            }

        } catch (AccountException e) {
            System.out.println(Color.RED + "Ошибка при закрытии счета: " + Color.RESET + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }  // closeAccount()


    public void openAccount() {
        if (currentUserId == 0) {
            System.out.println(Color.RED + "Ошибка:" + Color.RESET + " пользователь не авторизован.");
            return;
        }

        try {
            // Получаем список всех доступных валют через сервис
            List<Currency> availableCurrencies = currencyService.getAllCurrencies();

            boolean continueOpeningAccounts = true;

            while (continueOpeningAccounts) {
                // Выводим список валют
                System.out.println(Color.YELLOW + "Доступные валюты для открытия счета:" + Color.RESET);
                for (int i = 0; i < availableCurrencies.size(); i++) {
                    System.out.println((i + 1) + ". " + availableCurrencies.get(i).getName() + " (" + availableCurrencies.get(i).getCode() + ")");
                }

                int choice = -1;
                boolean validChoice = false;

                // Цикл для повторных попыток ввода правильного кода валюты
                while (!validChoice) {
                    // Запрашиваем код валюты для нового счета
                    System.out.println("Введите номер валюты, в которой вы хотите открыть счет:");
                    choice = getIntInput();
                    scanner.nextLine(); // Читаем лишний символ после ввода числа

                    // Проверяем, что пользователь ввел корректный номер валюты
                    if (choice >= 1 && choice <= availableCurrencies.size()) {
                        validChoice = true; // Ввод правильного выбора
                    } else {
                        System.out.println(Color.RED + "Ошибка:" + Color.RESET + " неверный выбор валюты. Попробуйте снова.");
                    }
                }

                // Выбираем валюту по номеру
                Currency selectedCurrency = availableCurrencies.get(choice - 1);

                try {
                    // Попытка создать новый счет через сервис
                    accountService.createAccount(currentUserId, selectedCurrency.getCode());
                    System.out.println("Новый счет в валюте " + selectedCurrency.getCode() + " успешно открыт.");
                } catch (AccountException e) {
                    // Обработка исключений, если счет с такой валютой уже существует или валюта не найдена
                    System.out.println(Color.RED + "Ошибка при открытии счета: " + Color.RESET+ e.getMessage());
                } catch (Exception e) {
                    // Обработка других неожиданных исключений
                    System.out.println(Color.RED + "Произошла непредвиденная ошибка: " + Color.RESET + e.getMessage());
                }

                // Спрашиваем, хочет ли пользователь открыть еще один счет
//                System.out.println(Color.GREEN + "Хотите открыть еще один счет?" + Color.RESET + " (да/нет):");
//                String answer = scanner.nextLine().trim().toLowerCase();
//                if (!answer.equals("да")) {
//                    continueOpeningAccounts = false;  // Прерываем цикл, если пользователь не хочет продолжать
//                }

                String answer;
                boolean validAnswer = false;
                while (!validAnswer) {
                    System.out.println(Color.GREEN + "Хотите открыть еще один счет?" + Color.RESET + " (да/нет):");
                    answer = scanner.nextLine().trim().toLowerCase();

                    if (answer.equals("да")) {
                        validAnswer = true;  // Пользователь выбрал продолжить
                    } else if (answer.equals("нет")) {
                        validAnswer = true;  // Пользователь выбрал прекратить
                        continueOpeningAccounts = false; // Прерываем цикл, если пользователь не хочет продолжать
                    } else {
                        System.out.println(Color.RED + "Ошибка:" + Color.RESET + " Неверный ввод. Пожалуйста, введите 'да' или 'нет'.");
                    }
                }
            }

        } catch (CurrencyException e) {
            // Обработка исключений, если валюты не доступны
            System.out.println(Color.RED + "Ошибка при получении списка валют: " + Color.RESET + e.getMessage());
        }
    }




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
                for (int i = 0; i < accounts.size(); i++) {
                    Account account = accounts.get(i);
                    Currency accountCurrency = account.getCurrency();  // Получаем валюту
                    System.out.println((i + 1) + ". Аккаунт ID: " + account.getId() + " (Валюта: " + accountCurrency.getName() + ")");
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
                showAllCurrency();
                waitRead();
                break;
            case 3:
                // Todo
               // addNewCurrency();
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

    public void showAllCurrency() {
        // Проверяем, что текущий пользователь — администратор
        if (activeUser.getRole() != Role.ADMIN) {
            System.out.println(Color.RED + "Ошибка:" + Color.RESET + " у вас нет прав для доступа к данному меню.");
            return;
        }

        try {
            // Получаем все доступные валюты
            List<Currency> currencies = currencyService.getAllCurrencies();

            // Выводим список валют
            System.out.println(Color.YELLOW + "Список доступных валют:" + Color.RESET);
            if (currencies.isEmpty()) {
                System.out.println(Color.RED + "Нет доступных валют." + Color.RESET);
            } else {
                for (Currency currency : currencies) {
                    System.out.println(currency.toString());
                }
            }
        } catch (CurrencyException e) {
            // Обработка исключений, если валюты не доступны
            System.out.println(Color.RED + "Ошибка при получении списка валют: " + Color.RESET + e.getMessage());
        }
    }

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

//        waitRead(); // Ожидаем ввода  |-> Задвоение для Нажмите Enter для продолжения
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

    public void showCurrencyRates() {
        try {
            System.out.println(Color.BLUE + "\t\t\t\033[1mКурсы валют относительно EUR\033[0m" + Color.RESET);
            System.out.printf("%-10s%-25s%-20s%n", "Код", "Название валюты", "Курс к EUR");
            System.out.println(Color.BLUE + "___________________________________________________________" + Color.RESET);

            Map<String, ExchangeRate> rates = exchangeRateService.getAllExchangeMap();

            rates.values().stream()
                    .filter(exchangeRate -> !exchangeRate.getToCurrency().getCode().equals("EUR")) // Пропускаємо всі записи, де валюта — EUR
                    .forEach(exchangeRate -> {
                        System.out.printf("%-10s%-25s%-20.4f%n",
                                exchangeRate.getToCurrency().getCode(),
                                exchangeRate.getToCurrency().getName(),
                                exchangeRate.getRate()
                        );
                    });

            System.out.println(Color.BLUE + "___________________________________________________________" + Color.RESET);
        } catch (Exception e) {
            System.out.println(Color.RED + "Произошла ошибка при получении курсов валют: " + e.getMessage() + Color.RESET);
        }
    }

    public void showTransactionHistory() {
        try {
            System.out.println(Color.BLUE + "\t\t\t\033[1mИстория транзакций\033[0m" + Color.RESET);
            System.out.printf("%-10s%-20s%-20s%-25s%-25s%-15s%-20s%n",
                    "ID",  "Отправитель", "Получатель", "Отправленная сумма", "Полученная сумма", "Курс", "Дата");
            System.out.println(Color.BLUE + "_________________________________________________________________________________________________________________________" + Color.RESET);

            List<Transaction> transactions = transactionService.findAllTransactions().values().stream().toList();

            if (transactions.isEmpty()) {
                System.out.println(Color.YELLOW + "Транзакции для отображения отсутствуют." + Color.RESET);
            } else {
                for (Transaction transaction : transactions) {
                    String fromAccount = transaction.getFromAccount().toString();
                    String toAccount = transaction.getToAccount() != null ? transaction.getToAccount().toString() : "N/A";
                    String fromAmount = String.format("%.2f %s", transaction.getFromAmount(), transaction.getFromCurrency().getCode());
                    String toAmount = String.format("%.2f %s", transaction.getToAmount(), transaction.getToCurrency().getCode());
                    String exchangeRate = String.format("%.2f", transaction.getExchangeRate());
                    String date = transaction.getTimeOperation().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                    System.out.printf("\n%-10dID счёта: %-5d Пользователь ID: %-5d Баланс: %-10.2f Валюта: %-12s Код: %-3s -> %-10.2f %-3s %-10.2f%n",
                            transaction.getTransactionId(),
                            transaction.getFromAccount().getId(),
                            transaction.getFromAccount().getUserId(),
                            transaction.getFromAmount(),
                            transaction.getFromCurrency().getName(),
                            transaction.getFromCurrency().getCode(),
                            transaction.getFromAmount(),
                            transaction.getFromCurrency().getCode(),
                            transaction.getExchangeRate());

                    System.out.printf("%-10sID счёта: %-5d Пользователь ID: %-5d Баланс: %-10.2f Валюта: %-12s Код: %-3s -> %-10.2f %-3s %-10.2f%n",
                            "",
                            transaction.getToAccount() != null ? transaction.getToAccount().getId() : "N/A",
                            transaction.getToAccount() != null ? transaction.getToAccount().getUserId() : "N/A",
                            transaction.getToAmount(),
                            transaction.getToCurrency() != null ? transaction.getToCurrency().getName() : "N/A",
                            transaction.getToCurrency() != null ? transaction.getToCurrency().getCode() : "N/A",
                            transaction.getToAmount(),
                            transaction.getToCurrency() != null ? transaction.getToCurrency().getCode() : "N/A",
                            transaction.getExchangeRate());
                }
            }

            System.out.println(Color.BLUE + "_________________________________________________________________________________________________________________________" + Color.RESET);

        } catch (Exception e) {
            System.out.println(Color.RED + "Ошибка при получении истории транзакций: " + e.getMessage() + Color.RESET);
            e.printStackTrace();  // Додати стек-трейс для детальної діагностики
        }

        System.out.println(Color.BLUE + "\n0. Вернуться в предыдущее меню" + Color.RESET);
        System.out.println(Color.GREEN + "\nСделать выбор:" + Color.RESET);

        // Перевірка вводу користувача
        int input = -1;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(Color.RED + "Некоректний ввід. Будь ласка, введіть число." + Color.RESET);
            scanner.nextLine();  // Очищаємо буфер
        }

        if (input == 0) {
            return;
        }
    }


    public void transferBetweenAccounts() {
        try {
            List<Account> userAccounts = accountService.getAllAccountsByUserId(currentUserId);

            if (userAccounts.size() < 2) {
                System.out.println(Color.RED + "У вас должно быть хотя бы два счета для перевода между ними." + Color.RESET);
                return;
            }

            System.out.println(Color.BLUE + "Ваши счета:" + Color.RESET);
            for (int i = 0; i < userAccounts.size(); i++) {
                System.out.printf("%d. %s (Баланс: %.2f %s)%n",
                        i + 1,
                        userAccounts.get(i).getCurrency().getName(),
                        userAccounts.get(i).getBalance(),
                        userAccounts.get(i).getCurrency().getCode());
            }

            System.out.println(Color.GREEN + "Выберите счет для отправки средств (номер):" + Color.RESET);
            int fromIndex = scanner.nextInt() - 1;
            validateAccountChoice(fromIndex, userAccounts.size());

            Account fromAccount = userAccounts.get(fromIndex);

            System.out.println(Color.GREEN + "Выберите счет для получения средств (номер):" + Color.RESET);
            int toIndex = scanner.nextInt() - 1;
            validateAccountChoice(toIndex, userAccounts.size());

            if (fromIndex == toIndex) {
                throw new IllegalArgumentException("Нельзя переводить средства на тот же счет.");
            }

            Account toAccount = userAccounts.get(toIndex);

            System.out.println(Color.GREEN + "Введите сумму для перевода:" + Color.RESET);
            double amount = scanner.nextDouble();
            if (amount <= 0 || amount > fromAccount.getBalance()) {
                throw new IllegalArgumentException("Недостаточно средств или неверная сумма.");
            }

            transactionService.transfer(fromAccount.getId(), toAccount.getId(), amount);
            System.out.println(Color.GREEN + "Перевод успешно выполнен!" + Color.RESET);

            System.out.println(Color.BLUE + "\nВаши счета:" + Color.RESET);
            for (int i = 0; i < userAccounts.size(); i++) {
                System.out.printf("%d. %s (Баланс: %.2f %s)%n",
                        i + 1,
                        userAccounts.get(i).getCurrency().getName(),
                        userAccounts.get(i).getBalance(),
                        userAccounts.get(i).getCurrency().getCode());
            }
        } catch (Exception e) {
            System.out.println(Color.RED + "Ошибка при выполнении перевода: " + e.getMessage() + Color.RESET);
        }
    }

    private void validateAccountChoice(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Неверный выбор счета.");
        }
    }

    private void waitRead() {
        System.out.println(Color.CYAN + "\nНажмите Enter для продолжения" + Color.RESET);
        scanner.nextLine();
    }

}