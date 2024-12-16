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
import rs.ac.fink.evidencija_robe.data.Prostor;
import rs.ac.fink.evidencija_robe.dao.ProstorDao;
import rs.ac.fink.evidencija_robe.dao.ResourceMenager;
import rs.ac.fink.evidencija_robe.exception.RobaException;

public class ProstorService {
    
    private static final ProstorService instance = new ProstorService();
    
    private ProstorService() {
    }

    public static ProstorService getInstance() {
        return instance;
    }
    
    public Prostor findProstorById(int prostorID) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();

            return ProstorDao.getInstance().findById(prostorID, con);

        } catch (SQLException ex) {
            throw new RobaException("Failed to find product with id " + prostorID, ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }
}
