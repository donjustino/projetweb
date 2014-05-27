/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilisateurs.gestionnaires;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.currentTimeMillis;
import java.util.Collection;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Justin
 */
@Stateless
public class GestionnaireUtilisateurs {
    @PersistenceContext(unitName = "MusicStorePersistance")
    private EntityManager em;
    
     public void creerUtilisateursDeTest() {
        Utilisateur root = creerUtilisateur("root", "root","Mulenet","Justin","avie");
    }
     

    public Utilisateur creerUtilisateur(String login, String password,String nom,String prenom,String abonnement) {
        Utilisateur u = new Utilisateur(login, password,nom,prenom,abonnement);
        em.persist(u);
        return u;
    }
     public void creeUtilisateurAleatoire(int nb) {
        InputStream is = GestionnaireUtilisateurs.class.getResourceAsStream("dataMar-25-2014.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = "";
        String cvsSplitBy = ",";

        try {
            for (int i = 0; i < nb; i++) {
                line = br.readLine();
                Random randomGenerator = new Random();
                String rdm = Integer.toString(randomGenerator.nextInt(899999) + 100000);
                String[] users = line.split(cvsSplitBy);
                Utilisateur u = new Utilisateur(rdm,"pass");
                em.persist(u);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Collection<Utilisateur> getAllUsers() {
        Query q = em.createQuery("select u from Utilisateur u");
        return q.getResultList();
    }

    public void modifierUtilisateur(String login, String password) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        Utilisateur u = (Utilisateur) q.getSingleResult();
        u.setLogin(login);
        u.setPassword(password);
    }

    // SOLUTION POUR FAIRE LA RECHERCHE !!! //
    //    public Utilisateur chercherUtilisateurId(int id) {
    //        Utilisateur u = em.find(Utilisateur.class, id);
    //        return u;
    //    }
    public Collection<Utilisateur> chercherUtilisateurPrenom(String prenom) {
        Query q = em.createQuery("select u from Utilisateur u where u.login = :login");
        Utilisateur u = em.find(Utilisateur.class, prenom);
        q.setParameter("login", prenom);
        System.out.println("Resultat pour : " + prenom + q.getResultList());
        return q.getResultList();
    }

    public Collection<Utilisateur> chercherUtilisateurNom(String nom) {
        Query q = em.createQuery("select u from Utilisateur u where u.lastname = :nom");
        q.setParameter("nom", nom);
        System.out.println("Resultat pour : " + nom + q.getResultList());
        return q.getResultList();
    }

    public Collection<Utilisateur> chercherUtilisateurLogin(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        return q.getResultList();
    }

    public void supprimerUtilisateur(int id) {
        Utilisateur u = em.find(Utilisateur.class, id);
        em.remove(u);
    }

    
    public  Boolean verifUtilisateur(String login, String password) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login and u.password=:password");
        q.setParameter("password", password);
        q.setParameter("login", login);
        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
        

    }
    public void modifierAbonnement(String login,String typeabonnement) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        Utilisateur u = (Utilisateur) q.getSingleResult();
        u.setTypeabonnement(typeabonnement);
        u.setJourinscrption(currentTimeMillis());
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
