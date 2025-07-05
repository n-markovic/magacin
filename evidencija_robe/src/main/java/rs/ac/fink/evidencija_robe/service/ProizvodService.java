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
    
    public List<Proizvod> findProizvodiAll() throws RobaException {
    Connection con = null;
    try {
        con = ResourceMenager.getConnection();
        return ProizvodDao.getInstance().findAll(con);
    } catch (SQLException ex) {
        throw new RobaException("There is no products in storage room");
    } finally {
        ResourceMenager.closeConnection(con);
    }
}

   public int addNewProizvod(Proizvod proizvod) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();

            return ProizvodDao.getInstance().insert(proizvod, con);
        } catch (SQLException ex) {
            throw new RobaException("Failed to add new product " + proizvod, ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }
   
    public void updateProizvod(Proizvod proizvod) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();
            con.setAutoCommit(false);
            
            //Proizvod proizvodBaza = ProizvodDao.getInstance().findById(proizvod.getIdProizvod(), con);
            if (proizvod != null) {
                //proizvodBaza.setKolicina(proizvod.getKolicina());
                
                ProizvodDao.getInstance().update(proizvod, con);
            }
            
            con.commit();
        } catch (SQLException ex) {
            ResourceMenager.rollbackTransactions(con);
            throw new RobaException("Failed to update product " + proizvod.getNaziv(), ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }
   
   public void deleteProizvod(String productName) throws RobaException {
        Connection con = null;
        try {
            con = ResourceMenager.getConnection();
            con.setAutoCommit(false);

            Proizvod proizvod = ProizvodDao.getInstance().findByName(productName, con);
            if (proizvod != null) {
                ProizvodDao.getInstance().delete(proizvod, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourceMenager.rollbackTransactions(con);
            throw new RobaException("Failed to delete product with name " + productName, ex);
        } finally {
            ResourceMenager.closeConnection(con);
        }
    }


}
