/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class Excursion1 {
            private int id,prix;
    private String Nom_Excursion,Description_Excursion,Type_Excursion,Lieu,Image,valabilite;

    public Excursion1(int id, int prix, String Nom_Excursion, String Description_Excursion, String Type_Excursion, String Lieu, String Image, String valabilite) {
        this.id = id;
        this.prix = prix;
        this.Nom_Excursion = Nom_Excursion;
        this.Description_Excursion = Description_Excursion;
        this.Type_Excursion = Type_Excursion;
        this.Lieu = Lieu;
        this.Image = Image;
        this.valabilite = valabilite;
    }

    @Override
    public String toString() {
        return "Excursion{" + "id=" + id + ", prix=" + prix + ", Nom_Excursion=" + Nom_Excursion + ", Description_Excursion=" + Description_Excursion + ", Type_Excursion=" + Type_Excursion + ", Lieu=" + Lieu + ", Image=" + Image + ", valabilite=" + valabilite + '}';
    }

    public Excursion1() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNom_Excursion() {
        return Nom_Excursion;
    }

    public void setNom_Excursion(String Nom_Excursion) {
        this.Nom_Excursion = Nom_Excursion;
    }

    public String getDescription_Excursion() {
        return Description_Excursion;
    }

    public void setDescription_Excursion(String Description_Excursion) {
        this.Description_Excursion = Description_Excursion;
    }

    public String getType_Excursion() {
        return Type_Excursion;
    }

    public void setType_Excursion(String Type_Excursion) {
        this.Type_Excursion = Type_Excursion;
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

}
