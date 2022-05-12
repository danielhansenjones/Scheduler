package model;

/**
 * This class is an object constructor used to create User objects and get/set their values.
 */

public class User {
    private int userId;
    private String username;
    private String password;

    /**
     * @param userId   Int value of User ID
     * @param username String value of Username
     * @param password String value of Password
     */
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets User ID
     *
     * @return userId Integer
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets User ID
     *
     * @param userId Integer
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets Username
     *
     * @return username String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets Username
     *
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets Password
     *
     * @return password String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets Password
     *
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ("#" + userId + " " + username);
    }
}
