package resume.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import resume.model.Mooc;
import resume.model.Certificate;

@Repository("fake")
public class ResumeDataAccessService implements ResumeDao {

    private static List<Mooc> mList = new ArrayList<>();
    private static List<Certificate> cList = new ArrayList<>();

    @Override
    public Mooc addMooc(Mooc mooc) {
        mList.add(new Mooc(mooc.getName(), mooc.getIssuedBy(), mooc.getissueDate(), mooc.getCredentialID(),
                mooc.gerCredentialURL()));
        return mooc;
    };

    @Override
    public String fetchResume() {
        return "fake string 22";
    }

    @Override
    public List<Certificate> listCertificates() {
        return cList;
    }

    @Override
    public Optional<Certificate> fetchCertificate(UUID ID) {
        return cList.stream().filter(cert -> cert.getId().equals(ID)).findFirst();
    }

    @Override
    public int addCertificate(Certificate cert) {
        cList.add(new Certificate(UUID.randomUUID(), cert.getName(), cert.getIssuedBy(), cert.getIssueDate(),
                cert.getExpireDate(), cert.getCredentialID(), cert.gerCredentialURL()));
        return 99;
    }

    @Override
    public int updateCertificate(Certificate cert) {
        System.out.println("update");
        return fetchCertificate(cert.getId()).map(c -> {
            int index = cList.indexOf(c);
            System.out.println("testing");
            System.out.println(index);
            if (index >=0){
                cList.set(index, cert);
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public int deleteCertificate(UUID ID){
        Optional<Certificate> certMaybe = fetchCertificate(ID);
        if (certMaybe.isEmpty()){
            return 0;
        }
        cList.remove(certMaybe.get());
        return 1;
    }

}
