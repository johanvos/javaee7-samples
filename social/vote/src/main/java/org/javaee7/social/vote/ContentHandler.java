package org.javaee7.social.vote;

import com.lodgon.dali.core.entity.Content;
import com.lodgon.dali.core.entity.User;
import com.lodgon.dali.core.service.ContentService;
import com.lodgon.dali.core.service.DaliCoreException;
import com.lodgon.dali.core.storage.ContentStorageService;
import com.lodgon.dali.core.storage.FetchType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	ContentStorageService contentStorage;

	@Context
	HttpServletRequest request;

	@Path("overview")
	@GET
	public Response overview() throws URISyntaxException {
		List<? extends Content> messages = contentService.findByType("", 1);
		Map<String, List<? extends Content>> comments = new HashMap<>();
		for (Content message : messages) {
			comments.put(message.getId(), contentService.findByParent(message, 2, 1));
		}
		request.getSession().setAttribute("messages", messages);
		request.getSession().setAttribute("comments", comments);
		return Response.seeOther(new URI("/vote/overview.jsp")).build();
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
			content.setScore(0);

			Content created = contentService.create(content);
			return Response.seeOther(new URI("/vote/rest/content/overview")).build();
		} else {
			return Response.seeOther(new URI("/vote/index.jsp")).build();
		}
	}

	@Path("{contentId}/comment")
	@POST
	public Response comment(@PathParam("contentId") String contentId,
					@FormParam("message") String message) throws DaliCoreException, URISyntaxException {
		User author = (User) request.getSession().getAttribute("user");
		Content content = contentStorage.get(contentId, FetchType.NONE);
		if (author != null && content != null) {
			Content comment = new Content();
			comment.setContent(message);
			comment.setParent(content);
			comment.setAuthor(author);
			comment.setType(2);
			comment.setScore(0);

			Content created = contentService.create(comment);
			return Response.seeOther(new URI("/vote/rest/content/overview")).build();
		} else {
			return Response.seeOther(new URI("/vote/index.jsp")).build();
		}
	}

	@Path("{contentId}/delete")
	@GET
	public Response delete(@PathParam("contentId") String contentId) throws URISyntaxException {
		Content content = contentStorage.get(contentId, FetchType.NONE);
		if (content != null) {
			contentService.delete(content, true);
		}
		return Response.seeOther(new URI("/vote/rest/content/overview")).build();
	}
}
