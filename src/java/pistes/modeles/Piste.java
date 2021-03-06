/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pistes.modeles;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import musiques.modeles.Morceau;

/**
 *
 * @author Justin
 */
@Entity
public class Piste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private int difficulte;

    @OneToOne(cascade = {CascadeType.ALL})
    private Morceau morceau;

    public Piste() {
    }

    public Piste(String nom, int difficulte, Morceau morceau) {
        this.nom = nom;
        this.difficulte = difficulte;
        this.morceau = morceau;
    }

    public Piste(String nom, Morceau morceau) {
        this.nom = nom;
        this.morceau = morceau;
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
        if (!(object instanceof Piste)) {
            return false;
        }
        Piste other = (Piste) object;
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

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public Morceau getMorceau() {
        return morceau;
    }

    public void setMorceau(Morceau morceau) {
        this.morceau = morceau;
    }

    @Override
    public String toString() {
        return "pistes.modeles.Piste[ id=" + id + " ]";
    }

}
