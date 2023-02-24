package dao;

import models.play_model;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class play_dao
{
    public List<play_model> getAllPlays() throws SQLException
    {
        List<play_model> list_of_plays = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://tonic.o2switch.net:3306/lafe6113_theater_shows", "lafe6113_kristi", "WHE,EKA?rX$^");
             Statement stmt = conn.createStatement())



        {
            ResultSet rs = stmt.executeQuery("SELECT * FROM plays");

            while (rs.next()) {
                play_model mp = new play_model();

                mp.setId(rs.getInt("id"));
                mp.setTitle(rs.getString("title"));
                mp.setWriter(rs.getString("writer"));
                mp.setDirector(rs.getString("director"));
                mp.setActors(rs.getString("actors"));

                list_of_plays.add(mp);
            }
        }

        return list_of_plays;
    }
}
