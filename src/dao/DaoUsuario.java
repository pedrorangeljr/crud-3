package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.SingleConnection;
import model.Usuario;

public class DaoUsuario {

	private Connection connection;
	
	public DaoUsuario() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void slavarUsuario(Usuario usuario) {
		
		try {
			
			String sql = "INSERT INTO usuario(nome,sobrenome,email,login,senha)VALUES(?,?,?,?,?)";
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
	
	/*Metodo Listar Usuaários*/
	
	public List<Usuario> listarUsuarios() throws Exception {
		
		List<Usuario> listar = new ArrayList<Usuario>();
		
		String sql = "SELECT * FROM usuario";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet retorno = select.executeQuery();
		
		
		while(retorno.next()) {
			
			Usuario usuario = new Usuario();
			
			usuario.setId(retorno.getLong("id"));
			usuario.setNome(retorno.getString("nome"));
			usuario.setSobrenome(retorno.getString("sobrenome"));
			usuario.setEmail(retorno.getString("email"));
			usuario.setLogin(retorno.getString("login"));
			//usuario.setSenha(retorno.getString("senha"));
			
			listar.add(usuario);
		}
		
		return listar;
	}
	
	/*metodo deletar*/
	
	public void deletar(String id) {
		
		try {
			
			String sql = "DELETE FROM usuario WHERE id= '"+id+"'";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			
			connection.commit();
			
		} catch (Exception e) {
			
			try {
				
				connection.rollback();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
	}
}
