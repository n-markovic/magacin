/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.data;
import java.io.Serializable;

public class Radnik implements Serializable{
    
    private int radnik_id = -1;
    private String ime_i_prezime;
    private String username;
    private String password;
    private String telefon;
    
    public Radnik() {
    }

    public Radnik(String ime_i_prezime, String username, String password, String telefon) {
        this.ime_i_prezime = ime_i_prezime;
        this.username = username;
        this.password = password;
        this.telefon = telefon;
    }
    
    public Radnik(int radnik_id, String ime_i_prezime, String username, String password, String telefon) {
        this.radnik_id = radnik_id;
        this.ime_i_prezime = ime_i_prezime;
        this.username = username;
        this.password = password;
        this.telefon = telefon;
    }
    
    public int getIdRadnik() {
        return radnik_id;
    }
    

    public String getImeiPrezime() {
        return ime_i_prezime;
    }

    public void setImeiPrezime(String ime_i_prezime) {
        this.ime_i_prezime = ime_i_prezime;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTelefon() {
        return telefon;
    }
    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
}


