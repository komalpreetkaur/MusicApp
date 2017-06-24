package main.java.redballoon.webtest.service.lastfm;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class LastfmService {

	private final String API_KEY = "2d9ea2f7936bb229ea61208af8a6b0cb";
	private final String LAST_FM_URL = "http://ws.audioscrobbler.com/2.0/?api_key=" + API_KEY + "&format=json";
	private static LastfmService instance = null;

	private LastfmService(){};

	public static LastfmService getInstance() {
		if(instance == null) {
			instance = new LastfmService();
		}
		return instance;
	}

	/**
	 * 
	 * Call Last.fm API to fetch list of the most popular artists by country
	 * @param search
	 * @param page
	 * @param perPage
	 * @return
	 */
	public String getMostPopularArtistsByCountry(String search, String page, String perPage) {
		return getResponse(createURL("geo.gettopartists"
				+ "&country=" + search
				+ "&page=" + page
				+ "&perPage=" + perPage));
	}

	/**
	 * Call Last.fm API to fetch list of artist top tracks
	 * @param artist
	 * @return
	 */
	public String getArtistsTopTracks(String artist) {
		try {
			artist = URLEncoder.encode(artist, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return getResponse(createURL("artist.gettoptracks"
				+ "&artist=" + artist));
	}

	/**
	 * 
	 * Create api url
	 * 
	 * @param method
	 * @return
	 */
	private String createURL(String method) {
		return LAST_FM_URL + "&method="+method;
	}

	/**
	 * Get response from api
	 * 
	 * @param url
	 * @return
	 */
	private String getResponse(final String url) {
		String output = null;
		try {
			final ClientResponse response = Client.create().resource(url).accept("application/json").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}
			output = response.getEntity(String.class);
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
}
