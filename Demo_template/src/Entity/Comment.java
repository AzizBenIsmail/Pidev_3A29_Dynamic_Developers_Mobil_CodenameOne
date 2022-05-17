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
public class Comment {
    int id, idpost;
    String Commentaire , Objet;

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }
   
    public Comment() {
    }

    public Comment(int id, String Commentaire, String Objet) {
        this.id = id;
        this.Commentaire = Commentaire;
        this.Objet = Objet;
    }

    public Comment(int id, String Commentaire) {
        this.id = id;
        this.Commentaire = Commentaire;
    }

    public Comment(int id, int idpost, String Commentaire, String Objet) {
        this.id = id;
        this.idpost = idpost;
        this.Commentaire = Commentaire;
        this.Objet = Objet;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public String getObjet() {
        return Objet;
    }

    public void setObjet(String Objet) {
        this.Objet = Objet;
    }
    
    
}
