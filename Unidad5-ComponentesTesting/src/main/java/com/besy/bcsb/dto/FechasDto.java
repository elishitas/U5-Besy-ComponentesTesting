package com.besy.bcsb.dto;

public class FechasDto {
    private String desde;
    private String hasta;

    public FechasDto() {}

    public FechasDto(String desde, String hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }
}

