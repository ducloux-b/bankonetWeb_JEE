/* Created on 23 juin 2005 */
package com.bankonet.service;

import com.bankonet.dao.ClientDAO;
import com.bankonet.dao.CompteDAO;
import com.bankonet.model.BankonetException;
import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.Virement;

/**
 * @author sysdeo
 */
  class BanqueServiceClassique implements BanqueService {
    CompteDAO compteDAO = new CompteDAO();

    ClientDAO clientDAO = new ClientDAO();

    public Client findClient(String login, String motDePasse) throws BankonetException {
        Client client = clientDAO.findClient(login, motDePasse);
        loadComptes(client);
        return client;
    }

    private void loadComptes(Client client) throws BankonetException {
        compteDAO.loadComptes(client);
    }

    public Virement effectuerVirement(Compte cptSource, Compte cptDest, float montant) throws BankonetException {
        float cptSourceSoldeOrigine = cptSource.getSolde();
        float cptDestSoldeOrigine = cptDest.getSolde();

        try {
            if (cptSource.getIdentifiant() == cptDest.getIdentifiant()) {
                throw new BankonetException("les deux comptes doivent etre differents");
            }

            if (cptSource.debitAutorise(montant) && (cptDest.creditAutorise(montant))) {
                compteDAO.transfertMontant(cptSource, cptDest, montant);
            }
        } catch (BankonetException e) {
            cptSource.setSolde(cptSourceSoldeOrigine);
            cptDest.setSolde(cptDestSoldeOrigine);
            // Lancer une exception
            throw new BankonetException(e.getMessage());
        }
        return new Virement(cptSource, cptDest, montant);
    }

}