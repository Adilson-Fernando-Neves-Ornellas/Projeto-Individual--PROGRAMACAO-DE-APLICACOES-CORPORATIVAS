package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bilhete;

public class BilheteDao {

    private Connection con;

    public BilheteDao() {
         con = DatabaseConnection.getConnection();
    }

    public List<Bilhete> listarTodos() throws SQLException {
        String sql = "SELECT id, evento_id, compra_id FROM bilhetes";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Bilhete> bilhetes = new ArrayList<>();
        while (rs.next()) {
            Bilhete bilhete = new Bilhete();
            bilhete.setId(rs.getInt("id"));
            bilhete.setEventoId(rs.getInt("evento_id"));
            bilhete.setCompraId(rs.getInt("compra_id"));
            bilhetes.add(bilhete);
        }

        stmt.close();
        con.close();
        return bilhetes;
    }
}
