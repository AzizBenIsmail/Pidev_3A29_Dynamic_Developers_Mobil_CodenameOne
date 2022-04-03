/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {
    
    private int id;
    private int CIN;
    private String UserName;
    private String Email;
    private String photo;
    private int Numero;
    private String Adresse;
    private String Password;

    public Utilisateur(int CIN, String UserName, String Email, String photo, int Numero, String Adresse, String Password) {
        
        this.CIN = CIN;
        this.UserName = UserName;
        this.Email = Email;
        this.photo = photo;
        this.Numero = Numero;
        this.Adresse = Adresse;
        this.Password = Password;
    }
    
    
    public Utilisateur() {
    }

    public Utilisateur(int id) {
        this.id = id;
    }

    public Utilisateur(String username, String email) {
        this.UserName = username;
        this.Email = email;
    }

    
    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + UserName + ", adresse=" + Email + ", photoDeProfil=" + photo + '}';
    }

    public Utilisateur(int CIN, String UserName, String Email, String photo, String Adresse) {
        this.CIN = CIN;
        this.UserName = UserName;
        this.Email = Email;
        this.photo = photo;
        this.Adresse = Adresse;
      
    }


    public int getId() {
        return id;
    }

    public int getCIN() {
        return CIN;
    }

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoto() {
        return photo;
    }

    public int getNumero() {
        return Numero;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    
    
    
    
    
    
    
    
    
}