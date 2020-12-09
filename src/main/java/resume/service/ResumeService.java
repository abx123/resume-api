package resume.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

// import java.util.List;
// import java.util.UUID;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import resume.dao.*;
import resume.model.*;

@Service
public class ResumeService {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ExperiencesDao experiencesDao;
    @Autowired
    private EducationsDao educationsDao;
    @Autowired
    private CertificatesDao certificatesDao;
    @Autowired
    private MoocsDao moocsDao;

    public ResumeService() {
        super();
    }

    public Resume getResume(String id) {
        Person person = DataMapper.mapToPerson(personDao.getPerson(id));
        List<Experience> experiences = experiencesDao.getExperiences(id).stream().map(DataMapper::mapToExperience)
                .collect(Collectors.toList());
        List<Education> educations = educationsDao.getEducations(id).stream().map(DataMapper::mapToEducation)
                .collect(Collectors.toList());
        List<Certificate> certifcations = certificatesDao.getCertifications(id).stream()
                .map(DataMapper::mapToCertificate).collect(Collectors.toList());
        List<Mooc> moocs = moocsDao.getMoocs(id).stream().map(DataMapper::mapToMooc).collect(Collectors.toList());

        return new Resume(person, experiences, educations, certifcations, moocs);
    }

    public List<Person> getPersons() {
        List<Person> persons = personDao.getPersons().stream().map(DataMapper::mapToPerson)
                .collect(Collectors.toList());
        return persons;
    }

    public Person getPerson(String id) {
        return DataMapper.mapToPerson(personDao.getPerson(id));
    }

    public boolean deletePerson(String personID, Map<String, Object> results) {
        if (!personDao.deletePerson(personID)) {
            results.put("error", "Error deleting person");
            return false;
        }
        return true;
    }

    public boolean upsertPerson(Person person, Map<String, Object> results) {
        if (!personDao.upsertPerson(person)) {
            results.put("error", "Error upserting person");
            return false;
        }
        return true;
    }

    public Experience getExperience(String expID) {
        return DataMapper.mapToExperience(experiencesDao.getExperience(expID));
    }

    public List<Experience> getExperiences(String personID) {
        List<Experience> exps = experiencesDao.getExperiences(personID).stream().map(DataMapper::mapToExperience)
                .collect(Collectors.toList());
        return exps;
    }

    public boolean deleteExperience(String expID, Map<String, Object> results) {
        if (!experiencesDao.deleteExperience(expID)) {
            results.put("error", "Error deleting experience");
            return false;
        }
        return true;
    }

    public boolean upsertExperience(Experience exp, Map<String, Object> results) {
        if (!experiencesDao.upsertExperience(exp)) {
            results.put("error", "Error upserting experience");
            return false;
        }
        return true;
    }

    public List<Education> getEducations(String personID) {
        List<Education> educations = educationsDao.getEducations(personID).stream().map(DataMapper::mapToEducation)
                .collect(Collectors.toList());
        return educations;
    }

    public Education getEducation(String eduID) {
        return DataMapper.mapToEducation(educationsDao.getEducation(eduID));
    }

    public boolean deleteEducation(String eduID, Map<String, Object> results) {
        if (!educationsDao.deleteEducation(eduID)) {
            results.put("error", "Error deleting education");
            return false;
        }
        return true;
    }

    public boolean upsertEducation(Education edu, Map<String, Object> results) {
        if (!educationsDao.upsertEducation(edu)) {
            results.put("error", "Error upserting education");
            return false;
        }
        return true;
    }

    public List<Certificate> getCertifications(String personID) {
        List<Certificate> certifcations = certificatesDao.getCertifications(personID).stream()
                .map(DataMapper::mapToCertificate).collect(Collectors.toList());
        return certifcations;
    }

    public Certificate getCertificate(String certID) {
        return DataMapper.mapToCertificate(certificatesDao.getCertificate(certID));
    }

    public boolean deleteCertificate(String certID, Map<String, Object> results) {
        if (!certificatesDao.deleteCertificate(certID)) {
            results.put("error", "Error deleting certificate");
            return false;
        }
        return true;
    }

    public boolean upsertCertificate(Certificate cert, Map<String, Object> results) {
        if (!certificatesDao.upsertCertificate(cert)) {
            results.put("error", "Error upserting certificate");
            return false;
        }
        return true;
    }

    public List<Mooc> getMoocs(String personID) {
        List<Mooc> moocs = moocsDao.getMoocs(personID).stream().map(DataMapper::mapToMooc).collect(Collectors.toList());
        return moocs;
    }

    public Mooc getMooc(String moocID) {
        return DataMapper.mapToMooc(moocsDao.getMooc(moocID));
    }

    public boolean deleteMooc(String moocID, Map<String, Object> results) {
        if (!moocsDao.deleteMooc(moocID)) {
            results.put("error", "Error deleting mooc");
            return false;
        }
        return true;
    }

    public boolean upsertMooc(Mooc mooc, Map<String, Object> results) {
        if (!moocsDao.upsertMooc(mooc)) {
            results.put("error", "Error upserting mooc");
            return false;
        }
        return true;
    }


}
