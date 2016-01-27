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
package br.com.semanticwot.cd.discovery.cache;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.message.internal.DateProvider;

@Cached
@Provider
public class CacheInterceptor implements ContainerRequestFilter,
        ContainerResponseFilter {

    private EntityCache entityCache;
    private DateProvider dateProvider;

    public CacheInterceptor() {
        this.entityCache = new EntityCache();
        this.dateProvider = new DateProvider();
    }

	//Invocado na requisição 
    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {

        if (requestContext.getMethod().equals("GET")) {
            String unparsedDate = requestContext
                    .getHeaderString("If-Modified-Since");
            if (StringUtils.isNotEmpty(unparsedDate)) {

                Date date = dateProvider.fromString(unparsedDate);
                String path = requestContext.getUriInfo().getPath();

                if (!entityCache.isUpdated(path, date)) {

                    // Modificado de NOT_MODIFIED para FOUND, pois o primeiro não
                    // permite anexar uma entidade na resposta
                    // e a semantica do FOUND cabe aqui.
                    // Cache é muito importante, por causa do custo computacional
                    // do raciocinio em ontologias
                    Response response = Response.status(Status.FOUND)
                            .entity(entityCache.getEntityDatePairEntity(path))
                            .build();
                    requestContext.abortWith(response);
                }
            }
        }

    }

    // Invocado na resposta, para qualquer tipo de requisição.
    // Se eu mandar um POST, ele atualiza o path com a data atual
    @Override
    public void filter(ContainerRequestContext requestContext,
            ContainerResponseContext responseContext) throws IOException {

        Object entity = responseContext.getEntity();
        String path = requestContext.getUriInfo().getPath();

        entityCache.put(path, entity);
    }

}
