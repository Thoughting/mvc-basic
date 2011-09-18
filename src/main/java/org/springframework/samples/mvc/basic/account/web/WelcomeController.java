package org.springframework.samples.mvc.basic.account.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 * Handles requests for the application welcome page. 
 */
@Controller
public class WelcomeController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(WelcomeController.class);

	//	@Autowired
	//	private ActiviteService activiteservice;
	//
	//	public void setActiviteservice(ActiviteService activiteservice) {
	//		this.activiteservice = activiteservice;
	//	}
	//
	//	@Autowired
	//	private PersonneService personneservice;
	//
	//	public void setPersonneservice(PersonneService personneservice) {
	//		this.personneservice = personneservice;
	//	}

	/** 
	 * Simply selects the welcome view to render by returning void and relying 
	 * on the default request-to-view-translator. 
	 */
	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public ModelAndView welcome() {
		logger.info("Welcome!");
		String date = (new Date()).toString();/* Aujourd'hui*/
		date = DateFormat.getDateInstance().format(new Date());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aujourdhui", date);
		/*activiteService inject� : @Autowired */
		//		map.put("activites", activiteservice.getAllActivite());

		/* param�tre : nom de la vue, nom du mod�le, mod�le */
		ModelAndView m_w = new ModelAndView("login", "donnees", map);
		return m_w;
	}

	//	@RequestMapping(value = "register", method = RequestMethod.GET)
	//	public ModelAndView initPersonne() {
	//		logger.info("Register");
	//
	//		ModelAndView m_w = new ModelAndView("register", "personne", new Personne());
	//		m_w.addObject("personne_valide", false);
	//		return m_w;
	//	}

	//	@SuppressWarnings("deprecation")
	//	@RequestMapping(value = "register", method = RequestMethod.POST)
	//	public String processForm(@Valid Personne personne, BindingResult result, ModelMap model) {
	//		if (!result.hasErrors() && personne.memeMdp()) {
	//			model.addObject("personne_valide", true);
	//			System.out.println(personneservice);
	//			personneservice.ajouterPersonne(personne);
	//		} else
	//			model.addObject("mdpErr", "confirmation du mot de passe invalide");
	//		return "register";
	//	}

	@RequestMapping(value = "acces_refuse")
	public void acces_refuse() {
		logger.info("Acces refus�!");
	}
}
