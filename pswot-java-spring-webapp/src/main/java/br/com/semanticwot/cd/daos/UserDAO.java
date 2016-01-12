/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.daos;

import br.com.semanticwot.cd.models.SystemUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nailton
 */
@Repository
public class UserDAO extends AbstractJpaDAO< SystemUser> implements IUserDAO {

    @PersistenceContext // Diz que a classe requer um objeto de persistencia
    private EntityManager entityManager;

    public UserDAO() {
        super();
        setClazz(SystemUser.class);
    }
    
    @Override
    public SystemUser findOne( String id ){
      return entityManager.find( SystemUser.class, id );
   }
    
    @Override
    public void deleteById( String entityId ){
      SystemUser entity = findOne( entityId );
      delete( entity );
   }

}
