package com.ipiecoles.java.mdd050.controller;

import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
			 params="matricule"
			 )	 
	 public Employe rechercheMatricule(@RequestParam("matricule") String matricule, Map<String,Object> model) {
		 return employeService.findMyMatricule(matricule);
		}
	 

}
