/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piim.modelo;

import java.util.Objects;

/**
 *
 * @author Celina Sebastião
 */
public class Sector {
    
    private Integer idSector;
    private String nomeSector;
    
    public Sector(){
    
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        this.idSector = idSector;
    }

    public String getNomeSector() {
        return nomeSector;
    }

    public void setNomeSector(String nomeSector) {
        this.nomeSector = nomeSector;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.idSector);
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
        final Sector other = (Sector) obj;
        if (!Objects.equals(this.idSector, other.idSector)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sector{" + "idSector=" + idSector + ", nomeSector=" + nomeSector + '}';
    }
    
    
}
