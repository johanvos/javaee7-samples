/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.social.vote;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author joeri
 */
@javax.ws.rs.ApplicationPath("rest")
public class VoteApplication extends Application {

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
		resources.add(org.javaee7.social.vote.ContentHandler.class);
		resources.add(org.javaee7.social.vote.UserHandler.class);
		resources.add(org.javaee7.social.vote.VoteHandler.class);
	}
	
}
