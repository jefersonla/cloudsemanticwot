package me.rerun.karafcxf.rest;

import me.rerun.karafcxf.service.impl.HelloService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class HelloRestServiceImpl implements HelloRestService{


    //Just like Spring.  Please add Getters/Setters. Blueprint annotations are still work in progress
    private HelloService helloService;


    public String handleGet(String name){

        // Acessando o contexto de outro Bundle pela classe dele.
        BundleContext bundleContext 
                = FrameworkUtil.getBundle(HelloService.class).getBundleContext();
        return helloService.sayHello(String.valueOf(bundleContext.getBundle().getBundleId()));

    }


    /*
        Constructor
     */

    public HelloRestServiceImpl(){
    }

    /*
        Getters and Setters
     */

    public HelloService getHelloService() {
        return helloService;
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

}
