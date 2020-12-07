package resume.service;

import java.util.ArrayList;
import java.util.List;
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
        List<Mooc> moocs = moocsDao.getMoocs(id).stream().map(DataMapper::mapToMooc)
                .collect(Collectors.toList());

        return new Resume(person, experiences, educations, certifcations, moocs);
        // return new Resume(person, null, null, null, null);
    }

    public Person getPerson(String id) {
        return DataMapper.mapToPerson(personDao.getPerson(id));
    }

    public List<Experience> getExperiences(String personID) {
        List<Experience> experiences = experiencesDao.getExperiences(personID).stream().map(DataMapper::mapToExperience)
                .collect(Collectors.toList());
        return experiences;
    }

    public List<Education> getEducations(String personID) {
        List<Education> educations = educationsDao.getEducations(personID).stream().map(DataMapper::mapToEducation)
                .collect(Collectors.toList());
        return educations;
    }

    public List<Certificate> getCertifications(String personID) {
        List<Certificate> certifcations = certificatesDao.getCertifications(personID).stream()
                .map(DataMapper::mapToCertificate).collect(Collectors.toList());
        return certifcations;
    }

    public List<Mooc> getMoocs(String personID) {
        List<Mooc> moocs = moocsDao.getMoocs(personID).stream().map(DataMapper::mapToMooc).collect(Collectors.toList());
        return moocs;
    }
}
