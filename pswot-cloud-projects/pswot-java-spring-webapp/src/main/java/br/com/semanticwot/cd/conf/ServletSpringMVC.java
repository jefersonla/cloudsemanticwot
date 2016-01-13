/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.conf;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Essa é a classe principal de controller do Spring, é tipo a do JAX-RS, tudo
 * em java Web é Servlet por baixo rsrs
 *
 * @author nailton
 */
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() { // tem que colocar aqui para ser carregada junto com o sring, e não depois
        return new Class[]{AppWebConfiguration.class, JPAConfiguration.class,
            SecurityConfiguration.class, AmazonConfiguration.class, JPAProductionConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(
            ServletRegistration.Dynamic registration) { // Esse método regista onde colocar o arquivo em upload enquanto ele está sendo carregado
        super.customizeRegistration(registration);
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }

    @Override
    public void onStartup(ServletContext servletContext) // Define qual é o contexto ativo no momento
            throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
        servletContext.setInitParameter(
                AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");

    }

    //@Override
    //protected Filter[] getServletFilters() { // habilitar o lazy load
    //    return new Filter[]{new OpenEntityManagerInViewFilter()};
    //}

}
