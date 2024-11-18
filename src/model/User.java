package model;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static int userIdCounter = 0;
    private int id;
    private String name;
    private String email;
    private String password;
    // private List<Account> accounts; получвть из репозитория
    private Role role;

    public User(){
        this.role = Role.GUEST;
    }

    public User( String email, String password, String name) {
        this.id = ++userIdCounter; // Увеличиваем счётчик для нового пользователя
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = Role.USER;

    }

    @Override
    public String toString() {
        return String.format("id: %-5d имя: %-11s email: %-25s", id, name, email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
