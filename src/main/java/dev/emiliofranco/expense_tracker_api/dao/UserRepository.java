package dev.emiliofranco.expense_tracker_api.dao;

import dev.emiliofranco.expense_tracker_api.exception.UserAuthException;
import dev.emiliofranco.expense_tracker_api.model.User;

public interface UserRepository {
    int create(String firstName, String lastName, String email, String password) throws UserAuthException;
    User findByEmailAndPassword(String email, String password ) throws UserAuthException;
    int getCountByEmail(String email);

    User findById(int userId);
}
