/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.psowt.cloud.java.geonames.tests;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

/**
 *
 * @author nailton
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Criar um usuário no Geonames
        WebService.setUserName("****");

        // O identificador único do local é o id dele, presente tambem na URI
        // ex: http://sws.geonames.org/3450554/, indica salvador
        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setQ("salvador bahia");
        ToponymSearchResult searchResult;
        try {
            searchResult = WebService.search(searchCriteria);
            for (Toponym toponym : searchResult.getToponyms()) {
            // Imprime o nome do local, o país e o id dele
            System.out.println(toponym.getName() + " " + toponym
                    .getCountryName() + " " + toponym.getGeoNameId());
        }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
