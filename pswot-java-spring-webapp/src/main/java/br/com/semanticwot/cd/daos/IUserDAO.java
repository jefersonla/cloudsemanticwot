/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.daos;

import br.com.semanticwot.cd.models.SystemUser;
import java.util.List;

/**
 *
 * @author nailton
 */
public interface IUserDAO {

    List<SystemUser> findAll();

    void create(SystemUser entity);

    SystemUser update(SystemUser entity);

    void delete(SystemUser entity);
    
    void deleteById(String entityId);

    SystemUser findOne(String id);
    
}
