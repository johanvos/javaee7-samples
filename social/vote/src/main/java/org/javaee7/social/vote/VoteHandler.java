package org.javaee7.social.vote;

import com.lodgon.dali.core.entity.Content;
import com.lodgon.dali.core.entity.User;
import com.lodgon.dali.core.service.ContentService;
import com.lodgon.dali.core.service.UserService;
import com.lodgon.dali.core.storage.ContentStorageService;
import com.lodgon.dali.core.storage.FetchType;
import java.net.URI;
import java.net.URISyntaxException;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author joeri
 */
@ManagedBean
@Path("vote")
public class VoteHandler {

	@Inject
	ContentService contentService;
	@Inject
	ContentStorageService contentStorage;
	@Inject
	UserService userService;

	@Path("content/{contentId}")
	@GET
	public Response voteContent(@PathParam("contentId") String contentId) throws URISyntaxException {
		Content content = contentStorage.get(contentId, FetchType.NONE);
		if (content != null) {
			content.setScore(content.getScore() + 1);
			contentService.update(content);

			User author = content.getAuthor();
			author.setScore(author.getScore() + 1);
			userService.update(author);
		}

		return Response.seeOther(new URI("/vote/rest/content/overview")).build();
	}

}
