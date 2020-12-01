package resume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import resume.dao.ResumeDao;
import resume.model.Mooc;

@Service
public class ResumeService {

    private final ResumeDao resumeDao;

    @Autowired
    public ResumeService(@Qualifier("fake") ResumeDao resumeDao){
        this.resumeDao = resumeDao;
    }

    public int addMOOC(Mooc mooc){
        return resumeDao.insertMOOC(mooc);
    }

    public String getResume(){
        return resumeDao.readResume();
    }
}
