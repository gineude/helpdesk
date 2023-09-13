package com.gineude.helpdesk.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gineude.helpdesk.domains.Chamado;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class ChamadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;

    @NotNull(message = "O campo TÉCNICO é requerido")
    private Integer tecnico;

    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;

    @NotNull(message = "O campo PRIORIDADE é requerido")
    private Integer prioridade;

    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;

    private String nomeTecnico;

    private String nomeCliente;

    @NotNull(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;

    public ChamadoDTO() {
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this();
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.status = obj.getStatus().getCodigo();
        this.cliente = obj.getCliente().getId();
        this.tecnico = obj.getTecnico().getId();
        this.observacoes = obj.getObservacoes();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
        this.prioridade = obj.getPrioridade().getCodigo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
