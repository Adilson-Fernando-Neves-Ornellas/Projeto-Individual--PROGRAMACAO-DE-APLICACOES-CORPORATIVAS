package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Compra;
import model.Evento;
import model.Bilhete;

public class BilheteDao {

    private Connection con;

    public BilheteDao() {
        con = DatabaseConnection.getConnection();
    }

    public void inserir(Bilhete bilhete) throws SQLException {
        String sql = "INSERT INTO bilhetes (evento_id, compra_id) VALUES (?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, bilhete.getEventoId());
        stmt.setInt(2, bilhete.getCompraId());
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Bilhete> listarTodos() throws SQLException {
        String sql = "SELECT b.id, b.evento_id, b.compra_id, " +
                "e.nome AS evento_nome, " +
                "c.forma_pagamento, c.status, c.valor, c.cliente_id, c.qtd_bilhete, " +
                "cl.id AS cliente_id, cl.nome AS cliente_nome, cl.cpf AS cliente_cpf, cl.telefone AS cliente_telefone "
                +
                "FROM bilhetes b " +
                "JOIN eventos e ON b.evento_id = e.id " +
                "JOIN compras c ON b.compra_id = c.id " +
                "JOIN clientes cl ON c.cliente_id = cl.id";

        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Bilhete> bilhetes = new ArrayList<>();
        while (rs.next()) {
            Bilhete bilhete = new Bilhete();

            bilhete.setId(rs.getInt("id"));
            bilhete.setEventoId(rs.getInt("evento_id"));
            bilhete.setCompraId(rs.getInt("compra_id"));

            Evento evento = new Evento();
            evento.setNome(rs.getString("evento_nome"));
            bilhete.setEvento(evento);

            Compra compra = new Compra();
            compra.setFormaPagamento(rs.getString("forma_pagamento"));
            compra.setStatus(rs.getString("status"));
            compra.setValor(rs.getFloat("valor"));
            compra.setClienteId(rs.getInt("cliente_id"));
            compra.setQtdBilhete(rs.getInt("qtd_bilhete"));
            compra.setId(rs.getInt("compra_id"));

            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("cliente_id"));
            cliente.setNome(rs.getString("cliente_nome"));
            cliente.setCpf(rs.getString("cliente_cpf"));
            cliente.setTelefone(rs.getString("cliente_telefone"));

            compra.setCliente(cliente);

            bilhete.setCompra(compra);

            bilhetes.add(bilhete);
        }

        rs.close();
        stmt.close();
        con.close();

        return bilhetes;
    }

}
