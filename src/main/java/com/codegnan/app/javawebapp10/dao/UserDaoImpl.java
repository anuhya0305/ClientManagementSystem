package com.codegnan.app.javawebapp10.dao;

import com.codegnan.app.javawebapp10.dto.CredentialsDto;
import com.codegnan.app.javawebapp10.dto.UserDto;
import com.codegnan.app.javawebapp10.entity.Credentials;
import com.codegnan.app.javawebapp10.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(UserDto userDto, CredentialsDto credentialsDto) {
        boolean isUserSaved = false;

        User user = new User(userDto.getFirstName(), userDto.getLastName());
        String hashedPassword = BCrypt.hashpw(credentialsDto.getLoginPassword(), BCrypt.gensalt());
        Credentials credentials = new Credentials(credentialsDto.getUsername(), hashedPassword);

        String sqlQuery1 = "INSERT INTO credentials(username, login_password) VALUES (?,?);";
        String sqlQuery2 = "INSERT INTO users (first_name, last_name, credentials_id) VALUES (?,?,?);";

        try (Connection connection = DatabaseUtility.getDatabaseConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(sqlQuery1, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery2)) {

            connection.setAutoCommit(false);

            preparedStatement1.setString(1, credentials.getUsername());
            preparedStatement1.setString(2, credentials.getLoginPassword());
            int numOfRows = preparedStatement1.executeUpdate();
            if (numOfRows != 0) {
                ResultSet resultSet = preparedStatement1.getGeneratedKeys();
                resultSet.next();
                int credentials_id = resultSet.getInt(1);

                preparedStatement2.setString(1, user.getFirstName());
                preparedStatement2.setString(2, user.getLastName());
                preparedStatement2.setInt(3, credentials_id);
                numOfRows = preparedStatement2.executeUpdate();
                if (numOfRows != 0) {
                    connection.commit();

                    isUserSaved = true;
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return isUserSaved;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserDto> usersList = new ArrayList<>();

        String sqlQuery = "SELECT u.user_id, u.first_name, u.last_name, " +
                "c.username " +
                "FROM users u " +
                "INNER JOIN credentials c " +
                "ON u.credentials_id = c.credentials_id";

        try (Connection connection = DatabaseUtility.getDatabaseConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            while (resultSet.next()) {

                UserDto userDto = new UserDto();

                userDto.setUserId(resultSet.getInt(1));
                userDto.setFirstName(resultSet.getString(2));
                userDto.setLastName(resultSet.getString(3));
                userDto.setUsername(resultSet.getString(4));

                usersList.add(userDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    @Override
    public UserDto findByUsernameAndLoginPassword(String username, String loginPassword) {

        UserDto userDto = null;

        String sqlQuery = "SELECT u.user_id, u.first_name, u.last_name, c.login_password " +
                "FROM users u " +
                "INNER JOIN credentials c ON u.credentials_id = c.credentials_id " +
                "WHERE c.username = ?";

        try (Connection connection = DatabaseUtility.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String storedHash = resultSet.getString(4);

                if (BCrypt.checkpw(loginPassword, storedHash)) {
                    userDto = new UserDto();

                    userDto.setUserId(resultSet.getInt(1));
                    userDto.setFirstName(resultSet.getString(2));
                    userDto.setLastName(resultSet.getString(3));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDto;
    }

    @Override
    public boolean updateLoginPasswordByUsername(String username, String newPassword) {

        boolean isPasswordUpdated = false;

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        String sqlQuery = "UPDATE credentials SET login_password = ? WHERE username = ?";

        try (Connection connection = DatabaseUtility.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setString(2, username);

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                isPasswordUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isPasswordUpdated;
    }

    @Override
    public boolean deleteByUserId(int userId) {

        boolean isUserDeleted = false;

        String getCredentialsId = "SELECT credentials_id FROM users WHERE user_id = ?";
        String deleteUser = "DELETE FROM users WHERE user_id = ?";
        String deleteCredentials = "DELETE FROM credentials WHERE credentials_id = ?";

        try (Connection connection = DatabaseUtility.getDatabaseConnection()) {

            connection.setAutoCommit(false);

            int credentialsId = 0;

            // Get credentials_id
            PreparedStatement ps1 = connection.prepareStatement(getCredentialsId);
            ps1.setInt(1, userId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                credentialsId = rs.getInt("credentials_id");
            } else {
                return false;
            }

            // Delete user
            PreparedStatement ps2 = connection.prepareStatement(deleteUser);
            ps2.setInt(1, userId);
            ps2.executeUpdate();

            // Delete credentials
            PreparedStatement ps3 = connection.prepareStatement(deleteCredentials);
            ps3.setInt(1, credentialsId);
            ps3.executeUpdate();

            connection.commit();
            isUserDeleted = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUserDeleted;
    }
}