/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilisateurs.modeles;

import java.io.Serializable;
import static java.lang.System.currentTimeMillis;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Justin
 */
@Entity
public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTypeabonnement() {
        return typeabonnement;
    }

    public void setTypeabonnement(String typeabonnement) {
        this.typeabonnement = typeabonnement;
    }

    public long getJourinscrption() {
        return jourinscrption;
    }

    public void setJourinscrption(long jourinscrption) {
        this.jourinscrption = jourinscrption;
    }
    private String nom;
    private String prenom;
    private String typeabonnement;
    private long jourinscrption;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Utilisateur() {
    }
    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;
    }
     public Utilisateur(String login, String password,String nom,String prenom,String abonnement) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.typeabonnement = abonnement;
        this.jourinscrption = currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utilisateurs.modeles.Utilisateur[ id=" + id + " ]";
    }
    
    
}
