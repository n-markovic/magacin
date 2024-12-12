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
import rs.ac.fink.evidencija_robe.data.Radnik;
import rs.ac.fink.evidencija_robe.dao.RadnikDao;
import rs.ac.fink.evidencija_robe.dao.ResourceMenager;
import rs.ac.fink.evidencija_robe.exception.RobaException;
        
public class RadnikService {
    
    private static final RadnikService instance = new RadnikService();

    private RadnikService() {
    }

    public static RadnikService getInstance() {
        return instance;
    }
    
    public int addNewRadnik(Radnik radnik) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();

            return RadnikDao.getInstance().insert(radnik, con);
        } catch (SQLException ex) {
            throw new RobaException("Failed to add new product " + radnik, ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }
    
    public Radnik findRadnik(int productName) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();

            return RadnikDao.getInstance().find(productName, con);

        } catch (SQLException ex) {
            throw new RobaException("Failed to find worker with id " + productName, ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }
    
}
