/* Created on 29 juil. 2005 */
package com.bankonet.service;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author sysdeo
 */
public class BanqueServiceManager {

    private static BanqueService banqueService = null;

    private static void loadBanqueService() {
        try {
            ResourceBundle rb = PropertyResourceBundle.getBundle("bdconf");

            String accesDonnees = rb.getString("acces_donnees");
            if (accesDonnees.equals("classique")) {
                banqueService = new BanqueServiceClassique();
            }
            else if (accesDonnees.equals("bouchon")) {
                banqueService = new BanqueServiceBouchon();
            }
            else {
                throw new RuntimeException("la valeur de la clé 'acces_donnees' dans le fichier properties n'est pas correcte");
            }
        } catch (MissingResourceException e) {
            throw new RuntimeException("Le mode d'acces aux donnees n'a pas ete specifie dans le fichier properties");
        }
    }

    public static BanqueService getBanqueService() {
        if (banqueService == null) {
            loadBanqueService();
        }
        return banqueService;
    }
}