package dev.emiliofranco.expense_tracker_api.service;

import dev.emiliofranco.expense_tracker_api.exception.UserAuthException;
import dev.emiliofranco.expense_tracker_api.model.User;

public interface UserService {
    User validateUser(String email, String password) throws UserAuthException;
    User registerUser(String firstName, String lastName, String email, String password) throws UserAuthException;
}
