/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artistes.modeles;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import musiques.modeles.Morceau;

/**
 *
 * @author Justin
 */
@Entity
public class Artiste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String url;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Morceau> morceaux;

    public Artiste() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artiste(String nom, String url) {
        this.nom = nom;
        this.url = url;
        this.morceaux = new HashSet();
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
        if (!(object instanceof Artiste)) {
            return false;
        }
        Artiste other = (Artiste) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Morceau> getMorceaux() {
        return morceaux;
    }

    public void setMorceaux(Set<Morceau> morceaux) {
        this.morceaux = morceaux;
    }

    @Override
    public String toString() {
        return "artistes.modeles.Artiste[ id=" + id + " ]";
    }

    public void addMorceau(Morceau m) {
        this.morceaux.add(m);
    }

}
