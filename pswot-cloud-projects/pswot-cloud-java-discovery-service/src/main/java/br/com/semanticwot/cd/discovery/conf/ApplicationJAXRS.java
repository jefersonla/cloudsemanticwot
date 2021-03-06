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
package br.com.semanticwot.cd.discovery.conf;

import br.com.semanticwot.cd.discovery.cache.CacheInterceptor;
import br.com.semanticwot.cd.discovery.services.DiscoveryService;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.auth.BasicAuthDefinition;
import io.swagger.models.auth.SecuritySchemeDefinition;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;

// Preciso dessa anotação para rodar no Glassfish, 
// é um meio para ele identidicar essa classe como a raiz do serviço RESTFul.
// Preciso do web.xml de qualquer forma.
@ApplicationPath("discovery/v0.1")
public class ApplicationJAXRS extends Application {

    // Configurando o Swagger, pode ser por web.xml
    // porém dessa forma vc tem muito mais controle
    public ApplicationJAXRS() {
        
        // Configurando o Swagger de forma programática
        BeanConfig beanConfig = new BeanConfig();
        // Básico
        //beanConfig.setTitle("Discovery Service");
        //beanConfig.setVersion("0.1");
        //beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/pswot/discovery/v0.1");
        beanConfig.setResourcePackage(DiscoveryService.class.getPackage().getName());
        // Licença
        //beanConfig.setLicense("Apache 2.0");
        //beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        // Contato
        //beanConfig.setContact("mailto:nailtonjr@dcc.ufba.br");
        //beanConfig.setDescription("This is the documentation of the "
        //        + "RESTful API to access Discovery PSWoT functionalities.");
        beanConfig.setScan(true);
        
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages",
                "br.com.semanticwot.pswot.cloud.service.discovery.services");

        return properties;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();

        singletons.add(new JettisonFeature());
        singletons.add(new CacheInterceptor());
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        addOutherRestResourceClasses(resources);
        return resources;
    }

    private void addOutherRestResourceClasses(
            Set<Class<?>> resources) {

        // Recursos do Swagger
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

    }
    
    private void addRestResourceClasses(
            Set<Class<?>> resources) {
        resources.add(
                br.com.semanticwot.cd.discovery.cache.CacheInterceptor.class);
        // Recurso de segurança
        //resources.add(
        //        br.com.semanticwot.cd.discovery.conf.AuthenticationFilter.class);
        resources.add(
                br.com.semanticwot.cd.discovery.conf.CrossOriginResourceSharingFilter.class);
        resources.add(br.com.semanticwot.cd.discovery.services.DiscoveryService.class);
    }

}
