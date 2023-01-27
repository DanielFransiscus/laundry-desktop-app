/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.daniel.aplikasi.view;

/**
 *
 * @author DANIELFRANS
 */
public class User {

    private static int userID;
    private static String username;
    private static String password;

    /**
     * @return the username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * @param aUsername the username to set
     */
    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        User.userID = userID;
    }
}
