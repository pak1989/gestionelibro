package it.prova.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.model.Autore;
import it.prova.service.AutoreService;

@WebServlet("/libro/SearchAutoreAjaxServlet")
public class SearchAutoreAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AutoreService autoreService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public SearchAutoreAjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String termAutore = request.getParameter("termAutore");

		List<Autore> listaAutoreByTerm = autoreService.cercaByNomeILike(termAutore);
		String jsonAutore = buildJsonResponseAutore(listaAutoreByTerm);
		response.getWriter().write(jsonAutore);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

}
