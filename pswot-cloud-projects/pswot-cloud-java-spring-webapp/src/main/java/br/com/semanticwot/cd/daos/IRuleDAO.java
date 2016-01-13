/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.daos;

import br.com.semanticwot.cd.models.Role;
import java.util.List;

/**
 *
 * @author nailton
 */
public interface IRuleDAO {

    Role findByName(String name);
    
    List<Role> findAll();

    void create(Role entity);

    Role update(Role entity);

    void delete(Role entity);
    
    void deleteById(long entityId);

    Role findOne(long id);
    
}
