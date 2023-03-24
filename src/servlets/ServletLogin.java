package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLogin;
import model.Usuario;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin();

	public ServletLogin() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String url = request.getParameter("url");

			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

				Usuario usuario = new Usuario();

				usuario.setLogin(login);
				usuario.setSenha(senha);

				if (daoLogin.validaLogin(usuario)) {

					request.getSession().setAttribute("usuario", usuario.getLogin());

					if (url == null || url.equals("null")) {

						url = "principal/principal.jsp";
					}

					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					request.setAttribute("msg", "Informe Login e senha corretamente");
					dispatcher.forward(request, response);
				} else {

					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe Login e senha corretamente");
					redirecionar.forward(request, response);
				}

			} else {

				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe Login e senha corretamente");
				redirecionar.forward(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();

			RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}
	}

}
