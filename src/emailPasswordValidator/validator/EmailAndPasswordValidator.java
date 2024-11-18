package emailPasswordValidator.validator;

import exceptions.EmailValidateException;
import exceptions.PasswordValidateException;

public class EmailAndPasswordValidator {


    // throw - КЛЮЧЕВОЕ СЛОВО ИСПОЛЬЗУЕМОЕ ДЛЯ ЯВНОГО ВЫБРОСА ИСКЛЮЧЕНИЯ ( СОЗДАНИЕ ОБЪЕКТА ИСКЛЮЧЕНИЯ )


    public static void isValidEmail(String email) throws EmailValidateException {
        // 1. Должна присутствовать @ и только ОДНА

        String initStr = "Email must meet the following requirements: ";
        StringBuilder errorMessage = new StringBuilder(initStr);

        // 1. Должна присутствовать @
        int indexAt = email.indexOf('@');
        // int lastAt = email.lastIndexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) {
            errorMessage.append("\n\t\t- must contain exactly one '@' symbol. ");
        }


        // 2. Точка после собаки
        int dotIndexAfterAt = email.indexOf('.', indexAt + 1);
        if (dotIndexAfterAt == -1) {
            errorMessage.append("\n\t\t- must have a '.' character after the '@' symbol. ");
        }

        // 3. После последней точки есть 2 или более символов
        // test@fazx.com.ne.t
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex + 2 >= email.length()) {
            errorMessage.append("\n\t\t- the part after the last '.'" +
                    " in the email address must be at least two characters long. ");
        }

// 4.  Алфавит, цифры, '-', '_', '.', '@'
        /*
        Я беру каждый символ. Проверяю, что он не является "запрещенным"
        И если нахожу не подходящий символ - возвращаю false
         */
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);

            // Если символ удовлетворяет одному из условий на "правильность"
            boolean isPass = (Character.isAlphabetic(ch) ||
                    Character.isDigit(ch) ||
                    ch == '-' ||
                    ch == '_' ||
                    ch == '.' ||
                    ch == '@');

            // Если любой символ НЕ подходящий, сразу возвращаем false
            if (!isPass) {
                errorMessage.append("\n\t\t- can only contain letters, digits, '-', '_', '.', and '@'. No other characters are allowed.");
            }
        }

        // 6. Первый символ - должна быть буква
        // Если 0-й символ НЕ является буквой, то email не подходит = return false;
        char firstChar = email.charAt(0);
        if (!Character.isAlphabetic(firstChar)) {
            errorMessage.append("\n\t\t- the first symbol should be alphabetic");
        }

        // Все проверки пройдены. email подходит.


        if ((errorMessage.length() > initStr.length())) {
            throw new EmailValidateException(errorMessage.toString());
        }
    } // isValidEmail


    // проверка пароля

    public static void isValidPassword(String password) throws PasswordValidateException {
        if (password == null || password.length() < 8) {
            System.out.println();
            throw new PasswordValidateException("\"Password must be at least 8 characters long.\"");
        }

        boolean isDigit = false; // наличие хотя бы одной цифры
        boolean isLowercase = false; //  наличие хотя бы одной маленькой буквы
        boolean isUppercase = false; // наличие хотя бы одной большой буквы:
        boolean isSpecialChar = false; // наличие хотя бы одного специального символа

        String specialCharacters = "!%$@&*()[].,-";

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isDigit(ch)) isDigit = true;

            if (Character.isLowerCase(ch)) isLowercase = true;

            if (Character.isUpperCase(ch)) isUppercase = true;

            if (specialCharacters.indexOf(ch) != -1) isSpecialChar = true;

            if (isDigit && isLowercase && isUppercase && isSpecialChar) {
                return;
            }
        }

        // если будет несколько ошибок, в консоли отображаем все сразу, чтобы было понятно что требуется исправить
        StringBuilder errorMessage = new StringBuilder("Password must contain at least: ");
        boolean valid = true;

        if (!isDigit) {
            errorMessage.append("\n\t\t- one digit. ");
            valid = false;
        }
        if (!isLowercase) {
            errorMessage.append("\n\t\t- one lowercase letter. ");
            valid = false;
        }
        if (!isUppercase) {
            errorMessage.append("\n\t\t- one uppercase letter. ");
            valid = false;
        }
        if (!isSpecialChar) {
            errorMessage.append("\n\t\t- one special character. ");
            valid = false;
        }

        //
        if (!valid) {
            throw new PasswordValidateException(errorMessage.toString());
        }
    } // isValidPassword


}
















