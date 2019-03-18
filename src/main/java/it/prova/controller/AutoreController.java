package it.prova.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.javafaker.Faker;

import it.prova.dto.AutoreDTO;
import it.prova.model.Autore;
import it.prova.service.AutoreService;
import it.prova.validator.AutoreValidator;

@Controller
@RequestMapping("/autore/*")
public class AutoreController {

	@Autowired
	private AutoreService autoreService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search() {
		return "autore/search";
	}

	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	public String list(@ModelAttribute("autoreCommand") AutoreDTO autoreDTOInstance, Model model) {
		Autore example = AutoreDTO.buildAutoreInstanceForFindByExample(autoreDTOInstance);
		model.addAttribute("listAutori", autoreService.findByExample(example));
		return "autore/list";
	}

	@RequestMapping(value = "listAutoriLibri600", method = RequestMethod.GET)
	public String listAutoriLibri600(Model model) {
		model.addAttribute("listAutori", autoreService.listAllAutori().stream().filter(
				autoreItem -> autoreItem.getLibri().stream().anyMatch(libroItem -> libroItem.getNumeroPagine() >= 600))
				.collect(Collectors.toList()));
		return "autore/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("autoreCommand", new AutoreDTO());
		return "autore/create";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("autoreCommand") AutoreDTO autoreDTOInstance, BindingResult result,
			Model model) {

		new AutoreValidator().validate(autoreDTOInstance, result);
		if (result.hasErrors()) {
			return "autore/create";
		}

		autoreService.inserisciNuovo(AutoreDTO.buildAutoreInstance(autoreDTOInstance));
		model.addAttribute("listAutori", autoreService.listAllAutori());
		return "/autore/list";
	}
	
	@RequestMapping(value = "save10", method = RequestMethod.GET)
	public String save10(Model model) {
		int i = 0;
		Faker faker = new Faker();

		while (i < 10) {
			Autore autoreTemp = new Autore(faker.name().firstName(), faker.name().lastName(),new Date(), faker.name().username());
			autoreService.inserisciNuovo(autoreTemp);
			i++;
		}

		model.addAttribute("listAutori", autoreService.listAllAutori());
		return "/autore/list";
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(@RequestParam("idAutore") Long idAutore, Model model) {
		model.addAttribute("autoreCommand", new AutoreDTO(autoreService.caricaSingolo(idAutore)));
		return "/autore/show";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("idAutore") Long idAutore, Model model) {
		model.addAttribute("autoreCommand", new AutoreDTO(autoreService.caricaSingolo(idAutore)));
		return "/autore/delete";
	}

	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String remove(@RequestParam("idAutore") Long idAutore, Model model) {

		autoreService.rimuovi(autoreService.caricaSingolo(idAutore));
		model.addAttribute("listLibri", autoreService.listAllAutori());
		return "/autore/list";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("idAutore") Long idAutore, Model model) {
		model.addAttribute("autoreCommand", new AutoreDTO(autoreService.caricaSingolo(idAutore)));
		return "/autore/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("autoreCommand") AutoreDTO autoreDTOInstance, BindingResult result,
			Model model) {

		new AutoreValidator().validate(autoreDTOInstance, result);
		if (result.hasErrors()) {
			return "autore/edit";
		}

		autoreService.aggiorna(AutoreDTO.buildAutoreInstance(autoreDTOInstance));
		model.addAttribute("listAutori", autoreService.listAllAutori());
		return "/autore/list";
	}

}
