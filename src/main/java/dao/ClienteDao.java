package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao {

    private Connection con;

    public ClienteDao() {
        con = DatabaseConnection.getConnection();
    }

    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, data_nasc, senha) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getTelefone());
        stmt.setDate(4, Date.valueOf(cliente.getDataNasc()));
        stmt.setString(5, cliente.getSenha());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, cpf, telefone, data_nasc, senha FROM clientes";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setDataNasc(rs.getDate("data_nasc").toLocalDate());
            cliente.setSenha(rs.getString("senha"));
            clientes.add(cliente);
        }

        stmt.close();
        con.close();
        return clientes;
    }

    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome FROM clientes WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
        }

        stmt.close();
        // con.close();

        return cliente;
    }

}
