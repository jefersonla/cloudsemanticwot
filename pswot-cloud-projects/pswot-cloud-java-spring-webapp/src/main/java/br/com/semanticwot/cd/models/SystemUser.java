package br.com.semanticwot.cd.models;

import br.com.semanticwot.cd.util.PerfilStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class SystemUser implements UserDetails {

        // Adicionar uma lista de aplicações
    // Criar classe de aplicação
    // Criar controler para criar e editar usuário
    // Adicionar campo de IP, usuaŕio e senha para acesso via SSH
    @Id
    private String login;
    private String password;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @NotBlank
    private String ip;
    @NotNull
    private int port;
    
    @NotNull
    private PerfilStatus perfilstatus;

    public PerfilStatus getPerfilstatus() {
        return perfilstatus;
    }

    public void setPerfilstatus(PerfilStatus perfilstatus) {
        this.perfilstatus = perfilstatus;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "SystemUser{" + "login=" + login + ", password=" + password
                + ", name=" + name + ", roles=" + roles + '}';
    }

}
