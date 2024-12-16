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
    
    public List<Proizvod> findAll(Connection con) throws SQLException {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Proizvod> proizvodi = new ArrayList<>();
    try {
        ps = con.prepareStatement("SELECT * FROM proizvod");
        rs = ps.executeQuery();
        while (rs.next()) {
            Prostor prostor = ProstorDao.getInstance().findById(rs.getInt("fk_prostor"), con);
            Proizvod proizvod = new Proizvod(rs.getInt("proizvod_id"), prostor,rs.getString("naziv"), rs.getString("tip"), rs.getString("tezina"), rs.getInt("kolicina"), rs.getString("napomena"));
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
    
   
    
    public int insert(Proizvod proizvod, Connection con) throws SQLException, RobaException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO proizvod(naziv, tip, tezina, kolicina, napomena, fk_prostor) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proizvod.getNaziv());
            ps.setString(2, proizvod.getTip());
            ps.setString(3, proizvod.getTezina());
            ps.setInt(4, proizvod.getKolicina());
            ps.setString(5, proizvod.getNapomena());
            Prostor prostor = ProstorDao.getInstance().findByName(proizvod.getProstor().getImeMagacina(), con);
            
            if (prostor == null) {
                throw new RobaException("Prostor " + proizvod.getProstor() + " doesn't exist in database.");
            }
            ps.setInt(6, prostor.getIdProstor());
            //ps.setInt(6,2);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return id;
    }
    
    public void update(Proizvod proizvod, Connection con) throws SQLException {
        PreparedStatement ps = null;
        
        try {
            ps = con.prepareStatement("UPDATE proizvod SET kolicina=? WHERE proizvod_id=?");
            ps.setInt(1, proizvod.getKolicina());
            ps.setInt(2, proizvod.getIdProizvod());
            ps.executeUpdate();
        } finally {
            ResourceMenager.closeResources(null, ps);
        }
    }
    
    public void delete(Proizvod proizvod, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {


            //delete product
            ps = con.prepareStatement("DELETE FROM proizvod WHERE proizvod_id=?");
            ps.setInt(1, proizvod.getIdProizvod());
            ps.executeUpdate();
        } finally {
            ResourceMenager.closeResources(null, ps);
        }
    }
    
    
}
