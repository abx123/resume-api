package resume.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import resume.model.Mooc;

@Repository("fake")
public class ResumeDataAccessService implements ResumeDao {

    private static List<Mooc> DB = new ArrayList<>();
    @Override
    public int insertMOOC(Mooc mooc) {
        DB.add(new Mooc(mooc.getName(), mooc.getIssuedBy(), mooc.getissueDate(), mooc.getCredentialID(),
                mooc.gerCredentialURL()));
        return 0;
    };

    @Override
    public String getResume(){
        return "fake string 22";
    }
}
