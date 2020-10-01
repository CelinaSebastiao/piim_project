/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piim.controlo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import piim.dao.SectorDAO;
import piim.modelo.Municipio;
import piim.modelo.Sector;

/**
 *
 * @author Celina Sebastião
 */
@Named(value = "sectorBean")
@RequestScoped
public class SectorBean {

    /**
     * Creates a new instance of SectorBean
     */
    SectorDAO dao = new SectorDAO();
    Sector sector = new Sector();
    List<Sector> sectores;
  
   @PostConstruct
    public void inicizalizar() {
    
        sectores = new ArrayList<>();
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }
    
     public List<SelectItem> getSelectSectores() {
        List<SelectItem> lista = new ArrayList<>();
        for (Sector m : dao.listaSectores()) {
            lista.add(new SelectItem(m, m.getNomeSector()));
        }
        return lista;
    }
    
    
}
