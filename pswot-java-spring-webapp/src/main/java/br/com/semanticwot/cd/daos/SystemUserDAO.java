package br.com.semanticwot.cd.daos;

import br.com.semanticwot.cd.models.SystemUser;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * Classe totalmente gerenciada pelo Spring Security
 * @author nailton
 */
@Repository
public class SystemUserDAO implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public UserDetails loadUserByUsername(String username) // carrega o usuário para o filtro
            throws UsernameNotFoundException {
        String jpql = "select u from SystemUser u where u.login = :login";
        List<SystemUser> users = em.createQuery(jpql, SystemUser.class)
                .setParameter("login", username).getResultList();
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("O usuario " + username
                    + " não existe");
        }
        return users.get(0);
    }

}
