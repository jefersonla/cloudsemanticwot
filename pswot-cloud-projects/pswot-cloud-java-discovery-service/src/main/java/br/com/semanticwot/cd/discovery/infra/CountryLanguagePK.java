/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.discovery.infra;

import java.io.Serializable;

/**
 *
 * @author nailton
 */
public class CountryLanguagePK implements Serializable {

    private String countryCode;

    private String language;

    public CountryLanguagePK() {
    }

    public CountryLanguagePK(String countryCode, String language) {
        this.countryCode = countryCode;
        this.language = language;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryCode != null ? countryCode.hashCode() : 0);
        hash += (language != null ? language.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountryLanguagePK)) {
            return false;
        }
        CountryLanguagePK other = (CountryLanguagePK) object;
        if ((this.countryCode == null && other.countryCode != null) ||
                (this.countryCode != null &&
                !this.countryCode.equals(other.countryCode))) {
            return false;
        }
        if ((this.language == null && other.language != null) ||
                (this.language != null && !this.language.equals(other.language))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.semanticwot.ws.aula05.hibernate.restful.CountryLanguagePK[ countryCode=" +
                countryCode + ", language=" + language + " ]";
    }
    
}
