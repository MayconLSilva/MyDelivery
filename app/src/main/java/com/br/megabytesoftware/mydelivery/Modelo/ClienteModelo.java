package com.br.megabytesoftware.mydelivery.Modelo;

public class ClienteModelo {

    //Cadastro Principal
    private int IdCli;
    private String nomeCli;
    private String cpfCli;
    private String rgCli;
    private String telefoneCli;
    private String celularCli;
    private String emailCli;

    //Cadastro de Endereço
    private int idenderecoCli;
    private String enderecoCli;
    private String numeroEnderecoCli;
    private String bairroCli;
    private String ufCli;
    private String cidadeCli;
    private String cepCli;
    private Boolean enderecoPrincipalCli;

    private String dataCadastroCli;
    private String dataAlteracaoCli;

    public ClienteModelo(){}

    public int getIdCli() {
        return IdCli;
    }

    public void setIdCli(int idCli) {
        IdCli = idCli;
    }

    public String getNomeCli() {
        return nomeCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    public String getCpfCli() {
        return cpfCli;
    }

    public void setCpfCli(String cpfCli) {
        this.cpfCli = cpfCli;
    }

    public String getEnderecoCli() {
        return enderecoCli;
    }

    public void setEnderecoCli(String enderecoCli) {
        this.enderecoCli = enderecoCli;
    }

    public String getNumeroEnderecoCli() {
        return numeroEnderecoCli;
    }

    public void setNumeroEnderecoCli(String numeroEnderecoCli) {
        this.numeroEnderecoCli = numeroEnderecoCli;
    }

    public String getBairroCli() {
        return bairroCli;
    }

    public void setBairroCli(String bairroCli) {
        this.bairroCli = bairroCli;
    }

    public String getUfCli() {
        return ufCli;
    }

    public void setUfCli(String ufCli) {
        this.ufCli = ufCli;
    }

    public String getCidadeCli() {
        return cidadeCli;
    }

    public void setCidadeCli(String cidadeCli) {
        this.cidadeCli = cidadeCli;
    }

    public String getTelefoneCli() {
        return telefoneCli;
    }

    public void setTelefoneCli(String telefoneCli) {
        this.telefoneCli = telefoneCli;
    }

    public String getCelularCli() {
        return celularCli;
    }

    public void setCelularCli(String celularCli) {
        this.celularCli = celularCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getDataCadastroCli() {
        return dataCadastroCli;
    }

    public void setDataCadastroCli(String dataCadastroCli) {
        this.dataCadastroCli = dataCadastroCli;
    }

    public String getDataAlteracaoCli() {
        return dataAlteracaoCli;
    }

    public void setDataAlteracaoCli(String dataAlteracaoCli) {
        this.dataAlteracaoCli = dataAlteracaoCli;
    }

    public String getRgCli() {
        return rgCli;
    }

    public void setRgCli(String rgCli) {
        this.rgCli = rgCli;
    }

    public String getCepCli() {
        return cepCli;
    }

    public void setCepCli(String cepCli) {
        this.cepCli = cepCli;
    }

    public Boolean getEnderecoPrincipalCli() {
        return enderecoPrincipalCli;
    }

    public void setEnderecoPrincipalCli(Boolean enderecoPrincipalCli) {
        this.enderecoPrincipalCli = enderecoPrincipalCli;
    }

    public int getIdenderecoCli() {
        return idenderecoCli;
    }

    public void setIdenderecoCli(int idenderecoCli) {
        this.idenderecoCli = idenderecoCli;
    }
}
