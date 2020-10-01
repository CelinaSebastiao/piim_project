/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piim.modelo;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Celina Sebastião
 */
public class Projecto {
    
    private Integer idProjecto;
    private String nomeProjecto; 
    private double custoProjecto;
    private Date dataInicio;
    private Date dataTermino;
    private Sector sector;
    private Municipio municipio;

    public Projecto() {
    }

    public Integer getIdProjecto() {
        return idProjecto;
    }

    public void setIdProjecto(Integer idProjecto) {
        this.idProjecto = idProjecto;
    }

    public String getNomeProjecto() {
        return nomeProjecto;
    }

    public void setNomeProjecto(String nomeProjecto) {
        this.nomeProjecto = nomeProjecto;
    }

    public double getCustoProjecto() {
        return custoProjecto;
    }

    public void setCustoProjecto(double custoProjecto) {
        this.custoProjecto = custoProjecto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    
    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.idProjecto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Projecto other = (Projecto) obj;
        if (!Objects.equals(this.idProjecto, other.idProjecto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Projecto{" + "idProjecto=" + idProjecto + ", nomeProjecto=" + nomeProjecto + '}';
    }
    
    
    
    
}
