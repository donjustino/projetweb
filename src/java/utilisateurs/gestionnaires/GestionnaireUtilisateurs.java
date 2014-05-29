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

    @PersistenceContext(unitName = "WebPU")
    private EntityManager em;
    private static final long weekend = 60000; ///604800000;
    private static final long mois = 262974383000L;
    private static final long annee = 31556926000L;
    private long avie = 0;

    public void creerUtilisateursDeTest() {
        Utilisateur root = creerUtilisateur("root", "root", "Mulenet", "Justin", "gratuit");
    }

    public Utilisateur creerUtilisateur(String login, String password, String nom, String prenom, String abonnement) {
        System.out.println("Test fonction de création d'utilisateur :");
        int compte = convertAbonnementToInt(abonnement);
        System.out.println("Compte convertit en int :" + compte);
        Utilisateur u = new Utilisateur(login, password, nom, prenom, compte);
        em.persist(u);
        return u;
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

    public Collection<Utilisateur> getUtilisateur(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        return q.getResultList();

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

    public Boolean verifUtilisateur(String login, String password) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login and u.password=:password");
        q.setParameter("password", password);
        q.setParameter("login", login);
        if (q.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public void modifierAbonnement(String login, String typeabonnement) {
        int compte = convertAbonnementToInt(typeabonnement);
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        Utilisateur u = (Utilisateur) q.getSingleResult();
        u.setTypeabonnement(compte);
        u.setJourinscrption(currentTimeMillis());
    }

    public int convertAbonnementToInt(String abonnement) {
        int compte = 0;
        switch (abonnement) {
            case "gratuit":
                compte = 0;
                break;
            case "weekend":
                compte = 1;
                break;
            case "semaine":
                compte = 2;
                break;
            case "mois":
                compte = 3;
                break;
            case "annee":
                compte = 4;
                break;
            case "vie":
                compte = 100;
                break;
            default:
                compte = 0;
        }
        return compte;
    }

    public boolean checkTemps(String login) {

        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        Utilisateur u = (Utilisateur) q.getSingleResult();
        System.out.println("Utilisateur trouvé :" + u.getLogin());
        System.out.println("Type d'abonnement : " + u.getTypeabonnement());
        long tempsabo = giveAbonnement(u.getTypeabonnement());
        System.out.println("Durée d'abonnement : " + tempsabo);
        if (tempsabo == 4) {
            System.out.println("Utilisateur à  vie");
            return true;
        }
        if (tempsabo == 0) {
            System.out.println("Utilisateur gratuit");
            return false;
        }
        long temp = currentTimeMillis() - u.getJourinscrption();
        System.out.println("Temps : " + temp);
        if (temp < tempsabo) {
            System.out.println("Utilisateur avec abonnement OK ");
            return true;
        } else {
            System.out.println("Utilisateur avec abonnement pas valide");
            return false;
        }
    }

    public long giveAbonnement(int typeabonnement) {
        long temps = 0;
        switch (typeabonnement) {
            case 0:
                temps = 0;
                break;
            case 1:
                temps = weekend;
                break;
            case 2:
                temps = mois;
                break;
            case 3:
                temps = annee;
                break;
            case 4:
                temps = avie;
                break;
            default:
                temps = 0;
        }
        return temps;
    }

    public boolean checkAbonnement(String login) {
        if (checkTemps(login) == true) {
            System.out.println("Utilisateur abo OK");
            return true;
        } else {
            System.out.println("Utilisateur gratuit");
        }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
