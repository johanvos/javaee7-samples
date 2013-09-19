package org.javaee7.social.user;

import com.lodgon.dali.core.entity.User;
import com.lodgon.dali.core.service.DaliCoreException;
import com.lodgon.dali.core.service.UserService;
import java.net.URI;
import java.net.URISyntaxException;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author johan
 */
@ManagedBean
@Path("user")
public class UserHandler {
    
  //  @Inject
    //UserStorageService userStorage;
    @Inject UserService userService;

		@Context
		HttpServletRequest request;

    @POST
    @Path("create")
    public Response createUser (@FormParam("name") String name
            , @FormParam("email") String email, @FormParam("password") String password)
            throws DaliCoreException, URISyntaxException {
        System.out.println("Creating user named "+name);
        System.out.println("userservice = "+userService);
    //    System.out.println("userstorage = "+userStorage);
        User user = new User();
        user.setEmail(email);
        user.setScreenName(name);
        User created = userService.create(user);
        userService.setPassword(created, password);
        System.out.println("Created: "+created);

				request.getSession().setAttribute("user", created);
        return Response.seeOther(new URI("/user/welcome.jsp")).build();
    }
    
    @POST
    @Path("validate")
    public Response validateUser(@FormParam("email") String email,
    @FormParam("password") String password) throws DaliCoreException, URISyntaxException {
        System.out.println("email = "+email+" and pwd = "+password);
        User me = userService.validateEmailAndPassword("", email, password);
        System.out.println("Validated? "+me);

				if (me == null) {
					return Response.seeOther(new URI("/user/index.jsp?login=false")).build();
				} else {
					request.getSession().setAttribute("user", me);
					return Response.seeOther(new URI("/user/welcome.jsp")).build();
				}
    }
}
