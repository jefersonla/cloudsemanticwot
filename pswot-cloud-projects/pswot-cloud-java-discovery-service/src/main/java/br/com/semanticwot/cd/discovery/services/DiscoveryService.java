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

package br.com.semanticwot.cd.discovery.services;

import br.com.semanticwot.cd.discovery.infra.City;
import br.com.semanticwot.cd.discovery.infra.JerseyClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/* Esse serviço deve atender a essas requisições, o mesmo para umidity */
// http://localhost:8080/discovery/v0.1/temperature/query/?location=Salvador&datestart=2016-01-05&dateend=2016-01-22
// http://localhost:8080/discovery/v0.1/temperature/?location=salvador&matching=exact
//1) Adicionar o cache 
//2) Adicionar o interceptador para requisições assincronas, - OK
//      ver se funcionou o gerado automáticamente pelo netbeans - OK
//3) Aqui eu vou primeiro usar o location para recuperar a localização 
//      com o geonames, entender ele
//4) Adicionar o serviço de query com o TDB, testar, usar o Jena
//5) Adicionar o serviço de POST no TDB, testar
//6) Colocar o Swagger para gerar a documentação do serviço, aprender a usar
@Path("")
@Consumes({MediaType.TEXT_XML, MediaType.APPLICATION_XML,
    MediaType.APPLICATION_JSON})
@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_XML,
    MediaType.APPLICATION_JSON})
public class DiscoveryService {

    @GET
    @Path("{type}")
    public Response discovery(@PathParam("type") String typeOfDevice,
            @QueryParam("location") String location,
            @QueryParam("matching") String matching) {

        if (location == null || matching == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        // Dessa forma eu dispenso ter que usar classes como empacotadores de entidades,
        // ou seja, usando o GenericType do JAX-RS
        JerseyClient client = new JerseyClient();
        Object city = client.findAll_JSON(Object.class);
         // do whatever with response
        client.close();
        
        return Response.ok((ArrayList<City>) city).build();
    }

    @GET
    @Path("{type}/query")
    public Response query(@PathParam("type") String typeOfDevice,
            @QueryParam("location") String location,
            @QueryParam("datestart") String datestart,
            @QueryParam("dateend") String dateend) {

        // Verificando se foram passados todos os parametros
        if (location == null || datestart == null || dateend == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }

        // Verificando se as datas estão corretas
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date datestartParsed = format.parse(datestart);
            Date dateendParsed = format.parse(dateend);
        } catch (ParseException ex) {
            Logger.getLogger(DiscoveryService.class.getName())
                    .log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        // Criando a consulta SPARQL
        
        return Response.ok().build();
    }

}
