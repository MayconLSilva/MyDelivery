package com.br.megabytesoftware.mydelivery.Modelo;

public class EntregaModelo {
    private int idEntrega;
    private String dataLancamentoEntrega;
    private String dataSaidaEntrega;
    private String dataEntregueEntrega;
    private int idCliente;
    private int idTracking;
    private String cargaEntrega;
    private String valorEntrega;
    private String assinaturaEntrega;
    private int idERPEntrega;
    private int idCargaEntrega;
    private String ocorrenciaEntrega;
    private String obsEntrega;

    //MÉTODOS PARA CONSULTA
    private String nomeCliente;

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getDataLancamentoEntrega() {
        return dataLancamentoEntrega;
    }

    public void setDataLancamentoEntrega(String dataLancamentoEntrega) {
        this.dataLancamentoEntrega = dataLancamentoEntrega;
    }

    public String getDataSaidaEntrega() {
        return dataSaidaEntrega;
    }

    public void setDataSaidaEntrega(String dataSaidaEntrega) {
        this.dataSaidaEntrega = dataSaidaEntrega;
    }

    public String getDataEntregueEntrega() {
        return dataEntregueEntrega;
    }

    public void setDataEntregueEntrega(String dataEntregueEntrega) {
        this.dataEntregueEntrega = dataEntregueEntrega;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTracking() {
        return idTracking;
    }

    public void setIdTracking(int idTracking) {
        this.idTracking = idTracking;
    }

    public String getCargaEntrega() {
        return cargaEntrega;
    }

    public void setCargaEntrega(String cargaEntrega) {
        this.cargaEntrega = cargaEntrega;
    }

    public String getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(String valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

    public String getAssinaturaEntrega() {
        return assinaturaEntrega;
    }

    public void setAssinaturaEntrega(String assinaturaEntrega) {
        this.assinaturaEntrega = assinaturaEntrega;
    }

    public int getIdERPEntrega() {
        return idERPEntrega;
    }

    public void setIdERPEntrega(int idERPEntrega) {
        this.idERPEntrega = idERPEntrega;
    }

    public int getIdCargaEntrega() {
        return idCargaEntrega;
    }

    public void setIdCargaEntrega(int idCargaEntrega) {
        this.idCargaEntrega = idCargaEntrega;
    }

    public String getOcorrenciaEntrega() {
        return ocorrenciaEntrega;
    }

    public void setOcorrenciaEntrega(String ocorrenciaEntrega) {
        this.ocorrenciaEntrega = ocorrenciaEntrega;
    }

    public String getObsEntrega() {
        return obsEntrega;
    }

    public void setObsEntrega(String obsEntrega) {
        this.obsEntrega = obsEntrega;
    }


    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
