package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHelper {

    public static String getPasswordHash(String email) {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            throw new IllegalStateException(
                    "DB_URL, DB_USER, and DB_PASSWORD environment variables are required."
            );
        }

        String sql = "SELECT password FROM dbkoel.users WHERE email = ?";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }

                throw new IllegalStateException("No user found in DB with email: " + email);
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not get password hash from database.", e);
        }
    }
    public static boolean isPlaylistSaved(String playlistName, String userEmail) {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUser == null || dbPassword == null) {
            throw new IllegalStateException(
                    "DB_URL, DB_USER, and DB_PASSWORD environment variables are required."
            );
        }

        String sql =
                "SELECT p.id " +
                        "FROM dbkoel.playlists p " +
                        "JOIN dbkoel.users u ON p.user_id = u.id " +
                        "WHERE p.name = ? AND u.email = ?";

        try (
                Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, playlistName);
            statement.setString(2, userEmail);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not verify playlist in database.", e);
        }
    }
    private DatabaseHelper() {
    }
}