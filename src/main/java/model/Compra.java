package model;

public class Compra {
    private int id;
    private String formaPagamento; // DINHEIRO, CARTAO, PIX
    private String status;         // PENDENTE, PAGO, CANCELADO
    private float valor;
    private int clienteId;
    private int qtdBilhete;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getClienteId() {
        return clienteId;
    }
    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getQtdBilhete() {
        return qtdBilhete;
    }
    public void setQtdBilhete(int qtdBilhete) {
        this.qtdBilhete = qtdBilhete;
    }
}
