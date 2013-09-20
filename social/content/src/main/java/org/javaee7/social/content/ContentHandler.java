package org.javaee7.social.content;

import com.lodgon.dali.core.entity.Content;
import com.lodgon.dali.core.entity.User;
import com.lodgon.dali.core.service.ContentService;
import com.lodgon.dali.core.service.DaliCoreException;
import com.lodgon.dali.core.service.UserService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author joeri
 */
@ManagedBean
@Path("content")
public class ContentHandler {

	@Inject
	ContentService contentService;
	@Inject
	UserService userService;

	@Context
	HttpServletRequest request;

	@Path("signup")
	@POST
	public Response signup(@FormParam("name") String name,
					@FormParam("email") String email,
					@FormParam("password") String password) throws DaliCoreException, URISyntaxException {
		User existing = userService.getByEmail("", email);
		if (existing == null) {
			User user = new User();
			user.setScreenName(name);
			user.setEmail(email);
			User created = userService.create(user);
			userService.setPassword(created, password);

			request.getSession().setAttribute("user", created);
			return Response.seeOther(new URI("/content/rest/content/overview")).build();
		} else {
			return Response.seeOther(new URI("/content/index.jsp?signup=false")).build();
		}
	}

	@Path("signin")
	@POST
	public Response signin(@FormParam("email") String email,
					@FormParam("password") String password) throws DaliCoreException, URISyntaxException {
		User user = userService.validateEmailAndPassword("", email, password);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			return Response.seeOther(new URI("/content/rest/content/overview")).build();
		} else {
			return Response.seeOther(new URI("/content/index.jsp?signin=false")).build();
		}
	}

	@Path("signout")
	@GET
	public Response signout() throws URISyntaxException {
		request.getSession().invalidate();
		return Response.seeOther(new URI("/content/index.jsp")).build();
	}

	@Path("overview")
	@GET
	public Response overview() throws URISyntaxException {
		List<? extends Content> contents = contentService.findByType("", 1);
		request.getSession().setAttribute("contents", contents);
		return Response.seeOther(new URI("/content/overview.jsp")).build();
	}

	@Path("create")
	@POST
	public Response create(@FormParam("title") String title,
					@FormParam("message") String message) throws DaliCoreException, URISyntaxException {
		User author = (User) request.getSession().getAttribute("user");
		if (author != null) {
			Content content = new Content();
			content.setTitle(title);
			content.setContent(message);
			content.setAuthor(author);
			content.setType(1);

			Content created = contentService.create(content);
			return Response.seeOther(new URI("/content/rest/content/overview")).build();
		} else {
			return Response.seeOther(new URI("/content/index.jsp")).build();
		}
	}
}
