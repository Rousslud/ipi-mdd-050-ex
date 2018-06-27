package com.ipiecoles.java.mdd050.controller;

import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.mdd050.exception.ConflictException;
import com.ipiecoles.java.mdd050.exception.EmployeException;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.service.EmployeService;

@RestController
@RequestMapping("/employes")
public class EmployeController {
	
	public static final String APPLICATION_JSON_CHARSET_UTF_8= "application/json;charset=UTF-8";
	
	@Autowired
	private EmployeService employeService;
	
	
	@GetMapping("/count")
	public Long countAllEmployes(){
		return employeService.countAllEmploye();
	}
	
	@RequestMapping(
			value="/{id}",
			produces = APPLICATION_JSON_CHARSET_UTF_8,
			method=RequestMethod.GET)
		public Employe detailEmploye(@PathVariable(value="id") Long id) {
		Employe result = employeService.findById(id);
		if (result == null ) {
			throw new EntityNotFoundException("L'employé d'identifiant : " + 
					  id + " n'a pas été trouvé.");
		}
		return result;
	}
	
	 @RequestMapping (
			 value = "",
			 method = RequestMethod.GET,
			 produces = APPLICATION_JSON_CHARSET_UTF_8, 
			 params="matricule"
			 )	 
	 	public Employe rechercheMatricule(@RequestParam("matricule") String matricule) {
		Employe employe = employeService.findMyMatricule(matricule);	
		 if (employe == null ) {
				throw new EntityNotFoundException("L'employé de matricule : " + 
						matricule + " n'a pas été trouvé.");
			}
		 return employe;
		}
	 
	 @RequestMapping (
			 value = "",
			 method = RequestMethod.GET,
			 produces = APPLICATION_JSON_CHARSET_UTF_8
			 )	 
	 	public Page<Employe> afficheListeEmployes(
			 @RequestParam("page") Integer page,
			 @RequestParam("size") Integer size,
			 @RequestParam("sortProperty") String sortProperty,
			 @RequestParam("sortDirection") String sortDirection) {
		 Page<Employe> pagin = employeService.findAllEmployes(page, size, sortProperty, sortDirection);
		 return pagin;
		 }
	 
	 @RequestMapping (
			 value = "",
			 method = RequestMethod.POST,
			 consumes = APPLICATION_JSON_CHARSET_UTF_8,
			 produces = APPLICATION_JSON_CHARSET_UTF_8
			 )	
		public Employe creerEmploye(@RequestBody Employe employe) throws ConflictException {
			try {
				return this.employeService.creerEmploye(employe);
			}
			catch (DataIntegrityViolationException e) {	
				if (e.getMessage().contains("matricule_unique")) {
				throw new ConflictException("L'employe de matricule " + employe.getMatricule() + " existe déjà!");
				}
				throw new IllegalArgumentException("Une erreur technique est survenue");
			}
		}
	 
	 @RequestMapping (
			 value = "/{id}",
			 method = RequestMethod.PUT
			 )	
		public Employe updateEmploye(@PathVariable("id") Long id, @RequestBody Employe employe) throws EmployeException {
				return this.employeService.updateEmploye(id, employe);
		}
	 
	 @RequestMapping (
			 value = "/{id}",
			 method = RequestMethod.DELETE
			 )	
		public void deleteEmploye(@PathVariable("id") Long id) throws EmployeException {
				this.employeService.deleteEmploye(id);
		}
	
}
