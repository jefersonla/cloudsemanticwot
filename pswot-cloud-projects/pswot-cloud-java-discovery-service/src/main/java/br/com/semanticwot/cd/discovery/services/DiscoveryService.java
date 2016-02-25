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

import br.com.semanticwot.cd.discovery.cache.Cached;
import br.com.semanticwot.cd.discovery.services.interfaces.Discoverable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.Extension;
import io.swagger.annotations.ExtensionProperty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

/* Esse serviço deve atender a essas requisições, o mesmo para umidity */
// http://localhost:8080/discovery/v0.1/temperature/query/?location=Salvador&datestart=2016-01-05&dateend=2016-01-22
// http://localhost:8080/discovery/v0.1/temperature/?location=salvador&matching=exact
//1) Adicionar o cache - OK
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
@Cached
@Api(value = "discovery")
public class DiscoveryService implements Discoverable {

    @Context
    private UriInfo uriInfo;

    @RolesAllowed("ROLE_ADMIN")
    @GET
    @Path("{type}")
    @ApiOperation(value = "Finds Services by type and location",
            notes = "The location is based in Geonames, and "
                    + "the type is based in SSN Ontology",
            response = Object.class,
            responseContainer = "List",
            extensions = {
                @Extension(name = "outra", properties = {
                    @ExtensionProperty(name = "type", value = "temperature")
                })
            })
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid request")})
    public Response discovery(
            @ApiParam(value = "Type of the device sensor", required = true)
            @PathParam("type") String typeOfDevice,
            @ApiParam(value = "Location used for the search", required = true) 
            @QueryParam("location") String location,
            @ApiParam(value = "Restrict of the semantic search", required = true)
            @QueryParam("matching") @DefaultValue("exact") String matching) {

        if (location == null || matching == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        // Adicionando a busca com o Geonames
        // Tive que criar um usuário no site do Geonames
        // Tive que instalar a dependencia do Geonames no repositório local 
        // com o projeto de testes por que a versão mais nova da API não 
        // está no repositório Maven
        //WebService.setUserName("pswot");
        // O identificador único do local é o id dele, presente tambem na URI
        // ex: http://sws.geonames.org/3450554/, indica salvador
//        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
//        searchCriteria.setQ(location);
//        ToponymSearchResult searchResult;
//        try {
//            searchResult = WebService.search(searchCriteria);
//            for (Toponym toponym : searchResult.getToponyms()) {
//                // Imprime o nome do local, o país e o id dele
//                System.out.println(toponym.getName() + " " + toponym
//                        .getCountryName() + " " + toponym.getGeoNameId());
//            }
//            
//        } catch (Exception ex) {
//            Logger.getLogger(DiscoveryService.class.getName()).log(Level.SEVERE,
//                    null, ex);
//            throw new WebApplicationException(Response.Status.BAD_REQUEST);
//        }
        return Response.ok("{}").build();
    }

    @RolesAllowed("ROLE_ADMIN")
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
