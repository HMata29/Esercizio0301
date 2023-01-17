package it.corso.esercizio0301.business.interfaces;

import it.corso.esercizio0301.model.Corso;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CorsoServiceInterface {
    public Corso getById (Integer id);

    Corso insert(Corso c);

    Corso update(Integer id, Corso request);

    void delete (Integer id);

    void creaCorsoConUtente(Integer id, Corso c);

    List<Corso> getAll();

    void  uploadFile(Integer id, MultipartFile course) throws IOException;



}
