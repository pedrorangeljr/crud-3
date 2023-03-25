package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexao.SingleConnection;
import model.Usuario;

public class DaoUsuario {

	private Connection connection;
	
	public DaoUsuario() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void slavarUsuario(Usuario usuario) {
		
		try {
			
			String sql = "INERT INTO usuario(nome,sobrenome,email,login,senha)VALUES(?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setString(1, usuario.getNome());
			insert.setString(2, usuario.getSobrenome());
			insert.setString(3, usuario.getEmail());
			insert.setString(4, usuario.getLogin());
			insert.setString(5, usuario.getSenha());
			insert.execute();
			
			connection.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
