package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Compra;

public class CompraDao {

    private Connection con;

    public CompraDao() {
        con = DatabaseConnection.getConnection();
    }

    public void inserir(Compra compra) throws SQLException {
        String sql = "INSERT INTO compras (forma_pagamento, status, valor, cliente_id, qtd_bilhete) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, compra.getFormaPagamento());
        stmt.setString(2, compra.getStatus());
        stmt.setFloat(3, compra.getValor());
        stmt.setInt(4, compra.getClienteId());
        stmt.setInt(5, compra.getQtdBilhete());
        stmt.execute();
        stmt.close();
        con.close();
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
