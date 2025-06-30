package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDao {

    private Connection con;

    public UsuarioDao() {
        con = DatabaseConnection.getConnection();
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email FROM usuarios";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuarios.add(usuario);
        }

        stmt.close();
        con.close();
        return usuarios;
    }

    public List<Usuario> listarPorNome(String nome) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email FROM usuarios WHERE LOWER(nome) LIKE ?";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + nome.toLowerCase() + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            usuarios.add(u);
        }

        stmt.close();
        con.close();
        return usuarios;
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

    public boolean emailExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }


}
