package dambi.accessingrestmongoprogramazioliburuak.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBProgramazioLiburuaRepository implements ProgramazioLiburuaRepository {
    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();

    @Autowired
    private MongoClient client;
    private MongoCollection<ProgramazioLiburua> programazioLiburuaCollection;

    @PostConstruct
    void init() {
        programazioLiburuaCollection = client.getDatabase("berreskurapenProiektua").getCollection("programazioLiburuak",
                ProgramazioLiburua.class);
    }

    // Funtzio hau liburu guztiak datu basetik eskuratzen ditu
    @Override
    public List<ProgramazioLiburua> programazioLiburuGuztiak() {
        return programazioLiburuaCollection.find().into(new ArrayList<>());
    }

    // Liburua izenaren arabera bilatu eta itzultzen du.
    @Override
    public ProgramazioLiburua liburuaByTitle(String title) {
        return programazioLiburuaCollection.find(eq("title", title)).first();
    }

    // Emandako autorearen izenaren arabera liburu guztiak bilatu eta itzultzen
    // ditu.
    @Override
    public List<ProgramazioLiburua> autoreenLiburuak(String autorearenIzena) {

        return programazioLiburuaCollection.find(new Document("authors", autorearenIzena)).into(new ArrayList<>());

    }

    // Funtzio hau liburu bat datu basera gehitzen du.
    @Override
    public ProgramazioLiburua liburuBerria(ProgramazioLiburua programazioLiburua) {

        programazioLiburuaCollection.insertOne(programazioLiburua);
        return programazioLiburua;
    }

    // Liburu baten estatusa datu basetik eguneratzen du.
    @Override
    public ProgramazioLiburua liburuarenEstatusa(ProgramazioLiburua programazioLiburua) {
        Bson filter = new Document("title", programazioLiburua.getTitle());
        Bson update = new Document("$set", new Document("status", programazioLiburua.getStatus()));

        programazioLiburuaCollection.updateOne(filter, update);
        return programazioLiburua;
    }

    // liburua izenaren arabera datu basetik ezabatzen du.
    @Override
    public String deleteByTitle(String title) {
        if (programazioLiburuaCollection.find(eq("title", title)).first() != null) {
            programazioLiburuaCollection.deleteMany(eq("title", title));
            return title + " daukan liburua datu-basetik ezabatua izan da";
        } else {
            return title + " liburua ez da aurkitu! Agian sartu duzun izena ez da egokia";
        }
    }

    // Emandako orrialde kopurua baino gehiagoko orrialdeak dituzten liburuak
    // bilatzen ditu, handienetik txikienera antolatuta.
    @Override
    public List<ProgramazioLiburua> liburuLuzeena(int orrialdeKopurua) {
        // Iragazi emandako kopurua baino orrialde gehiago dituzten liburuak
        Bson filter = Filters.gt("pageCount", orrialdeKopurua);

        // Ordenatu liburuak orriaren kopuruaren arabera
        Bson sort = Sorts.descending("pageCount");

        // Lortu iragazkiak betetzen dituzten liburuei dagozkien dokumentuak
        List<ProgramazioLiburua> programazioLiburuak = new ArrayList<>();
        for (ProgramazioLiburua liburua : programazioLiburuaCollection.find(filter).sort(sort)) {
            ProgramazioLiburua programazioLiburua = new ProgramazioLiburua();
            programazioLiburua.setTitle(liburua.getTitle());
            programazioLiburua.setPageCount(liburua.getPageCount());
            programazioLiburuak.add(programazioLiburua);
        }

        return programazioLiburuak;
    }

    // Emandako data baino lehen bistaratu diren liburuak datu basetik bilatzen
    // ditu.
    public List<ProgramazioLiburua> urteBaterarte(Date urtea) {
        Bson filter = Filters.lte("publishedDate", urtea);
        Bson sort = Sorts.descending("publishedDate");
        FindIterable<ProgramazioLiburua> result = programazioLiburuaCollection.find(filter).sort(sort);

        List<ProgramazioLiburua> programazioLiburuak = new ArrayList<>();
        for (ProgramazioLiburua liburua : result) {
            programazioLiburuak.add(liburua);
        }
        return programazioLiburuak;
    }

    // Liburu baten puntuazioa datu basetik eguneratzen du.
    @Override
    public ProgramazioLiburua puntuazioaGorde(ProgramazioLiburua programazioLiburua) {
        Bson filter = new Document("title", programazioLiburua.getTitle());
        Bson update = new Document("$set", new Document("puntuazioa", programazioLiburua.getPuntuazioa()));

        programazioLiburuaCollection.updateOne(filter, update);
        return programazioLiburua;
    }

    // Kategoria baten arabera liburu guztiak bilatzen ditu.
    public List<ProgramazioLiburua> findByCategory(String categoria) {
        return programazioLiburuaCollection.find(new Document("categories", categoria)).into(new ArrayList<>());
    }

}
