package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import model.Usuario;


@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DaoUsuario daoUsuario = new DaoUsuario();
    
    public ServletUsuario() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			String usuario = request.getParameter("excluir");
			
			if(acao.equalsIgnoreCase("excluir")) {
				
				daoUsuario.deletar(usuario);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("principal/principal.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);
			}
			
			else if(acao.equalsIgnoreCase("listarTodos")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("principal/principal.jsp");
				request.setAttribute("usuarios", daoUsuario.listarUsuarios());
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario();
			
			usuario.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setNome(nome);
			usuario.setSobrenome(sobrenome);
			usuario.setEmail(email);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			
			if(id == null || id.isEmpty()) {
				
				daoUsuario.slavarUsuario(usuario);
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("principal/principal.jsp");
			request.setAttribute("usuarios", daoUsuario.listarUsuarios());
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
