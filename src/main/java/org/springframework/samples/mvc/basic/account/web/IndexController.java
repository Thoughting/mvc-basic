package org.springframework.samples.mvc.basic.account.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);

	//	@Autowired
	//	private ActiviteService activiteservice;
	//
	//	public void setActiviteservice(ActiviteService activiteservice) {
	//		this.activiteservice = activiteservice;
	//	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(WebRequest webRequest) {
		logger.info("Index!");

		Map<String, Object> map = new HashMap<String, Object>();

		Principal loggedAs = webRequest.getUserPrincipal();
		/* renvoie l'utilisateur connecté 
		 * webrequest en paramètre  
		 * AUTRE FACON D'ACCEDER AU USER CONNECTE 
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal()); 
		 */

		map.put("loggedAs", loggedAs);
		//map.put("activites", activiteservice.getAllActivite());

		/* paramètre : nom de la vue, nom du modèle, modèle */
		ModelAndView m_w = new ModelAndView("index", "donnees", map);

		return m_w;
	}

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public void modif_profil(WebRequest webRequest) {
		logger.info("Notifications!");
	}
}
