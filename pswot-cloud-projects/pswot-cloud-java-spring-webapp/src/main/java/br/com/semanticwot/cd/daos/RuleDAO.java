/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.daos;

import br.com.semanticwot.cd.models.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nailton
 */
@Repository
public class RuleDAO extends AbstractJpaDAO< Role > implements IRuleDAO {
    
    @PersistenceContext // Diz que a classe requer um objeto de persistencia
    private EntityManager entityManager;
    
    // Regra de negocio
    @Override
    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager
                .createQuery(
                        "select distinct(p) from Role p where p.name=:name",
                        Role.class).setParameter("name", name);
        return query.getSingleResult();
    }
    
}
