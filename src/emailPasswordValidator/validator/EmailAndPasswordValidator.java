package emailPasswordValidator.validator;

import exceptionsUtils.EmailValidateException;
import exceptionsUtils.PasswordValidateException;

public class EmailAndPasswordValidator {


    // throw - КЛЮЧЕВОЕ СЛОВО ИСПОЛЬЗУЕМОЕ ДЛЯ ЯВНОГО ВЫБРОСА ИСКЛЮЧЕНИЯ ( СОЗДАНИЕ ОБЪЕКТА ИСКЛЮЧЕНИЯ )


    public static void isValidEmail(String email) throws EmailValidateException {
        // 1. Должна присутствовать @ и только ОДНА

        String initStr = "Электронная почта должна соответствовать следующим требованиям: ";
        StringBuilder errorMessage = new StringBuilder(initStr);

        // 1. Должна присутствовать @
        int indexAt = email.indexOf('@');
        // int lastAt = email.lastIndexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) {
            errorMessage.append("\n\t\t- должна содержать ровно один символ '@'. ");
        }


        // 2. Точка после собаки
        int dotIndexAfterAt = email.indexOf('.', indexAt + 1);
        if (dotIndexAfterAt == -1) {
            errorMessage.append("\n\t\t- должна содержать символ '.' после символа '@'. ");
        }

        // 3. После последней точки есть 2 или более символов
        // test@fazx.com.ne.t
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex + 2 >= email.length()) {
            errorMessage.append("\n\t\t- часть после последней точки " +
                    "в адресе электронной почты должна содержать как минимум два символа. ");
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
                errorMessage.append("\n\t\t- может содержать только буквы, цифры," +
                        " '-', '_', '.', и '@'. Другие символы не допускаются.");
            }
        }

        // 6. Первый символ - должна быть буква
        // Если 0-й символ НЕ является буквой, то email не подходит = return false;
        char firstChar = email.charAt(0);
        if (!Character.isAlphabetic(firstChar)) {
            errorMessage.append("\n\t\t- первый символ должен быть буквенным");
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
            throw new PasswordValidateException("\"Пароль должен содержать не менее 8 символов.\"");
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
        StringBuilder errorMessage = new StringBuilder("Пароль должен содержать хотя бы: ");
        boolean valid = true;

        if (!isDigit) {
            errorMessage.append("\n\t\t- одну цифру. ");
            valid = false;
        }
        if (!isLowercase) {
            errorMessage.append("\n\t\t- одну строчную букву. ");
            valid = false;
        }
        if (!isUppercase) {
            errorMessage.append("\n\t\t- одну заглавную букву. ");
            valid = false;
        }
        if (!isSpecialChar) {
            errorMessage.append("\n\t\t- один специальный символ. ");
            valid = false;
        }

        //
        if (!valid) {
            throw new PasswordValidateException(errorMessage.toString());
        }
    } // isValidPassword


}
















