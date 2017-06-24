package main.java.redballoon.webtest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.redballoon.webtest.service.lastfm.LastfmService;

@Path("/artists")
public class ArtistsService {

	/**
	 * 
	 * Fetch list of the popular tracks of selected artist
	 * @param artist
	 * @return
	 */
	@GET
	@Path("/artist")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getArtist(@QueryParam("artist") String artist){
		return Response.status(200)
				.entity(LastfmService.getInstance()
						.getArtistsTopTracks(artist))	
				.build();
	}

	/**
	 * 
	 *  Fetch list of the most popular artists by country
	 * 
	 * @param search
	 * @param page
	 * @param perPage
	 * @return
	 */
	@GET
	@Path("/search")
	public Response getSearch(
			@QueryParam("search") String search,
			@QueryParam("page") String page,
			@QueryParam("perPage") String perPage) {
		return Response.status(200)
				.entity(LastfmService.getInstance()
						.getMostPopularArtistsByCountry(search, page, perPage))
				.build();
	}
}