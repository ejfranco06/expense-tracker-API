package dev.emiliofranco.expense_tracker_api.model;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(int userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
