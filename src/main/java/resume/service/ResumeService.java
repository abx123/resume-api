package resume.service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import resume.dao.ResumeDao;
import resume.model.Mooc;
import resume.model.Certificate;

@Service
public class ResumeService {

    private final ResumeDao resumeDao;

    @Autowired
    public ResumeService(@Qualifier("fake") ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    public Mooc addMOOC(Mooc mooc) {
        return resumeDao.addMooc(mooc);
    }

    public String getResume() {
        return resumeDao.fetchResume();
    }

    public List<Certificate> getCerts() {
        return resumeDao.getCerts();
    }

    public Optional<Certificate> getCert(UUID ID) {
        return resumeDao.getCert(ID);
    }

    public int upsertCert(Certificate cert) {
        if (cert.getId() == null) {
            return resumeDao.insertCert(cert);
        }
        return resumeDao.updateCert(cert);
    }

    public int deleteCert(UUID ID){
        return resumeDao.deleteCert(ID);
    }
}
