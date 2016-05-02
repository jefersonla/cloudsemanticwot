package me.rerun.karafcxf.service.impl;

import avahi4j.exceptions.Avahi4JException;
import java.io.IOException;
import java.lang.reflect.Field;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private Thread thread;
    
    public void start(BundleContext context) throws Avahi4JException, 
            IllegalArgumentException, NoSuchFieldException, 
            IllegalAccessException, IOException, Exception {
        
        // http://njbartlett.name/2014/05/26/static-linking.html
        System.setProperty("java.library.path", "/usr/lib/jni");
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);
        
        thread = new Thread(new TestServiceBrowser());
        thread.start();
        
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
        thread.interrupt();
        thread.stop();
    }

}