/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.conf;

import br.com.semanticwot.cd.controllers.HomeController;
import br.com.semanticwot.cd.daos.SystemUserDAO;
import br.com.semanticwot.cd.infra.FileSaver;
import br.com.semanticwot.cd.infra.MailManager;
import br.com.semanticwot.cd.models.SystemUser;
import br.com.semanticwot.cd.viewresolver.CustomXMLViewResolver;
import br.com.semanticwot.cd.viewresolver.JsonViewResolver;
import com.google.common.cache.CacheBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * Classe para informar a servlet principal quais são as classes de controller
 *
 * @author nailton
 */
@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, SystemUser.class,
    FileSaver.class, SystemUserDAO.class}) // uma classe para cada pacote que deve ser lido
@EnableCaching // habilitando o cache do spring
public class AppWebConfiguration extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // Interceptar parametros da URL e usar para configurar alguma coisa, nesse caso, ele captura o locate e muda o idioma da página
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
    
    @Bean // Esse é o bean que resolve o locate
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }
    
    @Override // caso o spring não consiga resolver a requisição para um controler, ele deixa a cargo do conteiner java web
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() { // Método para definir caminho das views
        InternalResourceViewResolver resolver
                = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposedContextBeanNames("shoppingCart"); // deixa ele visivel
        return resolver;
    }

    @Bean
    public MessageSource messageSource() { // Método para disponibilizar arquivos de mensagens
        ReloadableResourceBundleMessageSource bundle
                = new ReloadableResourceBundleMessageSource();
        bundle.setBasename("/WEB-INF/messages/messages");
        bundle.setDefaultEncoding("UTF-8");
        bundle.setCacheSeconds(1);
        return bundle;
    }

    @Bean
    public FormattingConversionService mvcConversionService() { // Método para setar padrões de conversão
        DefaultFormattingConversionService conversionService
                = new DefaultFormattingConversionService(
                        true);

        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        registrar.registerFormatters(conversionService);
        return conversionService;
    }

    @Bean
    public MultipartResolver multipartResolver() { // Trarar requisições com arquivos em anexo
        return new StandardServletMultipartResolver();
    }

    @Bean
    public RestTemplate restTemplate() { // Para poder injetar um RestTemplate
        return new RestTemplate();
    }
    
    /*@Bean
    public CacheManager cacheManager(){ // modo de cache mais básico
        return new ConcurrentMapCacheManager();
    }*/
    
    @Bean
    public CacheManager cacheManager() { // Cache mais avançado, onde é possivel determinar por exemplo o tempo de duração do cache
        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
                .maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
        GuavaCacheManager cacheManager = new GuavaCacheManager();
        cacheManager.setCacheBuilder(builder);
        return cacheManager;
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) { // Esse método determina os possiveis formatos de retorno da viewResolver
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

        resolvers.add(internalResourceViewResolver());
        resolvers.add(new JsonViewResolver());
        resolvers.add(getMarshallingXmlViewResolver());

        ContentNegotiatingViewResolver resolver
                = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }
    
    @Bean
    public CustomXMLViewResolver getMarshallingXmlViewResolver() { // Resolver para o formato XML
        //Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        //marshaller.setClassesToBeBound(Product.class);
		XStreamMarshaller marshaller = new XStreamMarshaller();
		HashMap<String, Class<?>> keys = new HashMap<String,Class<?>>();
		//keys.put("product", Product.class);
		//keys.put("price", Price.class);
		marshaller.setAliases(keys);
        return new CustomXMLViewResolver(marshaller);
    }
    
    @Bean
    public JavaMailSenderImpl mailSender() { // para consegui fazer a injeção automática

        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost("smtp.gmail.com");
        javaMailSenderImpl.setPassword("senhadeapp");
        javaMailSenderImpl.setPort(587);
        javaMailSenderImpl.setUsername("wiser");
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        javaMailSenderImpl.setJavaMailProperties(mailProperties);

        return javaMailSenderImpl;
    }
}
