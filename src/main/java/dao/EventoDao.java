package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Evento;

public class EventoDao {

    private Connection con;

    public EventoDao() {
        con = DatabaseConnection.getConnection();
    }

    public void inserir(Evento evento) throws SQLException {
        String sql = "INSERT INTO eventos (nome, data_hora_inicio, data_hora_fim, capacidade, valor_ingresso) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, evento.getNome());
        stmt.setTimestamp(2, Timestamp.valueOf(evento.getDataHoraInicio()));
        stmt.setTimestamp(3, Timestamp.valueOf(evento.getDataHoraFim()));
        stmt.setInt(4, evento.getCapacidade());
        stmt.setDouble(5, evento.getValorIngresso());
        stmt.execute();
        stmt.close();
        con.close();
    }

    public List<Evento> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, data_hora_inicio, data_hora_fim, capacidade, valor_ingresso FROM eventos";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Evento> eventos = new ArrayList<>();
        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id"));
            evento.setNome(rs.getString("nome"));
            evento.setDataHoraInicio(rs.getTimestamp("data_hora_inicio").toLocalDateTime());
            evento.setDataHoraFim(rs.getTimestamp("data_hora_fim").toLocalDateTime());
            evento.setCapacidade(rs.getInt("capacidade"));
            evento.setValorIngresso(rs.getDouble("valor_ingresso"));
            eventos.add(evento);
        }

        stmt.close();
        con.close();
        return eventos;
    }
}
