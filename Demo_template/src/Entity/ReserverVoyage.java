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
public class ReserverVoyage {
    int Client,id;

    public int getClient() {
        return Client;
    }

    public void setClient(int Client) {
        this.Client = Client;
    }
    Voyage voyage;  
    String Travel_Class;
    int Age;

    public ReserverVoyage() {
    }

    public ReserverVoyage(int id,String Travel_Class, int Age,Voyage voyage,int Client) {

                        this.id = id;
                this.Client = Client;
        this.voyage = voyage;
        this.Travel_Class = Travel_Class;
        this.Age = Age;
    }
     public ReserverVoyage(String Travel_Class, int Age,Voyage voyage,int Client) {

        this.Client = Client;
        this.voyage = voyage;
        this.Travel_Class = Travel_Class;
        this.Age = Age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }
    

    public String getTravel_Class() {
        return Travel_Class;
    }

    public void setTravel_Class(String Travel_Class) {
        this.Travel_Class = Travel_Class;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
}
