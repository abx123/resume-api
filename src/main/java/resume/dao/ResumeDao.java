package resume.dao;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import resume.model.Mooc;
import resume.model.Certificate;

public interface ResumeDao {

    Mooc addMooc(Mooc mooc);

    String fetchResume();

    List<Certificate> listCertificates();
    Optional<Certificate> fetchCertificate(UUID ID);
    int addCertificate(Certificate cert);
    int updateCertificate(Certificate cert);
    int deleteCertificate(UUID ID);

    default Mooc insertMooc(Mooc mooc) {
        return addMooc(mooc);
    }

    default String getResume() {
        return fetchResume();
    }

    default List<Certificate> getCerts() {
        return listCertificates();
    }

    default Optional<Certificate> getCert(UUID ID) {
        return fetchCertificate(ID);
    }

    default int insertCert(Certificate cert) {
        return addCertificate(cert);
    }

    default int updateCert(Certificate cert) {
        return updateCertificate(cert);
    }

    default int deleteCert(UUID ID){
        return deleteCertificate(ID);
    }

}
