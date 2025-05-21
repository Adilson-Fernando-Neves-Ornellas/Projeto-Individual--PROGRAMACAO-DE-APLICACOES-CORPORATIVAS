package model;

import java.time.LocalDateTime;

public class Evento {
    private int id;
    private String nome;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private int capacidade;
    private double valorIngresso;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }
    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }
    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }
    public void setValorIngresso(double valorIngresso) {
        this.valorIngresso = valorIngresso;
    }
}
