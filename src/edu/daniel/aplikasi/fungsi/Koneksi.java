/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.daniel.aplikasi.fungsi;

import edu.daniel.aplikasi.view.FrmLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANIELFRANS
 */
public class Koneksi {

    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/laundry";
        String user = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }

    public static void Conn() {
        try {
            Connection c = getConnection();
            System.out.println(String.format("Berhasil terhubung ke database.", new Object[]{c.getCatalog()}));
        } catch (SQLException e) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
