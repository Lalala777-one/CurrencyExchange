package model;

import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private String email;
    private String password;
    // private List<Account> accounts; получвть из репозитория
    private Role role;

    public User(){
        this.role = Role.GUEST;
    }

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = Role.USER;

    }

    @Override
    public String toString() {
        return "User{" +
                "email = '" + email + '\'' +
                ", id = " + id +
                '}';
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
