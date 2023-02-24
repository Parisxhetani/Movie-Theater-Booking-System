package dao;

import models.play_model;
import models.show_model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class show_dao
{
    public List<show_model> get_shows_by_play_id(String play_id) throws SQLException
    {
        List<show_model> list_of_shows = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://tonic.o2switch.net:3306/lafe6113_theater_shows", "lafe6113_kristi", "WHE,EKA?rX$^");
             Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery("SELECT shows.ID, plays.Title, halls.Name, shows.Show_Date, shows.Show_Time " +
                    "FROM shows " +
                    "INNER JOIN plays on shows.Play_ID = plays.ID " +
                    "INNER JOIN halls on shows.Hall_ID = halls.ID " +
                    "WHERE plays.ID = " + play_id + ";");

            while (rs.next()) {
                show_model sm = new show_model();

                sm.setId(rs.getInt("id"));
                sm.setPlayTitle(rs.getString("title"));
                sm.setHallName(rs.getString("name"));
                sm.setShowDate(rs.getString("show_date"));
                sm.setShowTime(rs.getString("show_time"));

                list_of_shows.add(sm);
            }
        }

        return list_of_shows;
    }
}
