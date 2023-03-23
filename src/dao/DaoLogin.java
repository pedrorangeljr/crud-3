package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.SingleConnection;
import model.Usuario;

public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		
		connection = SingleConnection.getConnection();
	}
	
	public boolean validaLogin(Usuario usuario) throws Exception {
		
		String sql = "SELECT * FROM usuario WHERE upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement validar = connection.prepareStatement(sql);
		validar.setString(1, usuario.getLogin());
		validar.setString(2, usuario.getSenha());
		
		ResultSet resultado = validar.executeQuery();
		
		if(resultado.next()) {
			
			return true; /*Autenticado*/
		}
		
		return false; /*Não autenticado*/
	}

}
