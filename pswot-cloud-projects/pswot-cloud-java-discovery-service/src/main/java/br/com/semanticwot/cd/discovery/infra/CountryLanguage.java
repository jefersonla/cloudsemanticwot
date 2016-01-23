/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.discovery.infra;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nailton
 */
@XmlRootElement
public class CountryLanguage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected CountryLanguagePK countryLanguagePK;

    private String isOfficial;

    private float percentage;

    private Country country;

    public CountryLanguage() {
    }

    public CountryLanguage(CountryLanguagePK countryLanguagePK) {
        this.countryLanguagePK = countryLanguagePK;
    }

    public CountryLanguage(CountryLanguagePK countryLanguagePK,
            String isOfficial, float percentage) {
        this.countryLanguagePK = countryLanguagePK;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    public CountryLanguage(String countryCode, String language) {
        this.countryLanguagePK = new CountryLanguagePK(countryCode, language);
    }

    public CountryLanguagePK getCountryLanguagePK() {
        return countryLanguagePK;
    }

    public void setCountryLanguagePK(CountryLanguagePK countryLanguagePK) {
        this.countryLanguagePK = countryLanguagePK;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryLanguagePK != null ? countryLanguagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountryLanguage)) {
            return false;
        }
        CountryLanguage other = (CountryLanguage) object;
        if ((this.countryLanguagePK == null && other.countryLanguagePK != null) ||
                (this.countryLanguagePK != null &&
                !this.countryLanguagePK.equals(other.countryLanguagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.semanticwot.ws.aula05.hibernate.restful.CountryLanguage[ countryLanguagePK=" +
                countryLanguagePK + " ]";
    }
    
}
