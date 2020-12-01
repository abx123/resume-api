package resume.dao;

import resume.model.Mooc;

public interface ResumeDao {

    int insertMOOC(Mooc mooc);
    String getResume();

    default int addMooc(Mooc mooc){
        return insertMOOC(mooc);
    }

    default String  readResume(){
        return getResume();
    }
    
}
