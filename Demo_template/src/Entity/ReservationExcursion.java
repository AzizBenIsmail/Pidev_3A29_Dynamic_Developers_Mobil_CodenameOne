/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Oumayma
 */
public class ReservationExcursion {
     int id ,idex , nb ;
     String email,dateR;
     
    public ReservationExcursion() {
    }

    public ReservationExcursion(int nb, String email) {
        this.nb = nb;
        this.email = email;
    }
    
    public ReservationExcursion(int idex, int nb, String email) {
        this.idex = idex;
        this.nb = nb;
        this.email = email;
    }

    public ReservationExcursion(int id, int idex, int nb, String email,String dateR) {
        this.id = id;
        this.idex = idex;
        this.nb = nb;
        this.email = email;
        this.dateR=dateR;
    }

    public ReservationExcursion(int idex, int nb, String email,String dateR) {
        this.idex = idex;
        this.nb = nb;
        this.email = email;
        this.dateR=dateR;
    }

    public String getDateR() {
        return dateR;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdex() {
        return idex;
    }

    public void setIdex(int idex) {
        this.idex = idex;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
}
