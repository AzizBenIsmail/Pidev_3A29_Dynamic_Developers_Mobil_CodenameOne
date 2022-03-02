/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Voyage {
    private int id,Prix_Voyage;
    private String Destination,Nom_Voyage,Duree_Voyage,valabilite,Image,date;

    public Voyage() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", Prix_Voyage=" + Prix_Voyage + ", Destination=" + Destination + ", Nom_Voyage=" + Nom_Voyage + ", Duree_Voyage=" + Duree_Voyage + ", valabilite=" + valabilite + ", Image=" + Image + ", date=" + date + '}';
    }
    
 public Voyage(int id) {
        this.id = id;
    }
    public Voyage(int Prix_Voyage, String Destination, String Nom_Voyage, String Duree_Voyage, String valabilite, String Image) {
        this.Prix_Voyage = Prix_Voyage;
        this.Destination = Destination;
        this.Nom_Voyage = Nom_Voyage;
        this.Duree_Voyage = Duree_Voyage;
        this.valabilite = valabilite;
        this.Image = Image;
       // this.date = date;
    }

    public Voyage(int id, int Prix_Voyage, String Destination, String Nom_Voyage, String Duree_Voyage, String valabilite, String Image) {
        this.id = id;
        this.Prix_Voyage = Prix_Voyage;
        this.Destination = Destination;
        this.Nom_Voyage = Nom_Voyage;
        this.Duree_Voyage = Duree_Voyage;
        this.valabilite = valabilite;
        this.Image = Image;
      //  this.date = date;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix_Voyage() {
        return Prix_Voyage;
    }

    public void setPrix_Voyage(int Prix_Voyage) {
        this.Prix_Voyage = Prix_Voyage;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getNom_Voyage() {
        return Nom_Voyage;
    }

    public void setNom_Voyage(String Nom_Voyage) {
        this.Nom_Voyage = Nom_Voyage;
    }

    public String getDuree_Voyage() {
        return Duree_Voyage;
    }

    public void setDuree_Voyage(String Duree_Voyage) {
        this.Duree_Voyage = Duree_Voyage;
    }

    public String getValabilite() {
        return valabilite;
    }

    public void setValabilite(String valabilite) {
        this.valabilite = valabilite;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }




    
}
