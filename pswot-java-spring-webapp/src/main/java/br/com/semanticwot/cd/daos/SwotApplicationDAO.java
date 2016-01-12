/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.daos;

import br.com.semanticwot.cd.models.SwotApplication;
import br.com.semanticwot.cd.models.SwotApplicationForm;
import br.com.semanticwot.cd.models.SystemUser;
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
public class SwotApplicationDAO extends AbstractJpaDAO<SwotApplication>
        implements ISwotApplicationDAO {

    @PersistenceContext // Diz que a classe requer um objeto de persistencia
    private EntityManager entityManager;

    @Override
    public SwotApplication findByName(String name) {
        TypedQuery<SwotApplication> query = entityManager
                .createQuery(
                        "select distinct(p) from SwotApplication p where p.name=:name",
                        SwotApplication.class).setParameter("name", name);
        return query.getSingleResult();
    }

    // Falta fazer
    @Override
    public List<SwotApplication> findByUser(SystemUser systemUser) {
        TypedQuery<SwotApplication> query = entityManager.createQuery(
                "select distinct(p) from SwotApplication p join fetch p.systemUser",
                SwotApplication.class).setParameter("login", systemUser);
        return query.getResultList();
    }

    @Override
    public SwotApplication findOne(SystemUser systemUser) {
        TypedQuery<SwotApplication> query = entityManager
                .createQuery(
                        "select distinct(p) from SwotApplication "
                                + "p where p.systemUser=:login",
                        SwotApplication.class).setParameter("login", systemUser);
        return query.getSingleResult();

    }

}
