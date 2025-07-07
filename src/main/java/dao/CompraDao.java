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

    public CompraDao() {

    }

    public int inserir(Compra compra) throws SQLException {
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();

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
            return idGerado;
        } finally {
            if (con != null) con.close();
        }
    }

    public void excluir(int id) throws SQLException {
        Connection con = null;
        
        try {
            con = DatabaseConnection.getConnection();
            // Primeiro exclui os bilhetes associados
            String sqlBilhetes = "DELETE FROM bilhetes WHERE compra_id = ?";
            PreparedStatement stmtBilhetes = con.prepareStatement(sqlBilhetes);
            stmtBilhetes.setInt(1, id);
            stmtBilhetes.executeUpdate();
            stmtBilhetes.close();

            // Agora exclui a compra
            String sqlCompra = "DELETE FROM compras WHERE id = ?";
            PreparedStatement stmtCompra = con.prepareStatement(sqlCompra);
            stmtCompra.setInt(1, id);
            stmtCompra.executeUpdate();
            stmtCompra.close();

        } finally {
            if (con != null) con.close();
        }
    }

    public List<Compra> listarTodos() throws SQLException {
        Connection con = null;
        
        try {
            con = DatabaseConnection.getConnection();
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
            return compras;
        } finally {
            if (con != null) con.close();
        }
    }

    public List<Compra> listarPorNomeCliente(String nome) throws SQLException {
        Connection con = null;
        
        try {
            con = DatabaseConnection.getConnection();        
            String sql = """
                        SELECT c.id, c.forma_pagamento, c.status, c.valor, c.cliente_id, c.qtd_bilhete
                        FROM compras c
                        INNER JOIN clientes cl ON c.cliente_id = cl.id
                        WHERE LOWER(cl.nome) LIKE ?
                    """;

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome.toLowerCase() + "%");

            ResultSet rs = stmt.executeQuery();
            ClienteDao clienteDao = new ClienteDao();
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
            return compras;

        } finally {
            if (con != null) con.close();
        }
    }

}
