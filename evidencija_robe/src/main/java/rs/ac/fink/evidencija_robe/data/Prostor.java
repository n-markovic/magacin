/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.evidencija_robe.data;
import java.io.Serializable;


public class Prostor implements Serializable{
    private int prostor_id = -1;
    private Radnik radnik;
    private String ime_magacina;

    public Prostor() {
    }

    public Prostor(int prostor_id, Radnik radnik, String ime_magacina) {
        this.prostor_id = prostor_id;
        this.radnik = radnik;
        this.ime_magacina = ime_magacina;
    }
    
    public Prostor(Radnik radnik, String ime_magacina) {
        this.radnik = radnik;
        this.ime_magacina = ime_magacina;
    }
    
    public int getIdProstor() {
        return prostor_id;
    }
    

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }
    
    public String getImeMagacina() {
        return ime_magacina;
    }

    public void setImeMagacina(String ime_magacina) {
        this.ime_magacina = ime_magacina;
    }
    
    
}
