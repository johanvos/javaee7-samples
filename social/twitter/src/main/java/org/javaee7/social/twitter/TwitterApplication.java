/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.social.twitter;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author johan
 */
@ApplicationPath("rest")
public class TwitterApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically re-generated by NetBeans REST support to populate
     * given list with all resources defined in the project.
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(com.lodgon.dali.core.social.connect.resource.DaliCoreSocialHandler.class);
    resources.add(org.glassfish.jersey.client.filter.HttpDigestAuthFilter.class);
    resources.add(org.javaee7.social.twitter.TwitterHandler.class);
    resources.add(org.javaee7.social.twitter.UserHandler.class);
    }
    
    
}
