package it.prova.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.dto.LibroDTO;
import it.prova.model.Autore;
import it.prova.model.Libro;
import it.prova.service.AutoreService;
import it.prova.service.LibroService;
import it.prova.validator.LibroValidator;

@Controller
@RequestMapping("/libro/*")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@Autowired
	private AutoreService autoreService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(Model model) {
		model.addAttribute("libroCommand", new LibroDTO());
		model.addAttribute("listAutori", autoreService.listAllAutori());
		return "libro/search";
	}

	@RequestMapping(value = "searchAutoreJquery", method = RequestMethod.GET)
	public void searchAutoreJquery(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String termAutore = request.getParameter("termAutore");
		List<Autore> listaAutoreByTerm = autoreService.cercaByNomeILike(termAutore);
		String jsonAutore = buildJsonResponseAutore(listaAutoreByTerm);
		try {
			response.getWriter().write(jsonAutore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "cambiaStatoJquery", method = RequestMethod.GET)
	public String cambiaStatoJquery(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Long idLibro = Long.parseLong(request.getParameter("idLibro"));
		libroService.cambiaStatoAttivo(idLibro);
		return "redirect:/libro/list";
		// String jsonLibro =
		// buildJsonResponseLibro(libroService.caricaSingolo(idLibro));
		// try {
		// response.getWriter().write(jsonLibro);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET })
	public String list(@ModelAttribute("libroCommand") LibroDTO libroDTOInstance, Model model) {
		Libro example = LibroDTO.buildLibroInstanceForFindByExample(libroDTOInstance);
		model.addAttribute("listLibri", libroService.findByExample(example));
		return "libro/list";
	}

	@RequestMapping(value = "listCento", method = RequestMethod.GET)
	public String listCento(Model model) {
		model.addAttribute("listLibri", libroService.listAllLibri().stream()
				.filter(libroItem -> libroItem.getNumeroPagine() >= 100).collect(Collectors.toList()));
		return "libro/list";
	}

	@RequestMapping(value = "listThe", method = RequestMethod.GET)
	public String listThe(Model model) {

		model.addAttribute("listLibri",
				libroService.listAllLibri().stream()
						.filter(libroItem -> libroItem.getTitolo().startsWith("The")
								&& libroItem.getGenere().equalsIgnoreCase("Mythopoeia"))
						.map(libroItem -> libroItem.getIsbn() / libroItem.getIsbn()).collect(Collectors.toList()));
		return "libro/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("libroCommand", new LibroDTO());
		model.addAttribute("listAutori", autoreService.listAllAutori());
		return "libro/create";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("libroCommand") LibroDTO libroDTOInstance, BindingResult result, Model model) {

		new LibroValidator().validate(libroDTOInstance, result);
		if (result.hasErrors()) {
			model.addAttribute("listAutori", autoreService.listAllAutori());
			return "libro/create";
		}

		libroService.inserisciNuovo(LibroDTO.buildLibroInstance(libroDTOInstance));
		model.addAttribute("listLibri", libroService.listAllLibriEager());
		return "/libro/list";
	}

	@RequestMapping(value = "save50", method = RequestMethod.GET)
	public String save100(Model model) {
		int i = 0;
		Faker faker = new Faker();

		while (i < 50) {
			Libro libroTemp = new Libro(faker.book().title(), faker.book().genre(),
					(Integer) faker.number().numberBetween(50, 1000), true, 8845294048L,
					autoreService.caricaSingolo(1L));
			libroService.inserisciNuovo(libroTemp);
			i++;
		}

		model.addAttribute("listLibri", libroService.listAllLibriEager());
		return "/libro/list";
	}

	@RequestMapping(value = "cambiaAutore", method = RequestMethod.GET)
	public String cambiaAutore(Model model) {

		Faker faker = new Faker();
		List<Libro> listaLibri = libroService.listAllLibriEager();
		
		for (Libro libro : listaLibri) {
			libro.setAutore(autoreService.caricaSingolo(faker.number().numberBetween(1L, autoreService.listAllAutori().size())));
			libroService.aggiorna(libro);
		}
		
		model.addAttribute("listLibri", libroService.listAllLibriEager());
		return "/libro/list";
	}

	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String show(@RequestParam("idLibro") Long idLibro, Model model) {
		model.addAttribute("libroCommand", new LibroDTO(libroService.caricaSingolo(idLibro)));
		return "/libro/show";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("idLibro") Long idLibro, Model model) {
		model.addAttribute("libroCommand", new LibroDTO(libroService.caricaSingolo(idLibro)));
		return "/libro/delete";
	}

	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String remove(@RequestParam("idLibro") Long idLibro, Model model) {

		libroService.rimuovi(libroService.caricaSingolo(idLibro));
		model.addAttribute("listLibri", libroService.listAllLibri());
		return "/libro/list";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("idLibro") Long idLibro, Model model) {

		model.addAttribute("libroCommand", new LibroDTO(libroService.caricaSingolo(idLibro)));
		model.addAttribute("listAutori", autoreService.listAllAutori());
		return "/libro/edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute("libroCommand") LibroDTO libroDTOInstance, BindingResult result, Model model) {

		new LibroValidator().validate(libroDTOInstance, result);
		if (result.hasErrors()) {
			model.addAttribute("listAutori", autoreService.listAllAutori());
			return "libro/edit";
		}

		libroService.aggiorna(LibroDTO.buildLibroInstance(libroDTOInstance));
		model.addAttribute("listLibri", libroService.listAllLibri());
		return "/libro/list";
	}

	private String buildJsonResponseAutore(List<Autore> listaAutore) {
		JsonArray ja = new JsonArray();
		for (Autore autoreElement : listaAutore) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", autoreElement.getId());
			jo.addProperty("label", autoreElement.getNome() + " " + autoreElement.getCognome());
			ja.add(jo);
		}
		return new Gson().toJson(ja);
	}

	// private String buildJsonResponseLibro(Libro singoloLibro) {
	// JsonArray ja = new JsonArray();
	// JsonObject jo = new JsonObject();
	// jo.addProperty("value", singoloLibro.getId());
	// if (singoloLibro.getAttivo() == true) {
	// jo.addProperty("label", "disattiva");
	// } else {
	// jo.addProperty("label", "attiva");
	// }
	// ja.add(jo);
	// return new Gson().toJson(ja);
	// }

}
