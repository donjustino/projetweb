/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musiques.gestionnaires;

import artistes.modeles.Artiste;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import musiques.modeles.Morceau;
import pistes.modeles.Piste;

/**
 *
 * @author Justin
 */
@Stateless
public class GestionnairesMusiques {

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;

    public void CreationCatalogue() {
        parse();
    }

    public void parse() {
        String titre;
        ArrayList<Piste> morceaux = new ArrayList<Piste>();
//        ArrayList<Instrument> instrument = new ArrayList<>();
//          String nomInstru[];
        boolean ifTitle = true;
        titre = new String();
        String artiste = new String();
        try {
            InputStream flux = new FileInputStream("C:\\Users\\Justin\\Documents\\Netbeansproject\\Web\\src\\java\\utils\\liste_m.txt");
            InputStreamReader lecture = new InputStreamReader(flux);

            BufferedReader buff = new BufferedReader(lecture);
            String ligne = null;
            Artiste a = null;
            Morceau m = null;
            Piste p;
            while ((ligne = buff.readLine()) != null) {
                if (ifTitle == true && !ligne.isEmpty()) {
                    a = new Artiste(ligne.split(" - ")[0]);
                    m = new Morceau(ligne.split(" - ")[1], a);
                    em.persist(m);
                    em.persist(a);
                    ifTitle = false;
                } else if (!ligne.isEmpty() && ifTitle == false) {
                    p = new Piste(ligne, m);
                    m.addPiste(p);
                    em.persist(p);
                } else if (ligne.isEmpty()) {
                    ifTitle = true;
                }
            }
            buff.close();

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public Collection<Morceau> listeDesMorceaux() {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select m from Morceau m");
        return q.getResultList();
    }

    public Collection<Morceau> listedesMorceauxDeTest() {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select m from Morceau m");
        return q.getResultList();
    }
}
