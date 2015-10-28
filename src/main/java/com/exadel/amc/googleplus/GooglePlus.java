package com.exadel.amc.googleplus;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;
import com.google.api.services.plus.model.Comment;
import com.google.api.services.plus.model.CommentFeed;
import com.google.api.services.plus.model.Moment;
import com.google.api.services.plus.model.MomentsFeed;
import com.google.api.services.plus.model.PeopleFeed;
import com.google.api.services.plus.model.Person;

public class GooglePlus {

	/**
	 * Be sure to specify the name of your application. If the application name
	 * is {@code null} or blank, the application will log a warning. Suggested
	 * format is "MyCompany-ProductName/1.0".
	 */
	private static final String APPLICATION_NAME = "AMC POC";

	/** Directory to store user credentials. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(".store/amc_poc");

	/**
	 * Global instance of the {@link DataStoreFactory}. The best practice is to
	 * make it a single globally shared instance across your application.
	 */
	private FileDataStoreFactory dataStoreFactory;

	/** Global instance of the JSON factory. */
	private JsonFactory jsonFactory;

	/** Global instance of the HTTP transport. */
	private HttpTransport httpTransport;

	/** Plus instance */
	private Plus plus;

	public GooglePlus() throws GeneralSecurityException, IOException {
		jsonFactory = JacksonFactory.getDefaultInstance();
		httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
		plus = new Plus.Builder(httpTransport, jsonFactory, authorize()).setApplicationName(APPLICATION_NAME).build();
	}

	// 100 - max value of page size
	public List<Moment> getMoments(String personId) throws IOException {
		return getMoments(personId, 100L, 1);
	}
	
	public List<Moment> getMoments(String personId, long pageSize, int maxPages) throws IOException {
		Plus.Moments.List listMoments = plus.moments().list(personId, "vault");
		listMoments.setMaxResults(pageSize);
		MomentsFeed momentFeed = listMoments.execute();
		List<Moment> moments = momentFeed.getItems();
		List<Moment> result = new ArrayList<Moment>(); 
		int pageNumber = 1;
		while (moments != null && pageNumber <= maxPages) {
			pageNumber++;
			for (Moment moment : moments) {
				result.add(moment);
			}
			if (momentFeed.getNextPageToken() == null) {
				break;
			}
			listMoments.setPageToken(momentFeed.getNextPageToken());
			momentFeed = listMoments.execute();
			moments = momentFeed.getItems();
		}
		return result;
	}
	

	// 500 max value of page size for comments
	public List<Comment> getActivityComments(String activityId) throws IOException {
		return getActivityComments(activityId, 500L, 1);
	}
	
	public List<Comment> getActivityComments(String activityId, long pageSize, int maxPages) throws IOException {
		Plus.Comments.List listComments = plus.comments().list(activityId);
		listComments.setMaxResults(pageSize);
		CommentFeed commentFeed = listComments.execute();
		List<Comment> comments = commentFeed.getItems();
		List<Comment> result = new ArrayList<Comment>(); 
		int pageNumber = 1;
		while (comments != null && pageNumber <= maxPages) {
			pageNumber++;
			for (Comment comment: comments) {
				result.add(comment);
			}
			if (commentFeed.getNextPageToken() == null) {
				break;
			}
			listComments.setPageToken(commentFeed.getNextPageToken());
			commentFeed = listComments.execute();
			comments = commentFeed.getItems();
		}    
		return result;
	}
	
	
	public Activity getActivity(String activityId) throws IOException {
		return plus.activities().get(activityId).execute();
	}
	

	// 100 - max value of page size for activities
	public List<Activity> getActivities(String personId) throws IOException {
		return getActivities(personId, 100L, 1);
	}
	
	public List<Activity> getActivities(String personId, long pageSize, int maxPages) throws IOException {
		Plus.Activities.List listActivities = plus.activities().list(personId, "public");
		listActivities.setMaxResults(pageSize);
		ActivityFeed activityFeed = listActivities.execute();
		List<Activity> activities = activityFeed.getItems();
		List<Activity>result = new ArrayList<Activity>();
		int pageNumber = 1;
		while (activities != null && pageNumber <= maxPages) {
			pageNumber++;
			for (Activity activity : activities) {
				result.add(activity);
			}
			if (activityFeed.getNextPageToken() == null) {
				break;
			}
			listActivities.setPageToken(activityFeed.getNextPageToken());
			activityFeed = listActivities.execute();
			activities = activityFeed.getItems();
		}    
		return result;
	}
	
	public Person getPerson(String personId) throws IOException {
		return plus.people().get(personId).execute();
	} 
	
	// 50 - max value of page size
	public List<Person> searchPersons(String searchString) throws IOException {
		return searchPersons(searchString, 50L, 1);
	}
	
	public List<Person> searchPersons(String searchString, long pageSize, int maxPages) throws IOException {
		Plus.People.Search searchPeople = plus.people().search(searchString);
		searchPeople.setMaxResults(pageSize);
		//searchPeople.setFields("nextPageToken,items(id,displayName,circledByCount)");
		PeopleFeed peopleFeed = searchPeople.execute();
		List<Person> people = peopleFeed.getItems();
		List<Person> result = new ArrayList<Person>();
		int pageNumber = 1;
		while (people != null && pageNumber <= maxPages) {
			pageNumber++;
			for (Person person : people) {
				result.add(person);
			}
			if (peopleFeed.getNextPageToken() == null) {
				break;
			}
			searchPeople.setPageToken(peopleFeed.getNextPageToken());
			peopleFeed = searchPeople.execute();
			people = peopleFeed.getItems();
		}
		return result;
	}

	private Credential authorize() throws IOException {
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
				new InputStreamReader(GooglePlus.class.getResourceAsStream("/keys/googleplus_keys.json")));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory,
				clientSecrets, Arrays.asList(PlusScopes.PLUS_LOGIN, PlusScopes.PLUS_ME, PlusScopes.USERINFO_EMAIL, PlusScopes.USERINFO_PROFILE))
						.setDataStoreFactory(dataStoreFactory).build();

		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}

}
