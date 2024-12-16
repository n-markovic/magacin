/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import rs.ac.fink.evidencija_robe.data.Proizvod;
import rs.ac.fink.evidencija_robe.dao.ProizvodDao;
import rs.ac.fink.evidencija_robe.dao.ResourceMenager;
import rs.ac.fink.evidencija_robe.exception.RobaException;

public class ProizvodService {
    
    private static final ProizvodService instance = new ProizvodService();
    
    private ProizvodService() {
    }

    public static ProizvodService getInstance() {
        return instance;
    }
    
    public Proizvod findProizvodByName(String productName) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();

            return ProizvodDao.getInstance().findByName(productName, con);

        } catch (SQLException ex) {
            throw new RobaException("Failed to find product with name " + productName, ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }
    
    public Proizvod findProizvodById(int proizvod_id) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();

            return ProizvodDao.getInstance().findById(proizvod_id, con);

        } catch (SQLException ex) {
            throw new RobaException("Failed to find product with id " + proizvod_id, ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }
    
   public List<Proizvod> findProizvodiByType(String productType) throws RobaException {
    Connection con = null;
    try {
        con = ResourceMenager.getConnection();
        return ProizvodDao.getInstance().findByType(productType, con);
    } catch (SQLException ex) {
        throw new RobaException("Failed to find products with type " + productType, ex);
    } finally {
        ResourceMenager.closeConnection(con);
    }
}

    


}