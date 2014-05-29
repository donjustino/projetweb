/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musiques.modeles;

import artistes.modeles.Artiste;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import pistes.modeles.Piste;

/**
 *
 * @author Justin
 */
@Entity
public class Morceau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titre;
    private String annee;

    @OneToOne(cascade = {CascadeType.ALL})
    private Artiste artiste;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Piste> pistes;

    public Morceau() {
    }

    public Morceau(String titre, String annee, Artiste artiste) {
        this.titre = titre;
        this.annee = annee;
        this.artiste = artiste;
        this.pistes = new HashSet();
    }

    public Morceau(String titre, Artiste artiste) {
        this.titre = titre;
        this.artiste = artiste;
        this.pistes = new HashSet();
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
        if (!(object instanceof Morceau)) {
            return false;
        }
        Morceau other = (Morceau) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    @Override
    public String toString() {
        return "musiques.modeles.Morceau[ id=" + id + " ]";
    }

    public Set<Piste> getPistes() {
        return pistes;
    }

    public void setPistes(Set<Piste> pistes) {
        this.pistes = pistes;
    }

    public void addPiste(Piste p) {
        this.pistes.add(p);
    }

}
