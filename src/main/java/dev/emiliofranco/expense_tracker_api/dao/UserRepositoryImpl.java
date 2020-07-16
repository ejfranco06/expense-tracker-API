package dev.emiliofranco.expense_tracker_api.dao;

import dev.emiliofranco.expense_tracker_api.exception.UserAuthException;
import dev.emiliofranco.expense_tracker_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String SQL_CREATE = "INSERT INTO et_user (user_id, first_name, last_name, email, password)" +
            "VALUES (NEXTVAL('et_user_seq'), :firstName, :lastName, :email, :password)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM et_user WHERE email = :email";
    private static final String SQL_FIND_BY_USER_ID = "SELECT user_id, first_name, last_name, email, password FROM et_user WHERE user_id = :userId";


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int create(String firstName, String lastName, String email, String password) throws UserAuthException{
        try {
            MapSqlParameterSource paramMap = new MapSqlParameterSource();
            paramMap.addValue("firstName", firstName);
            paramMap.addValue("lastName", lastName);
            paramMap.addValue("email", email);
            paramMap.addValue("password", password);

            final KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(SQL_CREATE, paramMap, holder);

            return (int) holder.getKeys().get("user_id");
        } catch (Exception e){
          throw  new UserAuthException("Invalid detail. failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws UserAuthException {
        return null;
    }

    @Override
    public int getCountByEmail(String email) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("email", email);

        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, paramMap, Integer.class);
    }

    @Override
    public User findById(int userId) {
        try {
            MapSqlParameterSource paramMap = new MapSqlParameterSource();
            paramMap.addValue("userId", userId);
            return jdbcTemplate.queryForObject(SQL_FIND_BY_USER_ID, paramMap, userRowMapper);
        } catch (Exception e){
            throw  new UserAuthException("No user found");
        }
    }

    private RowMapper<User> userRowMapper =  ((resultSet, i) -> {
        int userId= resultSet.getInt("user_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(userId, firstName, lastName, email, password);
    });
}
