package br.com.semanticwot.pswot.gateway.service.zeroconf;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        // TODO add activation code here
        System.out.println("Primeiro bundle oi");
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
        System.out.println("Primeiro bundle tchau");
    }

}
