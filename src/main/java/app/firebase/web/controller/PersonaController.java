package app.firebase.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.firebase.web.model.Persona;
import app.firebase.web.model.PersonaDTO;
import app.firebase.web.service.PersonaServiceAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/persona")
@CrossOrigin("*")
public class PersonaController {

    @Autowired
    private PersonaServiceAPI PersonaServiceAPI;

    @PostMapping(value = "/save/{id}")
	public ResponseEntity<String> save(@RequestBody Persona Persona, @PathVariable String id) throws Exception {
		if (id == null || id.length() == 0 || id.equals("null")) {
			id = PersonaServiceAPI.save(Persona);
		} else {
			PersonaServiceAPI.save(Persona, id);
		}
		return new ResponseEntity<String>(id, HttpStatus.OK);
	}

	@GetMapping(value = "/findall")
	public List<PersonaDTO> getAll() throws Exception {
		return PersonaServiceAPI.getAll();
	}

	@GetMapping(value = "/find/{id}")
	public PersonaDTO find(@PathVariable String id) throws Exception {
		List<PersonaDTO> Personas = PersonaServiceAPI.getAll();
		for (PersonaDTO Persona : Personas) {
			if (Persona.getId().equals(id)) {
				System.out.println(Persona.toString());
				return Persona;
			}
		}
		return null;
	}

	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<PersonaDTO> delete(@PathVariable String id) throws Exception {
		PersonaDTO Persona = PersonaServiceAPI.get(id);
		if (Persona != null) {
			PersonaServiceAPI.delete(id);
		} else {
			return new ResponseEntity<PersonaDTO>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<PersonaDTO>(Persona, HttpStatus.OK);
	}
	
}