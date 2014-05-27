/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musiques.gestionnaires;

import artistes.modeles.Artiste;
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

    @PersistenceContext(unitName = "MusicStorePersistance")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void CreationCatalogue() {
        Artiste a = new Artiste("ACDC","http://fr.wikipedia.org/wiki/AC/DC");
        Morceau m = new Morceau("Highway To hell", "1979", a);
        
        Piste piste1 = new Piste("Drums 2 Bass drum", 5, m);
        Piste piste2 = new Piste("Drums 3 snare", 5, m);
        Piste piste3 = new Piste("Drums 5 hi-hat cymbals", 5, m);
        Piste piste4 = new Piste("Drums 6 hi-hat cymbals", 5, m);
        Piste piste5 = new Piste("Drums 2 Bass drum", 5, m);
        Piste piste6 = new Piste("Guitar 1 Malcolm", 5, m);
        Piste piste7 = new Piste("Guitar 2 Angus", 5, m);
        Piste piste8 = new Piste("Vocals 1 main", 5, m);
        Piste piste9 = new Piste("Vocals 2 main", 5, m);
        Piste piste10 = new Piste("Vocals 3 backing vocals guitar overdubs", 5, m);
        Piste piste11 = new Piste("Vocals 4 backing vocals", 5, m);

        em.persist(piste1);
        em.persist(piste2);
        em.persist(piste3);
        em.persist(piste4);
        em.persist(piste5);
        em.persist(piste6);
        em.persist(piste7);
        em.persist(piste8);
        em.persist(piste9);
        em.persist(piste10);
        em.persist(piste11);

        a.addMorceau(m);
        m.addPiste(piste1);
        m.addPiste(piste2);
        m.addPiste(piste3);
        m.addPiste(piste4);
        m.addPiste(piste5);
        m.addPiste(piste6);
        m.addPiste(piste7);
        m.addPiste(piste8);
        m.addPiste(piste9);
        m.addPiste(piste10);
        m.addPiste(piste11);
        em.persist(m);
        em.persist(a);
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
