package dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import model.Compra;
import java.sql.SQLException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompraDaoTest {

    private static int idGerado;

    @Test
    @Order(1)
    public void testInserir() throws SQLException {
        CompraDao dao = new CompraDao();
        Compra compra = new Compra();
        compra.setFormaPagamento("CARTAO");
        compra.setStatus("Pendente");
        compra.setValor(100);
        compra.setClienteId(1);  // Cliente válido na base
        compra.setQtdBilhete(2);

        idGerado = dao.inserir(compra);
        assertTrue(idGerado > 0);
    }

    @Test
    @Order(2)
    public void testListarTodos() throws SQLException {
        CompraDao dao = new CompraDao();
        List<Compra> compras = dao.listarTodos();
        assertNotNull(compras);
        assertTrue(compras.size() > 0);

        boolean encontrou = compras.stream().anyMatch(c -> c.getId() == idGerado);
        assertTrue(encontrou);
    }

    @Test
    @Order(3)
    public void testListarPorNomeCliente() throws SQLException {
        CompraDao dao = new CompraDao();
        List<Compra> compras = dao.listarPorNomeCliente("nome do cliente"); // Substitua por nome válido da sua base
        assertNotNull(compras);
        // Aqui pode colocar alguma validação mais específica se quiser
    }

    @Test
    @Order(4)
    public void testExcluir() throws SQLException {
        CompraDao dao = new CompraDao();
        dao.excluir(idGerado);

        List<Compra> compras = dao.listarTodos();
        boolean existe = compras.stream().anyMatch(c -> c.getId() == idGerado);
        assertFalse(existe);
    }
}
