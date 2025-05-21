package model;

public class Bilhete {
    private int id;
    private int eventoId;
    private int compraId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getEventoId() {
        return eventoId;
    }
    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public int getCompraId() {
        return compraId;
    }
    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }
}
