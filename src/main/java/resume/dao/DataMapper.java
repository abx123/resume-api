package resume.dao;

import resume.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unchecked")
public class DataMapper {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Logger log = LoggerFactory.getLogger(DataMapper.class.getName());

    // public static MovieTitle mapToMovieTitle(Document document) {
    //     MovieTitle movie = new MovieTitle();
    //     movie.setId(document.getObjectId("_id").toHexString());
    //     movie.setTitle(MessageFormat.format("{0}", document.get("title")));
    //     return movie;
    // }

    public static Person mapToPerson(Bson bson) {
        Person person = new Person(null, null, null, null, null, null);
        Document document = (Document) bson;
        try {
            // person.setId(document.getObjectId("_id").toHexString());
            person.setName(document.getString("name"));
            person.setTitle(document.getString("title"));
            person.setPhoneNumber(document.getString("phoneNumber"));
            person.setAddress(document.getString("address"));
            person.setSummary(document.getString("summary"));
            person.setSkills((List<String>) document.get("skills"));
        } catch (Exception e) {
            log.warn("Unable to map document `{}` to `Person` object: {} ", document, e.getMessage());
            log.warn("Skipping document");
        }
        return person;
    }

    public static Education mapToEducation(Bson bson) {
        Education education = new Education(null, null, null, null, null, null);
        Document document = (Document) bson;
        try {
            // person.setId(document.getObjectId("_id").toHexString());
            education.setType(document.getString("type"));
            education.setInstituteName(document.getString("instituteName"));
            education.setLocation(document.getString("location"));
            education.setStartDate(document.getDate("startDate"));
            education.setEndDate(document.getDate("endDate"));
            education.setGrade(document.getString("grade"));
        } catch (Exception e) {
            log.warn("Unable to map document `{}` to `Person` object: {} ", document, e.getMessage());
            log.warn("Skipping document");
        }
        return education;
    }

    public static Certificate mapToCertificate(Bson bson) {
        Certificate certificate = new Certificate(null, null, null, null, null, null, null);
        Document document = (Document) bson;
        try {
            // person.setId(document.getObjectId("_id").toHexString());
            certificate.setName(document.getString("name"));
            certificate.setIssuedBy(document.getString("issuedBy"));
            certificate.setIssueDate(document.getDate("issueDate"));
            certificate.setExpireDate(document.getDate("expireDate"));
            certificate.setCredentialID(document.getString("credentialID"));
            certificate.setCredentialURL(new URL(document.getString("credentialURL")));
            certificate.setThumbnailURL(new URL(document.getString("thumbnailURL")));
        } catch (Exception e) {
            log.warn("Unable to map document `{}` to `Person` object: {} ", document, e.getMessage());
            log.warn("Skipping document");
        }
        return certificate;
    }

    public static Mooc mapToMooc(Bson bson) {
        Mooc mooc = new Mooc(null, null, null, null, null, null);
        Document document = (Document) bson;
        try {
            // person.setId(document.getObjectId("_id").toHexString());
            mooc.setName(document.getString("name"));
            mooc.setIssuedBy(document.getString("issuedBy"));
            mooc.setIssueDate(document.getDate("issueDate"));
            mooc.setCredentialID(document.getString("credentialID"));
            mooc.setCredentialURL(new URL(document.getString("credentialURL")));
            mooc.setThumbnailURL(new URL(document.getString("thumbnailURL")));
        } catch (Exception e) {
            log.warn("Unable to map document `{}` to `Mooc` object: {} ", document, e.getMessage());
            log.warn("Skipping document");
        }
        return mooc;
    }

    public static Experience mapToExperience(Bson bson) {

        Experience experience = new Experience(null, null, null, null, null, null);
        Document document = (Document) bson;
        try {
            // experience.setId(document.getObjectId("_id").toHexString());
            // experience.setPersonID(document.getObjectId("personID").toHexString());

            experience.setTitle(document.getString("title"));
            experience.setCompanyName(document.getString("companyName"));
            experience.setLocation(document.getString("location"));
            experience.setStartDate(document.getDate("startDate"));
            experience.setEndDate(document.getDate("endDate"));
            experience.setDescription((List<String>) document.get("descriptions"));
        } catch (Exception e) {
            log.warn("Unable to map document `{}` to `Certificate` object: {} ", document, e.getMessage());
            log.warn("Skipping document");
        }
        return experience;
    }

    // private static Comment parseComment(Document document) {
    //     Comment comment = new Comment();
    //     comment.setId(document.getObjectId("_id").toHexString());
    //     comment.setText(document.getString("text"));
    //     comment.setEmail(document.getString("email"));
    //     comment.setDate(document.getDate("date"));
    //     comment.setMovieId(document.getObjectId("movie_id").toHexString());
    //     comment.setName(document.getString("name"));
    //     return comment;
    // }

    // private static Date parseDate(Object stringDate) {
    //     if (stringDate == null) {
    //         return null;
    //     }
    //     try {
    //         if (stringDate instanceof String) {
    //             return sdf.parse((String) stringDate);
    //         }
    //         if (stringDate instanceof Date) {
    //             return (Date) stringDate;
    //         }
    //     } catch (ParseException ex) {
    //         log.error("Error parsing `{}` string into Date object: {}", stringDate, ex.getMessage());
    //     }
    //     return null;
    // }

    // private static RottenTomatoes mapToRottenTomatoes(Document document) {
    //     RottenTomatoes tomatoes = new RottenTomatoes();
    //     if (document == null) {
    //         return tomatoes;
    //     }
    //     tomatoes.setLastUpdated(parseDate(document.getString("lastupdated")));
    //     if (document.containsKey("viewer")) {
    //         tomatoes.setViewer(mapToViewerRating((Document) document.get("viewer")));
    //     }

    //     return tomatoes;
    // }

    // private static ViewerRating mapToViewerRating(Document document) {
    //     ViewerRating viewer = new ViewerRating();
    //     if (document == null) {
    //         return viewer;
    //     }
    //     viewer.setNumReviews(document.getInteger("numReviews"));
    //     viewer.setRating(document.getDouble("rating"));

    //     return viewer;
    // }

    // private static IMDB mapToIMDB(Document document) {
    //     IMDB imdb = new IMDB();
    //     if (document == null) {
    //         return imdb;
    //     }
    //     imdb.setId(document.getInteger("id"));

    //     imdb.setRating(parseDouble(document.get("rating")));
    //     imdb.setVotes(parseInt(document.get("votes")));
    //     return imdb;
    // }

    public static Integer parseInt(Object o) {
        if (o instanceof String) {
            if ("".equals(o)) {
                return 0;
            }
            return Integer.valueOf((String) o);
        }
        return ((Number) o).intValue();
    }

    private static Double parseDouble(Object rating) {
        if (rating instanceof String) {
            if ("".equals(rating)) {
                return (double) 0;
            }
            return Double.parseDouble((String) rating);
        }
        return ((Number) rating).doubleValue();
    }
}
