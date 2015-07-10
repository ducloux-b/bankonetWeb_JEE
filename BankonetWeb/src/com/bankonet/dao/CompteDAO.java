/* Created on 26 juin 2005 */
package com.bankonet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bankonet.model.BankonetException;
import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

/**
 * @author misvy
 */
public class CompteDAO
{

    public void loadComptes(Client client) throws BankonetException
    {
        Connection connection = null;
        try
        {
            connection = ConnectionManager.getInstance().getConnection();
            Statement statement = connection.createStatement();

            List compteCourantList = new ArrayList();
            List compteEpargneList = new ArrayList();
            ResultSet resultSet = statement
                    .executeQuery("Select ID, LIBELLE, SOLDE, PLAFOND, TAUX, DECOUVERTAUTORISE,DISCRIMINANT FROM compte WHERE ID_CLIENT="
                            + client.getIdentifiant());
            while (resultSet.next())
            {
                if (resultSet.getString("DISCRIMINANT").equals("CC"))
                {
                    CompteCourant cc = buildCompteCourant(resultSet);
                    compteCourantList.add(cc);
                }
                else
                {
                    CompteEpargne ce = buildCompteEpargne(resultSet);
                    compteEpargneList.add(ce);
                }
            }

            client.setCompteCourantList(compteCourantList);
            client.setCompteEpargneList(compteEpargneList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BankonetException(e.getMessage());
        }
        finally
        {
            closeConnection(connection);
        }
    }

    private CompteCourant buildCompteCourant(ResultSet resultSet)
            throws BankonetException
    {
        try
        {
            int id = resultSet.getInt("ID");
            String libelle = resultSet.getString("LIBELLE");
            float solde = resultSet.getFloat("SOLDE");
            float plafond = resultSet.getFloat("PLAFOND");

            float decouvert = resultSet.getFloat("DECOUVERTAUTORISE");
            return new CompteCourant(id, libelle, solde, decouvert);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BankonetException("erreur dans le parcours du ResultSet");
        }
    }

    private CompteEpargne buildCompteEpargne(ResultSet resultSet)
            throws BankonetException
    {
        try
        {
            int id = resultSet.getInt("ID");
            String libelle = resultSet.getString("LIBELLE");
            float solde = resultSet.getFloat("SOLDE");
            float plafond = resultSet.getFloat("PLAFOND");
            float taux = resultSet.getFloat("TAUX");
            return new CompteEpargne(id, libelle, solde, taux, plafond);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BankonetException("erreur dans le parcours du ResultSet");
        }
    }

    public void transfertMontant(Compte cptSource, Compte cptDest, float montant)
            throws BankonetException
    {
        Connection connection=null;
        try
        {
            connection = ConnectionManager.getInstance().getConnection();
            boolean autoCommitDefault = connection.getAutoCommit();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE compte SET SOLDE="
                    + (cptSource.getSolde() - montant) + " WHERE ID="
                    + cptSource.getIdentifiant());
            statement.executeUpdate("UPDATE compte SET SOLDE="
                    + (cptDest.getSolde() + montant) + " WHERE ID="
                    + cptDest.getIdentifiant());
            connection.commit();
            cptSource.setSolde(cptSource.getSolde() - montant);
            cptDest.setSolde(cptDest.getSolde() + montant);
            connection.setAutoCommit(autoCommitDefault);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BankonetException(e.getMessage());
        }
        finally
        {
            closeConnection(connection);
        }
    }

    private void updateSolde(int compteId, float newSolde)
            throws BankonetException
    {
        Connection connection=null;
        try
        {
            connection = ConnectionManager.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE compte SET SOLDE=" + newSolde
                    + " WHERE ID=" + compteId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BankonetException(e.getMessage());
        }
        finally
        {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) throws BankonetException
    {
        try
        {
            if (connection != null && (!connection.isClosed()))
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BankonetException(
                    "erreur pendant la fermeture de la connection");
        }
    }
}