package com.example.app.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.app.entity.RasaPsa;
import com.example.app.repository.RasaPsaRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class HomeController {
	
	@Autowired
	private RasaPsaRepository rasaPsaRepository;
	
	
	//Ovaj API za testiranje 
	@GetMapping("/test")
	public String test() {
		
		return "Test.jsp";
	}
	
	
	
	@GetMapping("/home")
	public String home() {
		
		return "index.jsp";
	}
	

	@GetMapping("/loginStranica")
	public String loginPage () {
		
		return "Login.jsp";
			
	}  
	
	@GetMapping("/registrationStranica")
	public String registrationPage () {
		
		return "Registracija.jsp";
			
	}  


	
	@GetMapping("/AddRasaForm")
	public String AddRasa() {
		
		return "AddRasaForm.jsp";
	}
	
	@GetMapping("/updateRasaForm")
	public String updateRasa() {
		
		return "UpdateRasa.jsp";
		
	}
	
	@GetMapping("/deleteRasaForm")
	public String deleteRasa() {
		
		return "DeleteRasa.jsp";
		
	}
	
	@GetMapping("/RasaMainPage")
	public String RasaGlavnaStranica () {
		
		return "RasaPsaGlavnaStranica.jsp";
			
	}
	
	
	
	
	
	//API Akcija save
	@PostMapping("/saveRasaPsaData")
	@Transactional 
	public String saveData(@RequestParam("imeRase") String imeRase,
	                       @RequestParam("opisRase") String opisRase) {
	    
	    RasaPsa rasaPsa = new RasaPsa(imeRase,opisRase); 
	
	    
	    RasaPsa savedRasa = rasaPsaRepository.save(rasaPsa);
	    
	    System.out.println(savedRasa);
	    
	    
	    // Redirect to the home page after successful data save
	    return "UspesnoUbacivanje.jsp";
	    
	}
/*	
	//API Akcija izvlacimo sve rease
	@GetMapping("/getListaRasaPasa")
	@ResponseBody
	public ModelAndView getAllRasa() {
		
		ModelAndView mv = new ModelAndView();
		
		List<RasaPsa>findAllRasa = rasaPsaRepository.findAll();
		
		mv.addObject("AllRasaPsa",findAllRasa);
		
		mv.setViewName("ListaRasaPasa.jsp");
		
		return mv;		
	}
*/
	
	@GetMapping("/getListaRasaPasa")
	public String getAllRasa(HttpSession session) {
		
		
		 List<RasaPsa> findAllRasa = rasaPsaRepository.findAll();
		 
	        
	     
	      List<String> imeRasaList = new ArrayList<>();
	      List<String> opisRaseList = new ArrayList<>();
	        
	       for (RasaPsa rasaPsa : findAllRasa) {
	            imeRasaList.add(rasaPsa.getImeRase());
	            opisRaseList.add(rasaPsa.getOpisRase());
	        }
	        
	       
	        session.setAttribute("imeRasaList", imeRasaList);
	        session.setAttribute("opisRaseList", opisRaseList);
	        
	        return "ListaRasaPasa.jsp";
	    }
	
	
	
	
	
	
	
/*	Update po imenu izabranom 
 
 @PostMapping("/updateRasaPsa")
@ResponseBody
public String updateRasa(@RequestParam String imeRase, @RequestParam String opisRase, @RequestParam String newImeRase, @RequestParam String newOpisRase) {
    // Retrieve the existing entity from the database using the provided name and description
    RasaPsa existingRasaPsa = rasaPsaRepository.findByImeRaseAndOpisRase(imeRase, opisRase);
    
    if (existingRasaPsa != null) {
        // Update the retrieved entity with the new name and description
        existingRasaPsa.setImeRase(newImeRase);
        existingRasaPsa.setOpisRase(newOpisRase);
        
        // Save the updated entity back to the database
        rasaPsaRepository.save(existingRasaPsa);
        
        return "Uspesno ste updatejtovali rasu pasa sa imenom: " + imeRase + " i opisom: " + opisRase;
    } else {
        return "Rasa sa imenom " + imeRase + " i opisom " + opisRase + " nije pronadjena.";
    }
}			
	}
	*/
	
	//API  za update rase pasa
	@PostMapping("/updateRasaPsa")
	@ResponseBody
	public String updateRasa(@RequestParam int idRasa, @RequestParam String imeRase, @RequestParam String opisRase) {

	    Optional<RasaPsa> rasaPsaOptional = rasaPsaRepository.findById(idRasa);
	    
	    if (rasaPsaOptional.isPresent()) {
	    
	        RasaPsa existingRasaPsa = rasaPsaOptional.get();
	        existingRasaPsa.setImeRase(imeRase);
	        existingRasaPsa.setOpisRase(opisRase);
	        
	       
	        rasaPsaRepository.save(existingRasaPsa);
	        
	        return "Uspesno ste updatejtovali rasu psa: " ;
	    } else {
	        return "Rasa nije pronadjena.";
	    }
	}
	
	
	//API za delete rase psa
	@PostMapping("/deleteRasaPsa") 
	@ResponseBody
	public String deleteRasa(@RequestParam String imeRase) {
	   
	    RasaPsa rasaPsa = rasaPsaRepository.findByImeRase(imeRase);
	    
	    if (rasaPsa != null) {
	       
	        rasaPsaRepository.delete(rasaPsa);
	        
	        return "Uspesno ste izbrisali rasu psa sa imenom: " + imeRase;
	        
	    } else {
	    	
	        return "Rasa psa sa imenom " + imeRase + " nije pronadjena.";
	    }
		
	}
	

}
