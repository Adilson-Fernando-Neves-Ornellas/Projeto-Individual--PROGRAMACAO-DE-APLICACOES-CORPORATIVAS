package model;

public class Compra {
    private int id;
    private String formaPagamento;
    private String status;
    private float valor;
    private int clienteId;
    private int qtdBilhete;
    private Cliente cliente;

    public float calcularValorTotal() {
        return this.valor * this.qtdBilhete;
    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
