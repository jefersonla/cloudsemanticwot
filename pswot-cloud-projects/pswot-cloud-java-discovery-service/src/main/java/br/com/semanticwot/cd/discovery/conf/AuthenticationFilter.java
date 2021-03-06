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

/**
 *
 * @author nailton
 */
import br.com.semanticwot.cd.discovery.models.Role;
import br.com.semanticwot.cd.discovery.models.SystemUser;
import br.com.semanticwot.cd.discovery.models.SystemUserFacade;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This filter verify the access permissions for a user based on username and
 * password provided in request
 * */
//@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(
            Response.Status.UNAUTHORIZED).build();
    private static final Response ACCESS_FORBIDDEN = Response.status(
            Response.Status.FORBIDDEN).build();

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext
                    .getHeaders();

            //Fetch authorization header
            final List<String> authorization = headers.get(
                    AUTHORIZATION_PROPERTY);

            //If no authorization information present; block access
            if (authorization == null || authorization.isEmpty()) {
                requestContext.abortWith(ACCESS_DENIED);
                return;
            }

            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0)
                    .replaceFirst(AUTHENTICATION_SCHEME + " ", "");

            //Decode username and password
            String usernameAndPassword = new String(Base64.decode(
                    encodedUserPassword.getBytes()));

            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(
                    usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            //Verifying Username and password
            //System.out.println(username);
            //System.out.println(password);
            //Verify user access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(
                        RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(
                        rolesAnnotation.value()));

                //Is user valid?
                if (!isUserAllowed(username, password, rolesSet)) {
                    requestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            }
        }
    }

    // Aqui eu pego o usário e senha do banco
    // Encriptar o password com Crypto antes de verificar
    // Usar a mesma base que a aplicação web
    // Testado e funcionando
    private boolean isUserAllowed(final String username, final String password,
            final Set<String> rolesSet) {
        boolean isAllowed = false;

        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
        SystemUserFacade facade = new SystemUserFacade();
        SystemUser user = facade.find(username);

        // Não existe usuário com esse nome
        if (user == null) {
            return false;
        }

        // Debug de dados do usuário
        //System.out.println("DISCOVERY " + user.getLogin());
        //System.out.println("DISCOVERY " + user.getPassword());
        //System.out.println("DISCOVERY " + password);
        //System.out.println("DISCOVERY " + BCryptpassword);

        // Verificando se a senha confere, tem que usar o matches, pois
        // o hash não é o mesmo para os mesmos caracteres
        // parece que o hash é diferente para cada aplicação
        // mais ele consegue validar se o hash é válido
        if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {

            System.out.println("DISCOVERY OK PASSWORD");
            
            for (Role role : user.getRoleCollection()) {
                System.out.println("DISCOVERY " + role.getName());
                //Step 2. Verify user role
                if (rolesSet.contains(role.getName())) {
                    isAllowed = true;
                }
            }
        }
        return isAllowed;
    }
}
