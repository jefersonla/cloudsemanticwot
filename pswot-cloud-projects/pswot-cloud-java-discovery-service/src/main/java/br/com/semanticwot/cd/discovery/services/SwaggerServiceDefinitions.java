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

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.ReaderListener;
import io.swagger.models.Swagger;
import io.swagger.models.auth.BasicAuthDefinition;

/**
 *
 * @author nailton
 */
@SwaggerDefinition(
        info = @Info(
                description = "This is the documentation of the "
                + "RESTful API to access Discovery PSWoT functionalities.",
                version = "V0.1",
                title = "Discovery Service",
                termsOfService = "http://semanticwot.com.br/terms.html",
                contact = @Contact(
                        name = "Nailton Andrade",
                        email = "mailto:nailtonjr@dcc.ufba.br",
                        url = "http://semanticwot.com.br"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP},
        tags = {
            @Tag(name = "Private",
                    description = "Tag used to denote operations as private")
        },
        externalDocs = @ExternalDocs(value = "Tutorial API", url
                = "http://semanticwot.com.br/docs.html")
)
public class SwaggerServiceDefinitions implements ReaderListener {

    @Override
    public void beforeScan(Reader reader, Swagger swagger) {
        BasicAuthDefinition basic = new BasicAuthDefinition();
        swagger.addSecurityDefinition("basicAuth", basic);
    }

    @Override
    public void afterScan(Reader reader, Swagger swagger) {

    }
}
