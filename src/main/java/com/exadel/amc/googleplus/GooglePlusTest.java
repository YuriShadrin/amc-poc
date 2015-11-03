package com.exadel.amc.googleplus;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.Comment;
import com.google.api.services.plus.model.Person;

/**
 * Create Google account Go to https://console.developers.google.com/ Create new
 * project (click "Enable and manage APIs" link) or choose the existing Go to
 * "APIS & auth -> APIs" Look for "Google+ API" link, click it Click
 * "Enable API" button Look for "Google+ Domains API" link, click it Click
 * "Enable API" button Go to "APIS & auth -> Credentials" Add credentials -> API
 * key -> Server key Click "Create" button, get API key from opened popup window
 */
public class GooglePlusTest {

    private static final String SEARCH_STRING = "Exadel, Inc.";
    private static Logger log = LoggerFactory.getLogger(GooglePlusTest.class);

    public static void main(String[] args) throws Exception {
        GooglePlus gp = new GooglePlus();

        String searches[];
        if (args.length == 0) {
            searches = new String[] { SEARCH_STRING };
        } else {
            searches = args;
        }

        for (String search : searches) {

            log.info(">>> " + search + " <<<");

            List<Person> lp = gp.searchPersons(search, 5, 1);
            log.info("--- Persons:");
            for (Person p : lp) {
                // Only following fields are allowed in search: displayName,
                // etag, id, image, kind, objectType, url
                log.info(p.getId() + " " + p.getDisplayName());
            }

            if (lp.size() > 0) {
                log.info("--- Person '" + lp.get(0).getDisplayName() + "':");
                Person p = gp.getPerson(lp.get(0).getId());
                printPerson(p);
            }

            if (lp.size() > 0) {
                log.info("--- Activities:");
                List<Activity> la = gp.getActivities(lp.get(0).getId());
                for (Activity a : la) {
                    log.info("{}", a);

                    List<Comment> lc = gp.getActivityComments(a.getId());
                    if (lc.size() > 0) {
                        log.info("    Activity {} comments: {}", a.getId(), lc);
                    }
                }
            }

            log.info("");
        }
    }

    private static void printPerson(Person p) {
        log.info("\tid: " + p.getId());
        log.info("\tdisplay name: " + p.getDisplayName());
        log.info("\tname: " + p.getName());
        log.info("\tnick name: " + p.getNickname());

        log.info("\tcircled by count: " + p.getCircledByCount());
        log.info("\tplus one count: " + p.getPlusOneCount());

        log.info("\tabout me: " + p.getAboutMe());
        log.info("\tage range: " + p.getAgeRange());
        log.info("\tbirthday: " + p.getBirthday());
        log.info("\tbragging rights: " + p.getBraggingRights());
        log.info("\tcover: " + p.getCover());
        log.info("\tcurrent location: " + p.getCurrentLocation());
        log.info("\tdomain: " + p.getDomain());
        log.info("\temails: " + p.getEmails());
        log.info("\tetag: " + p.getEtag());
        log.info("\tgender: " + p.getGender());
        log.info("\tgoogle+ url: " + p.getUrl());
        log.info("\timage: " + p.getImage());
        log.info("\tis plus user: " + p.getIsPlusUser());
        log.info("\tkind: " + p.getKind());
        log.info("\tlanguage: " + p.getLanguage());
        log.info("\tobject type: " + p.getObjectType());
        log.info("\toccupation: " + p.getOccupation());
        log.info("\torganizations: " + p.getOrganizations());
        log.info("\tplaces lived: " + p.getPlacesLived());
        log.info("\trelationship status: " + p.getRelationshipStatus());
        log.info("\tskills: " + p.getSkills());
        log.info("\ttagline: " + p.getTagline());
        log.info("\turls: " + p.getUrls());
    }

}
