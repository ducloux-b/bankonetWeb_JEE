/* Created on 23 juin 2005 */
package com.bankonet.service;

import java.util.ArrayList;

import com.bankonet.model.BankonetException;
import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;
import com.bankonet.model.Virement;

/**
 * @author sysdeo
 */
class BanqueServiceBouchon implements BanqueService {

    public Client findClient(String login, String motDePasse) throws BankonetException {
        Client c = new Client(10, "bouchon", "john");

        ArrayList courantList = new ArrayList();
        courantList.add(new CompteCourant(12, "compte courant bouchon", 200, 1000));
        c.setCompteCourantList(courantList);

        ArrayList epargneList = new ArrayList();
        epargneList.add(new CompteEpargne(13, "compte epargne bouchon", 1000, 0.5f, 500));
        c.setCompteEpargneList(epargneList);
        return c;
    }

    public Virement effectuerVirement(Compte cptSource, Compte cptDest, float montant) throws BankonetException {
        System.out.println("demande de virement dans le bouchon. Le virement n'est pas effectue");
        return new Virement(cptSource, cptDest, montant);

    }

}