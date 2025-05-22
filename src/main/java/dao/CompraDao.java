package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import model.Compra;
import model.Cliente;
import dao.ClienteDao;

public class CompraDao {

    private Connection con;

    public CompraDao() {
        con = DatabaseConnection.getConnection();
    }

    public int inserir(Compra compra) throws SQLException {
        String sql = "INSERT INTO compras (forma_pagamento, status, valor, cliente_id, qtd_bilhete) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, compra.getFormaPagamento());
        stmt.setString(2, compra.getStatus());
        stmt.setFloat(3, compra.getValor());
        stmt.setInt(4, compra.getClienteId());
        stmt.setInt(5, compra.getQtdBilhete());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        int idGerado = -1;
        if (rs.next()) {
            idGerado = rs.getInt(1);
        }

        stmt.close();
        con.close();
        return idGerado;
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM compras WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
        stmt.close();
        con.close();
    }

    public List<Compra> listarTodos() throws SQLException {
        String sql = "SELECT id, forma_pagamento, status, valor, cliente_id, qtd_bilhete FROM compras";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ClienteDao clienteDao = new ClienteDao(); // ADICIONADO

        List<Compra> compras = new ArrayList<>();
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setId(rs.getInt("id"));
            compra.setFormaPagamento(rs.getString("forma_pagamento"));
            compra.setStatus(rs.getString("status"));
            compra.setValor(rs.getFloat("valor"));
            int clienteId = rs.getInt("cliente_id");
            compra.setClienteId(clienteId);
            compra.setQtdBilhete(rs.getInt("qtd_bilhete"));

            Cliente cliente = clienteDao.buscarPorId(clienteId); 
            compra.setCliente(cliente);

            compras.add(compra);
        }

        stmt.close();
        con.close();
        return compras;
    }

    public List<Compra> listarPorStatus(String status) throws SQLException {
        String sql = "SELECT id, forma_pagamento, status, valor, cliente_id, qtd_bilhete FROM compras WHERE status = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, status);
        ResultSet rs = stmt.executeQuery();

        List<Compra> compras = new ArrayList<>();
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setId(rs.getInt("id"));
            compra.setFormaPagamento(rs.getString("forma_pagamento"));
            compra.setStatus(rs.getString("status"));
            compra.setValor(rs.getFloat("valor"));
            compra.setClienteId(rs.getInt("cliente_id"));
            compra.setQtdBilhete(rs.getInt("qtd_bilhete"));
            compras.add(compra);
        }

        stmt.close();
        con.close();
        return compras;
    }

    public List<Compra> listarPorCliente(int clienteId) throws SQLException {
        String sql = "SELECT id, forma_pagamento, status, valor, cliente_id, qtd_bilhete FROM compras WHERE cliente_id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, clienteId);
        ResultSet rs = stmt.executeQuery();

        List<Compra> compras = new ArrayList<>();
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setId(rs.getInt("id"));
            compra.setFormaPagamento(rs.getString("forma_pagamento"));
            compra.setStatus(rs.getString("status"));
            compra.setValor(rs.getFloat("valor"));
            compra.setClienteId(rs.getInt("cliente_id"));
            compra.setQtdBilhete(rs.getInt("qtd_bilhete"));
            compras.add(compra);
        }

        stmt.close();
        con.close();
        return compras;
    }

    public List<Compra> listarPorStatusECliente(String status, int clienteId) throws SQLException {
        String sql = "SELECT id, forma_pagamento, status, valor, cliente_id, qtd_bilhete FROM compras WHERE status = ? AND cliente_id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, status);
        stmt.setInt(2, clienteId);
        ResultSet rs = stmt.executeQuery();

        List<Compra> compras = new ArrayList<>();
        while (rs.next()) {
            Compra compra = new Compra();
            compra.setId(rs.getInt("id"));
            compra.setFormaPagamento(rs.getString("forma_pagamento"));
            compra.setStatus(rs.getString("status"));
            compra.setValor(rs.getFloat("valor"));
            compra.setClienteId(rs.getInt("cliente_id"));
            compra.setQtdBilhete(rs.getInt("qtd_bilhete"));
            compras.add(compra);
        }

        stmt.close();
        con.close();
        return compras;
    }
}
