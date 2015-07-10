/* Created on 23 juin 2005 */
package com.bankonet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.bankonet.model.BankonetException;
import com.bankonet.model.Client;

/**
 * @author misvy
 */
public class ClientDAO {

    /**
     * Instancie et fabrique un objet de type Client
     */
    public Client findClient(String p_login, String p_motDePasse)
            throws BankonetException {
        Connection connection =null;
        try {
            connection = ConnectionManager.getInstance()
                    .getConnection();
            
            Statement statement = connection.createStatement();
            String query = "SELECT ID, NOM, PRENOM FROM client WHERE LOGIN='" + p_login
                    + "' and MOTDEPASSE ='" + p_motDePasse + "'";
            ResultSet result = statement.executeQuery(query);

            boolean hasNext = result.next();
            if (hasNext == false) {
                throw new BankonetException(
                        "Client non trouve : login ou mot de passe invalide");
            } else {
                int i = result.getInt("ID");
                String nom = result.getString("NOM");
                String prenom = result.getString("PRENOM");
                Client client = new Client(i, nom, prenom);
                return client;

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BankonetException("Erreur SQL : " + e.getMessage());
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