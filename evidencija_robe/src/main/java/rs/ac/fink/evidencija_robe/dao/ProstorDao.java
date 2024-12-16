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
import rs.ac.fink.evidencija_robe.data.Prostor;
import rs.ac.fink.evidencija_robe.data.Radnik;
import rs.ac.fink.evidencija_robe.exception.RobaException;


/**
 *
 * @author Admin
 */
public class ProstorDao {
    
    private static final ProstorDao instance = new ProstorDao();

    public ProstorDao() {
    }
    
    public static ProstorDao getInstance() {
        return instance;
    }
    
    public Prostor findById(int prostor_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Prostor prostor = null;
        try {
            ps = con.prepareStatement("SELECT * FROM prostor where prostor_id=?");
            ps.setInt(1, prostor_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Radnik radnik = RadnikDao.getInstance().find(rs.getInt("fk_radnik"), con);
                prostor = new Prostor(prostor_id, radnik, rs.getString("ime_magacina"));
            }
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return prostor;
    }
    
    public Prostor findByName(String ime_magacina, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Prostor prostor = null;
        try {
            ps = con.prepareStatement("SELECT * FROM prostor where ime_magacina=?");
            ps.setString(1, ime_magacina);
            rs = ps.executeQuery();
            if (rs.next()) {
                Radnik radnik = RadnikDao.getInstance().find(rs.getInt("fk_radnik"), con);
                prostor = new Prostor(rs.getInt("prostor_id"), radnik, ime_magacina);
            }
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return prostor;
    }
    
    public int insert(Prostor prostor, Connection con) throws SQLException, RobaException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO prostor(radnik_id, ime_magacina) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            Radnik radnik = RadnikDao.getInstance().find(prostor.getRadnik().getIdRadnik(), con);
            if (radnik == null) {
                throw new RobaException("Category " + prostor.getRadnik() + " doesn't exist in database.");
            }
            ps.setInt(1, radnik.getIdRadnik());
            ps.setString(2, prostor.getImeMagacina());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourceMenager.closeResources(rs, ps);
        }
        return id;
    }
    
    /*
    public void update(Prostor prostor, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE product SET name=?, price=?, count=?, fk_category=? WHERE id_product=?");
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getCount());
            ps.setInt(4, product.getCategory().getIdCategory());
            ps.setInt(5, product.getIdProduct());
            ps.executeUpdate();
        } finally {
            ResourceMenager.closeResources(null, ps);
        }
    }*/

    public void delete(Prostor prostor, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            //delete purchases of the product
            //PurchaseDao.getInstance().delete(product, con);

            //delete product
            ps = con.prepareStatement("DELETE FROM product WHERE id_product=?");
            ps.setInt(1, prostor.getIdProstor());
            ps.executeUpdate();
        } finally {
            ResourceMenager.closeResources(null, ps);
        }
    }
    
}
