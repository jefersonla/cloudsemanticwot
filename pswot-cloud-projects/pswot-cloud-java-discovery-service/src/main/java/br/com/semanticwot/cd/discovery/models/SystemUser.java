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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nailton
 */
@Entity
@Table(name = "SystemUser")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "SystemUser.findAll", query
            = "SELECT s FROM SystemUser s"),
    @NamedQuery(name = "SystemUser.findByLogin", query
            = "SELECT s FROM SystemUser s WHERE s.login = :login"),
    @NamedQuery(name = "SystemUser.findByIp", query
            = "SELECT s FROM SystemUser s WHERE s.ip = :ip"),
    @NamedQuery(name = "SystemUser.findByName", query
            = "SELECT s FROM SystemUser s WHERE s.name = :name"),
    @NamedQuery(name = "SystemUser.findByPassword", query
            = "SELECT s FROM SystemUser s WHERE s.password = :password"),
    @NamedQuery(name = "SystemUser.findByPerfilstatus", query
            = "SELECT s FROM SystemUser s WHERE s.perfilstatus = :perfilstatus"),
    @NamedQuery(name = "SystemUser.findByPort", query
            = "SELECT s FROM SystemUser s WHERE s.port = :port")})
public class SystemUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ip")
    private String ip;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "perfilstatus")
    private int perfilstatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "port")
    private int port;
    @JoinTable(name = "SystemUser_Role", joinColumns
            = {@JoinColumn(name = "SystemUser_login", referencedColumnName
                        = "login")}, inverseJoinColumns
            = {@JoinColumn(name = "roles_name", referencedColumnName = "name")})
    @ManyToMany
    private Collection<Role> roleCollection;
    @OneToMany(mappedBy = "systemUserlogin")
    private Collection<SwotApplication> swotApplicationCollection;

    public SystemUser() {
    }

    public SystemUser(String login) {
        this.login = login;
    }

    public SystemUser(String login, String ip, int perfilstatus, int port) {
        this.login = login;
        this.ip = ip;
        this.perfilstatus = perfilstatus;
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPerfilstatus() {
        return perfilstatus;
    }

    public void setPerfilstatus(int perfilstatus) {
        this.perfilstatus = perfilstatus;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @XmlTransient
    public Collection<Role> getRoleCollection() {
        return roleCollection;
    }

    public void setRoleCollection(Collection<Role> roleCollection) {
        this.roleCollection = roleCollection;
    }

    @XmlTransient
    public Collection<SwotApplication> getSwotApplicationCollection() {
        return swotApplicationCollection;
    }

    public void setSwotApplicationCollection(
            Collection<SwotApplication> swotApplicationCollection) {
        this.swotApplicationCollection = swotApplicationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.login == null && other.login != null) ||
                (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.semanticwot.cd.discovery.models.SystemUser[ login=" +
                login + " ]";
    }
    
}
