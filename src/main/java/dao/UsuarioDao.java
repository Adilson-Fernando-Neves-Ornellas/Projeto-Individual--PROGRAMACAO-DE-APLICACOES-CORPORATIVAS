package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDao {

    private Connection con;

    public UsuarioDao() {
        con = DatabaseConnection.getConnection();
    }

    public void inserir(Usuario usuario) throws SQLException {
        String sql = "insert into usuarios(nome,email, senha) values(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        Usuario usuario = null;
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
        }
        return usuario;
    }

}
