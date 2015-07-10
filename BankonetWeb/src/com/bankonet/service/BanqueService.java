/* Created on 29 juil. 2005 */
package com.bankonet.service;

import com.bankonet.model.BankonetException;
import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.Virement;

/**
 * @author misvy
 */
public interface BanqueService {
    public Client findClient(String login, String motDePasse) throws BankonetException;

    public Virement effectuerVirement(Compte cptSource, Compte cptDest, float montant) throws BankonetException;
}