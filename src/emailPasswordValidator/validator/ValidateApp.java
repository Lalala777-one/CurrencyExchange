package emailPasswordValidator.validator;

import exceptionsUtils.EmailValidateException;
import exceptionsUtils.PasswordValidateException;

public class ValidateApp {
    public static void main(String[] args) {

        // получаем email от пользователя (сканером, из другого слоя приложения и тд)
        String email = "estemail@.com";

        String password = "jhjl!1ррррр";


        //Валидация email
        try {
            EmailAndPasswordValidator.isValidEmail(email);
            // если мы дойдем до этой строчки кода, значит email валидный
            // можем создавать нового пользователя
            System.out.println("The email has passed validation.");

            // Если email прошел проверку - ПРОВЕРЯЕМ ПАРОЛЬ (если email не прошел, то пароль, возможно, проверять не нужно)
            EmailAndPasswordValidator.isValidPassword(password);
            System.out.println("Password has passed validation.");

            // Можем создать пользователя, с проверенным email и паролем
            // User user = new User(email, password)
            //service.createUser(email, password);

            // service.createUser
            //  User user = new User(email, password);
        } catch (EmailValidateException e) {
            System.out.println("Please provide a valid email address.");
            String message = e.getMessage();
            System.out.println(message);

        } catch (PasswordValidateException ex) {
            // Пароль не прошел проверку (возник объект исключения
            System.out.println("The password is invalid. Please check the requirements.");
            System.out.println(ex.getMessage());
        }
    }
}
