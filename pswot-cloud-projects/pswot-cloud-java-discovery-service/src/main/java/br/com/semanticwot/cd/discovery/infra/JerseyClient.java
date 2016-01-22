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
package br.com.semanticwot.cd.discovery.infra;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/** Jersey REST client generated for REST resource:CityFacadeREST
 * [br.com.semanticwot.ws.aula05.hibernate.restful.city]<br>
 * USAGE:
 * <pre>
 *        JerseyClient client = new JerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author nailton
 */
public class JerseyClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI
            = "http://localhost:8080/WS-Aula05-Hibernate-RESTFul/webresources";

    public JerseyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget
                = client.target(BASE_URI)
                .path("br.com.semanticwot.ws.aula05.hibernate.restful.city");
    }

    public JerseyClient(String username, String password) {
        this();
        setUsernamePassword(username, password);
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id}))
                .request().delete();
    }

    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN)
                .get(String.class);
    }

    public <T> T findAll_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                .get(responseType);
    }

    public <T> T findAll_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .get(responseType);
    }

    public void edit_XML(Object requestEntity, String id) throws
            ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id}))
                .request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                .put(javax.ws.rs.client.Entity.entity(requestEntity,
                javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void edit_JSON(Object requestEntity, String id) throws
            ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id}))
                .request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .put(javax.ws.rs.client.Entity.entity(requestEntity,
                javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void create_XML(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                .post(javax.ws.rs.client.Entity.entity(requestEntity,
                javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void create_JSON(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .post(javax.ws.rs.client.Entity.entity(requestEntity,
                javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T findRange_XML(Class<T> responseType, String from, String to)
            throws ClientErrorException {
        WebTarget resource = webTarget;
        resource
                = resource.path(java.text.MessageFormat.format("{0}/{1}",
                new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                .get(responseType);
    }

    public <T> T findRange_JSON(Class<T> responseType, String from, String to)
            throws ClientErrorException {
        WebTarget resource = webTarget;
        resource
                = resource.path(java.text.MessageFormat.format("{0}/{1}",
                new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .get(responseType);
    }

    public <T> T find_XML(Class<T> responseType, String id) throws
            ClientErrorException {
        WebTarget resource = webTarget;
        resource
                = resource.path(java.text.MessageFormat.format("{0}",
                new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                .get(responseType);
    }

    public <T> T find_JSON(Class<T> responseType, String id) throws
            ClientErrorException {
        WebTarget resource = webTarget;
        resource
                = resource.path(java.text.MessageFormat.format("{0}",
                new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .get(responseType);
    }

    public void close() {
        client.close();
    }

    public final void setUsernamePassword(String username, String password) {
        webTarget.register(new org.glassfish.jersey.client.filter.HttpBasicAuthFilter(username,
                password));
    }
    
}
