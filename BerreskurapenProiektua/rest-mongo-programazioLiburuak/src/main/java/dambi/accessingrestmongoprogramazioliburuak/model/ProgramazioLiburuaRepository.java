package dambi.accessingrestmongoprogramazioliburuak.model;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProgramazioLiburuaRepository {
    List<ProgramazioLiburua> programazioLiburuGuztiak();

    ProgramazioLiburua liburuaByTitle(String title);

    ProgramazioLiburua liburuBerria(ProgramazioLiburua programazioLiburua);

    ProgramazioLiburua liburuarenEstatusa(ProgramazioLiburua programazioLiburua);

    ProgramazioLiburua puntuazioaGorde(ProgramazioLiburua programazioLiburua);

    String deleteByTitle(String title);

    List<ProgramazioLiburua> liburuLuzeena(int orrialdeKopurua);

    List<ProgramazioLiburua> autoreenLiburuak(String autorearenIzena);

    List<ProgramazioLiburua> urteBaterarte(Date urtea);

    List<ProgramazioLiburua> findByCategory(String categoria);

}
