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

/**
 *
 * @author nailton
 */
public interface ISwotApplicationDAO {
    
    SwotApplication findByName(String name);
    
    List<SwotApplication> findByUser(SystemUser systemUser);
    
    List<SwotApplication> findAll();

    void create(SwotApplication entity);

    SwotApplication update(SwotApplication entity);

    void delete(SwotApplication entity);
    
    void deleteById(long entityId);

    SwotApplication findOne(long id);
    
    SwotApplication findOne(SystemUser systemUser);
    
}
