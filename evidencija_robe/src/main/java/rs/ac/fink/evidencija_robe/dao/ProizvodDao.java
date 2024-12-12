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
    
    protected Proizvod find(int proizvod_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Proizvod proizvod = null;
        try {
            ps = con.prepareStatement("SELECT * FROM proizvod where proizvod_id=?");
            ps.setInt(1, proizvod_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Prostor prostor = ProstorDao.getInstance().find(rs.getInt("prostor_id"), con);
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
            ps = con.prepareStatement("INSERT INTO prozivod(prostor_id, naziv, tip, tezina, kolicina, napomena) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            Prostor prostor = ProstorDao.getInstance().find(proizvod.getProstor().getIdProstor(), con);
            if (prostor == null) {
                throw new RobaException("Category " + proizvod.getProstor() + " doesn't exist in database.");
            }
            ps.setInt(1, prostor.getIdProstor());
            ps.setString(2, proizvod.getNaziv());
            ps.setString(3, proizvod.getTip());
            ps.setString(4, proizvod.getTezina());
            ps.setInt(5, proizvod.getKolicina());
            ps.setString(6, proizvod.getNapomena());          
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return id;
    }
    
    
}
