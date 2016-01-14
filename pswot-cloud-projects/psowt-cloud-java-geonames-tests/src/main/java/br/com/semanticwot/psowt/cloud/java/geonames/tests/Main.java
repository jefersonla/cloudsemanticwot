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
        WebService.setUserName("demo"); // add your username here

        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
        searchCriteria.setQ("zurich");
        ToponymSearchResult searchResult;
        try {
            searchResult = WebService.search(searchCriteria);
            for (Toponym toponym : searchResult.getToponyms()) {
            System.out.println(toponym.getName() + " " + toponym
                    .getCountryName());
        }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
