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
public class Excursion {
     private int id;
     private float prix ;
     private String Nom,Description,Type,Lieu,Image,valabilite,date;

    public Excursion() {
    }

    public Excursion(float prix, String Nom, String Description, String Type, String Lieu, String Image, String valabilite, String date) {
        this.prix = prix;
        this.Nom = Nom;
        this.Description = Description;
        this.Type = Type;
        this.Lieu = Lieu;
        this.Image = Image;
        this.valabilite = valabilite;
        this.date = date;
    }
    
    public Excursion(int id, float prix, String Nom, String Description, String Type, String Lieu, String Image, String valabilite, String date) {
        this.id = id;
        this.prix = prix;
        this.Nom = Nom;
        this.Description = Description;
        this.Type = Type;
        this.Lieu = Lieu;
        this.Image = Image;
        this.valabilite = valabilite;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String Lieu) {
        this.Lieu = Lieu;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getValabilite() {
        return valabilite;
    }

    public void setValabilite(String valabilite) {
        this.valabilite = valabilite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
     
     
}
