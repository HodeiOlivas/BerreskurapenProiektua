package dambi.accessingrestmongoprogramazioliburuak.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.text.Document;
import javax.validation.Valid;

import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import dambi.accessingrestmongoprogramazioliburuak.model.ProgramazioLiburua;
import dambi.accessingrestmongoprogramazioliburuak.model.ProgramazioLiburuaRepository;
import dambi.accessingrestmongoprogramazioliburuak.model.Puntuazioa;

@RestController // This means that this class is a Controller baina @Controller bakarrik
				// jarrita, PUT eta DELETEak ez dabiz
@RequestMapping(path = "/programazioLiburuak") // This means URL's start with /demo (after Application path)
public class MainController {

	@Autowired
	private ProgramazioLiburuaRepository programazioLiburuaRepository;

	@PutMapping("/puntuazioa/{title}")
	public ResponseEntity<ProgramazioLiburua> GehituPuntuazioa(@PathVariable("title") String title,
			@RequestBody Puntuazioa puntuazioa) {
		ProgramazioLiburua programazioLiburua = programazioLiburuaRepository.liburuaByTitle(title);

		if (programazioLiburua == null) {
			return ResponseEntity.notFound().build();
		}

		List<Puntuazioa> puntuazioaList = programazioLiburua.getPuntuazioa();
		if (puntuazioaList == null) {
			puntuazioaList = new ArrayList<>();
		}
		puntuazioaList.add(puntuazioa);
		programazioLiburua.setPuntuazioa(puntuazioaList);

		ProgramazioLiburua updatedProgramazioLiburua = programazioLiburuaRepository.puntuazioaGorde(programazioLiburua);

		return ResponseEntity.ok(updatedProgramazioLiburua);
	}

	@PutMapping(value = "/liburuarenEstatusa/{title}")
	public String updateLiburua(@Valid @RequestBody String status,
			@PathVariable String title) {
		try {
			ProgramazioLiburua liburua = programazioLiburuaRepository.liburuaByTitle(title);

			if (liburua == null) {
				return "Ez da liburua aurkitu";
			}

			if (liburua.getStatus().equals("PUBLISH")) {
				return "El libro ya está publicado";
			}

			if (liburua.getStatus().equals("MEAP")) {

				liburua.setStatus("PUBLISH");

			}

			// liburua.setStatus(status);
			programazioLiburuaRepository.liburuarenEstatusa(liburua);

			return "Saved";

		} catch (Exception ex) {
			return "Error: Arazo bat egon da liburuaren estatusa aldatu nahi duzunean";
		}
	}

	@PostMapping(path = "/liburuBerria")
	public @ResponseBody String addLiburuBerria(@RequestParam String title, @RequestParam String isbn,
			@RequestParam int pageCount, @RequestParam Date publishedDate, @RequestParam String thumbnailUrl,
			@RequestParam String shortDescription, @RequestParam String status, @RequestParam List<String> authors,
			@RequestParam List<String> categories /* @RequestParam List<Puntuazioa> puntuazioakList */) {

		if (isbn.length() > 0 && isbn.length() < 10) {

			return "Error: isbn 10 digito izan behar ditu";

		}

		if (!status.equals("PUBLISH") && !status.equals("MEAP")) {
			return "Error: Estatusaren balorea 'PUBLISH' edo 'MEAP' izan behar da";
		}

		String[] SDescription = shortDescription.split(" ");
		if (SDescription.length > 60) {

			return "Error: 'shortDescription' ezin ditu izan 60 hitz baino gehiago ";
		}

		ProgramazioLiburua liburua = new ProgramazioLiburua();
		liburua.setTitle(title);
		liburua.setIsbn(isbn);
		liburua.setPageCount(pageCount);
		liburua.setPublishedDate(publishedDate);
		liburua.setThumbnailUrl(thumbnailUrl);
		liburua.setShortDescription(shortDescription);
		liburua.setStatus(status);
		liburua.setAuthors(authors);
		liburua.setCategories(categories);
		// liburua.setPuntuazioa(puntuazioakList);
		programazioLiburuaRepository.liburuBerria(liburua);
		return "Saved";
	}

	@GetMapping("/publicatiedate/{year}")
	public List<ProgramazioLiburua> getLiburuakByPublicatiedate(Date year) {
		List<ProgramazioLiburua> liburuak = programazioLiburuaRepository.urteBaterarte(year);
		return liburuak;
	}

	@GetMapping("/liburuak/Kategoria/{categories}")
	public List<ProgramazioLiburua> getliburuakByCategory(String categoria) {
		return programazioLiburuaRepository.findByCategory(categoria);
	}

	@GetMapping(path = "/liburuaByTitle/{title}")
	public @ResponseBody ProgramazioLiburua getLiburuaByTitle(@PathVariable String title) {
		ProgramazioLiburua liburua = programazioLiburuaRepository.liburuaByTitle(title);
		if (liburua == null) {

			throw new NotFoundException("Liburua ez da aurkitu! Agian sartu duzun izena ez da egokia");
		}
		return liburua;
	}

	@GetMapping(path = "/liburuTamaña/{orrialdeKopurua}")
	public List<ProgramazioLiburua> getLiburuLuzeena(int orrialdeKopurua) {
		return programazioLiburuaRepository.liburuLuzeena(orrialdeKopurua);
	}

	@GetMapping(path = "/liburuGuztiak")
	public @ResponseBody Iterable<ProgramazioLiburua> getLiburuGuztiak() {

		return programazioLiburuaRepository.programazioLiburuGuztiak();
	}

	@GetMapping(path = "/AutoreenLiburuak/{authors}")
	public List<ProgramazioLiburua> getLiburuakByAuthor(String autorearenIzena) {
		return programazioLiburuaRepository.autoreenLiburuak(autorearenIzena);
	}

	@DeleteMapping(path = "/liburuaEzabatu/{title}")
	public String deleteLiburuaByTitle(String title) {
		try {
			return programazioLiburuaRepository.deleteByTitle(title);

		} catch (Exception ex) {
			System.out.println("Errorea " + title + " liburua ezabatzerakoan. ");
			return "Errorea egon da";

		}
	}

	@ControllerAdvice
	public class GlobalExceptionHandler {

		@ExceptionHandler(value = NotFoundException.class)
		public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
