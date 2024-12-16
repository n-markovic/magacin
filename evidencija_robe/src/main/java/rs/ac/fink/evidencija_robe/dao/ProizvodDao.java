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
import java.util.ArrayList;
import java.util.List;
import rs.ac.fink.evidencija_robe.data.Proizvod;
import rs.ac.fink.evidencija_robe.data.Prostor;
import rs.ac.fink.evidencija_robe.exception.RobaException;

public class ProizvodDao {
    
    private static final ProizvodDao instance = new ProizvodDao();

    public ProizvodDao() {
    }
    
    public static ProizvodDao getInstance() {
        return instance;
    }
    
    public Proizvod findByName(String naziv, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proizvod proizvod = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proizvod where naziv=?");
            ps.setString(1, naziv);
            rs = ps.executeQuery();
            if (rs.next()) {
                Prostor prostor = ProstorDao.getInstance().findById(rs.getInt("fk_prostor"), con);
                proizvod = new Proizvod(rs.getInt("proizvod_id"), prostor, naziv, rs.getString("tip"), rs.getString("tezina"), rs.getInt("kolicina"), rs.getString("napomena"));
            }
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return proizvod;
    }
    
    public List<Proizvod> findByType(String tip, Connection con) throws SQLException {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Proizvod> proizvodi = new ArrayList<>();
    try {
        ps = con.prepareStatement("SELECT * FROM proizvod WHERE tip=?");
        ps.setString(1, tip);
        rs = ps.executeQuery();
        while (rs.next()) {
            Prostor prostor = ProstorDao.getInstance().findById(rs.getInt("fk_prostor"), con);
            Proizvod proizvod = new Proizvod(rs.getInt("proizvod_id"), prostor,rs.getString("naziv"), tip, rs.getString("tezina"), rs.getInt("kolicina"), rs.getString("napomena"));
            proizvodi.add(proizvod);
        }
    } finally {
        ResourceMenager.closeResources(rs, ps);
    }
    return proizvodi;
}

    
    
    public Proizvod findById(int proizvod_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proizvod proizvod = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proizvod where proizvod_id=?");
            ps.setInt(1, proizvod_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Prostor prostor = ProstorDao.getInstance().findById(rs.getInt("fk_prostor"), con);
                proizvod = new Proizvod(proizvod_id, prostor, rs.getString("naziv"), rs.getString("tip"), rs.getString("tezina"), rs.getInt("kolicina"), rs.getString("napomena"));
            }
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return proizvod;
    }
    
    
    
    
}
