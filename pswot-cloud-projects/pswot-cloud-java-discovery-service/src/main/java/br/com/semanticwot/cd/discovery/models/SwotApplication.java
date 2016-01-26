/*
 * Copyright 2016 Nailton Andrade.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.semanticwot.cd.discovery.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nailton
 */
@Entity
@Table(name = "SwotApplication")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "SwotApplication.findAll", query
            = "SELECT s FROM SwotApplication s"),
    @NamedQuery(name = "SwotApplication.findById", query
            = "SELECT s FROM SwotApplication s WHERE s.id = :id"),
    @NamedQuery(name = "SwotApplication.findByDescription", query
            = "SELECT s FROM SwotApplication s WHERE s.description = :description"),
    @NamedQuery(name = "SwotApplication.findByName", query
            = "SELECT s FROM SwotApplication s WHERE s.name = :name"),
    @NamedQuery(name = "SwotApplication.findByReleaseDate", query
            = "SELECT s FROM SwotApplication s WHERE s.releaseDate = :releaseDate")})
public class SwotApplication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "releaseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;
    @JoinColumn(name = "systemUser_login", referencedColumnName = "login")
    @ManyToOne
    private SystemUser systemUserlogin;

    public SwotApplication() {
    }

    public SwotApplication(Long id) {
        this.id = id;
    }

    public SwotApplication(Long id, String description, String name,
            Date releaseDate) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public SystemUser getSystemUserlogin() {
        return systemUserlogin;
    }

    public void setSystemUserlogin(SystemUser systemUserlogin) {
        this.systemUserlogin = systemUserlogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SwotApplication)) {
            return false;
        }
        SwotApplication other = (SwotApplication) object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.semanticwot.cd.discovery.models.SwotApplication[ id=" + id +
                " ]";
    }
    
}
