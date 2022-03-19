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
public class Restaurant {
        private int id,Num_Tel_Restaurant;
    private String Nom_Restaurant,Adresse_Restaurant,Image,Description_Restaurant;

    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + id + ", Num_Tel_Restaurant=" + Num_Tel_Restaurant + ", Nom_Restaurant=" + Nom_Restaurant + ", Adresse_Restaurant=" + Adresse_Restaurant + ", Image=" + Image + ", Description_Restaurant=" + Description_Restaurant + '}';
    }

    public Restaurant(int id, int Num_Tel_Restaurant, String Nom_Restaurant, String Adresse_Restaurant, String Image, String Description_Restaurant) {
        this.id = id;
        this.Num_Tel_Restaurant = Num_Tel_Restaurant;
        this.Nom_Restaurant = Nom_Restaurant;
        this.Adresse_Restaurant = Adresse_Restaurant;
        this.Image = Image;
        this.Description_Restaurant = Description_Restaurant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_Tel_Restaurant() {
        return Num_Tel_Restaurant;
    }

    public void setNum_Tel_Restaurant(int Num_Tel_Restaurant) {
        this.Num_Tel_Restaurant = Num_Tel_Restaurant;
    }

    public String getNom_Restaurant() {
        return Nom_Restaurant;
    }

    public void setNom_Restaurant(String Nom_Restaurant) {
        this.Nom_Restaurant = Nom_Restaurant;
    }

    public String getAdresse_Restaurant() {
        return Adresse_Restaurant;
    }

    public void setAdresse_Restaurant(String Adresse_Restaurant) {
        this.Adresse_Restaurant = Adresse_Restaurant;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescription_Restaurant() {
        return Description_Restaurant;
    }

    public void setDescription_Restaurant(String Description_Restaurant) {
        this.Description_Restaurant = Description_Restaurant;
    }

}
