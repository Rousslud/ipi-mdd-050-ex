package com.ipiecoles.java.mdd050.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.service.ManagerService;

@RestController
@RequestMapping("/managers")
public class ManagerController {

	public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "/{idManager}/equipe/{idemploye}/remove", method = RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeEmployeTeam(@PathVariable("idManager") Long idm, @PathVariable("idemploye") Long ide) {
		this.managerService.deleteTechniciens(idm, ide);
	}
	
	@RequestMapping(value = "/{idManager}/equipe/{matricule}/add", method = RequestMethod.GET)
	public Technicien addEmployeTeam(@PathVariable("idManager") Long idm, @PathVariable("matricule") String matricule) {
		return this.managerService.addTechniciens(idm, matricule);
	}

}
