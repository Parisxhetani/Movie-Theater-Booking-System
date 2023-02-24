package dao;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import models.show_model;

import java.io.IOException;
import java.sql.*;

public class login_dao
{
    public boolean login(String username, String password)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://tonic.o2switch.net:3306/lafe6113_theater_shows", "lafe6113_kristi", "WHE,EKA?rX$^");
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE Username='" + username + "' and Pass='" + password + "'");

            if (rs.next()) {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession session = (HttpSession) externalContext.getSession(true);

                session.setAttribute("username", username);
                return true;

            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
