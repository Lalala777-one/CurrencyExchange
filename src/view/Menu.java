package view;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);


    // МЕНЮ ДЛЯ НЕ ЗАРЕГИСТРИРОВАННОГО ПОЛЬЗОВАТЕЛЯ
private void showGuestMenu(){
while (true){
    System.out.println(Color.YELLOW + "Welcome!\n" +
            "To access all features, please create an account or log in to an existing one." + Color.RESET);
    System.out.println("1. Log In");
    System.out.println("2. Sign Up");
    System.out.println("3. View Exchange Rates");
    System.out.println(Color.GREEN + "\nPlease enter a menu option:" + Color.RESET);

    int choice = scanner.nextInt();
    scanner.nextLine();

    showGuestSubMenu(choice);
}
} // showGuestMenu


    private void showGuestSubMenu(int choice){

    switch (choice){
        case 1:
            // Todo
            // метод
            waitRead();
            break;
        case 2:
            // Todo
            // метод
            waitRead();
            break;
        case  3:
            // Todo
            // метод
            waitRead();
            break;
        default:
            System.out.println(Color.GREEN + "Please make a valid choice\n" + Color.RESET);
    }
    }//


   // МЕНЮ ДЛЯ ЗАРЕГИСТРИРОВАННОГО ПОЛЬЗОВАТЕЛЯ

    private void showUserMenu(){
        while (true){
            System.out.println(Color.YELLOW + "Добро пожаловать в главное меню пользователя" + Color.RESET);
            System.out.println("1. Посмотреть курс валют");
            System.out.println("2. Посмотреть баланс");
            System.out.println("3. Открыть новый счет");
            System.out.println("4. Пополнить счет");
            System.out.println("5. Снять деньги со счета");
            System.out.println("6. Закрыть счет");
            System.out.println("7. Осуществить обмен валюты");
            System.out.println("8. Посмотреть историю всех транзакций");
            System.out.println("9. Logout");

            System.out.println("\nСделайте ваш выбор:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            showUserSubMenu(choice);
        }
    } // showUserMenu

    private void showUserSubMenu(int choice) {
   switch (choice){
       case 1:
           // Todo
           // method
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
           // Todo
           // method
           waitRead();
           break;
       case 6:
           // Todo
           // method
           waitRead();
           break;
       case 7:
           // Todo
           // method
           waitRead();
           break;
       case 8:
           // Todo
           // method
           waitRead();
           break;
       case 9:
           // Todo
           // method
           waitRead();
           break;
       default:
           System.out.println(Color.GREEN + "Сделайте корректный выбор\n" + Color.RESET);

   }
    } // showUserSubMenu



// МЕНЮ АДМИНИСТРАТОРА
    private void showAdminMenu(){
    while (true) {
        System.out.println(Color.YELLOW + "Добро пожаловать в меню администратора" + Color.RESET);
        System.out.println("1. Просмотр списка пользователей");
        System.out.println("2. Просмотр доступных валют");
        System.out.println("3. Добавление новой валюты");
        System.out.println("4. Удаление валюты");
        System.out.println("\nСделайте ваш выбор:");

        int choice = scanner.nextInt();
        scanner.nextLine();

        showAdminSubMenu(choice);
    }
    }

    private void showAdminSubMenu(int choice) {
    switch (choice){
        case 1:
            // Todo
            // method
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
        System.out.println("\nPress Enter to continue");
        scanner.nextLine();
    }


}
