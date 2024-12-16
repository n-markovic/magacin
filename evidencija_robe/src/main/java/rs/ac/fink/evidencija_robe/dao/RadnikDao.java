/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rs.ac.fink.evidencija_robe.data.Radnik;


/**
 *
 * @author Admin
 */
public class RadnikDao {
 
    private static final RadnikDao instance = new RadnikDao();

    public RadnikDao() {
    }
    
    public static RadnikDao getInstance() {
        return instance;
    }
    
    public Radnik find(int radnik_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Radnik radnik = null;
        try {
            ps = con.prepareStatement("SELECT * FROM radnik where radnik_id=?");
            ps.setInt(1, radnik_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                radnik = new Radnik(radnik_id, rs.getString("ime_i_prezime"), rs.getString("username"), rs.getString("password"), rs.getString("telefon"));
            }
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return radnik;
    }
    
    public int insert(Radnik radnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO radnik(ime_i_prezime, username, password, telefon) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, radnik.getImeiPrezime());
            ps.setString(2, radnik.getUsername());
            ps.setString(3, radnik.getPassword());
            ps.setString(4, radnik.getTelefon());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return id;
    }

    public void update(Radnik radnik, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE address SET ime_i_prezime=?, username=?, password=?, telefon=? WHERE id_address=?");
            ps.setString(1, radnik.getImeiPrezime());
            ps.setString(2, radnik.getUsername());
            ps.setString(3, radnik.getPassword());
            ps.setString(4, radnik.getTelefon());
            ps.setInt(5, radnik.getIdRadnik());
            ps.executeUpdate();
        } finally {
            ResourceMenager.closeResources(null, ps);
        }
    }

    public void delete(int radnik_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM radnik WHERE radnik_id=?");
            ps.setInt(1, radnik_id);
            ps.executeUpdate();
        } finally {
            ResourceMenager.closeResources(null, ps);
        }
    }
    
    public boolean UserLogin(String username, String password, Connection con) throws SQLException {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        ps = con.prepareStatement("SELECT password FROM radnik WHERE username = ?");
        ps.setString(1, username);
        rs = ps.executeQuery();

        if (rs.next()) {
            String storedPassword = rs.getString("password");
            return storedPassword.equals(password);
        }
    } finally {
        ResourceMenager.closeResources(rs, ps);
    }
    return false;
    }
}
