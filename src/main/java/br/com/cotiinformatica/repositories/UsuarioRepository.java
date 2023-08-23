package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Usuario;

public class UsuarioRepository {

	private JdbcTemplate jdbcTemplate;

	public UsuarioRepository(DataSource dataSource) {

		// inicializa o atributo jdbc template passando o data source

		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	public void create(Usuario usuario) throws Exception {

		String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?,?,?)";
		Object[] params = { usuario.getNome(), usuario.getEmail(), usuario.getSenha() };

		jdbcTemplate.update(sql, params);

	}

	public Usuario find(String email) throws Exception {

		String sql = "SELECT * FROM usuario WHERE email = ?";

		Object[] params = { email };

		List<Usuario> resultado = jdbcTemplate.query(sql, params, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			}

		});

		// verifica se encontrou usuário
		if (resultado.size() > 0) {
			return resultado.get(0);
		} else
			return null;
	}

	public Usuario find(String email, String senha) throws Exception {

		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

		Object[] params = { email, senha };

		List<Usuario> resultado = jdbcTemplate.query(sql, params, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));

				return usuario;
			}

		});

		// verifica se encontrou usuário
		if (resultado.size() > 0) {
			return resultado.get(0);
		} else
			return null;
	}

}
