/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javaee7.social.twitter;

import com.lodgon.dali.core.service.UserService;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author johan
 */
@ManagedBean
@Path("user")
public class TwitterHandler {
        @Inject UserService userService;

        @Path("hello")
        public Response sayHello() {
            System.out.println("TWITTER HANDLE SAYS HELLO");
            return Response.ok().build();
        }
}
