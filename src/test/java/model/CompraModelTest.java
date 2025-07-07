package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Compra;

public class CompraModelTest {

    @Test
    public void testCalcularValorTotal() {
        Compra compra = new Compra();
        compra.setValor(50.0f);
        compra.setQtdBilhete(3);

        float total = compra.calcularValorTotal();
        assertEquals(150.0f, total, 0.001);
    }
}