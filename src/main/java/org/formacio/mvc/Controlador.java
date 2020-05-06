package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controlador {

	@Autowired
	AgendaService agenda;

	@RequestMapping(path="/nombre")
	@ResponseBody
	public int nombre_contactes() {
		return agenda.nombreContactes();
		
	}
	
	@RequestMapping(path="/telefon")
	@ResponseBody
	public String tel(@RequestParam String id) {
		return agenda.recupera(id).getTelefon();
	}
	
	@RequestMapping(path="/contacte/{id}")
	@ResponseBody
	public Persona contactes(@PathVariable String id) {
		if(agenda.recupera(id) == null){
			throw new OperationException();
		} 	
		else
			return agenda.recupera(id);	
	}
	
	@RequestMapping(path="/afegir", method= RequestMethod.POST)
	@ResponseBody
	public void nou_contacte(@RequestParam String id, String nom, String telefon) {
		agenda.inserta(id, nom, telefon);;
	}
	
}

