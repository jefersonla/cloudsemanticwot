/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.infra;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author nailton
 */
public class PropertiesManager {

    public static String readProperties(String propertie) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "conf.properties";
            input = PropertiesManager.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);
            
            return prop.getProperty(propertie);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

}
