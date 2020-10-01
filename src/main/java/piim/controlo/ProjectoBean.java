/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piim.controlo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import piim.dao.ProjectoDAO;
import piim.modelo.Municipio;
import piim.modelo.Projecto;
import piim.modelo.Provincia;
import piim.modelo.Sector;

/**
 *
 * @author Celina Sebastião
 */
@Named(value = "projectoBean")
@RequestScoped

public class ProjectoBean implements Serializable {

    /**
     * Creates a new instance of ProjectoBean
     */
    Projecto projecto;

    ProjectoDAO projectoDAO = new ProjectoDAO();
    List<Projecto> projectos;

    List<Projecto> municipioComLetras;
    List<Projecto> provinciaComLetras;
    List<Projecto> sectorComLetras;

    String letrasNomeDaProvincia;
    String letrasNomeMunicipio;
    String letrasSector;

    @PostConstruct
    public void inicializar() {

        projectos = new ArrayList<>();
        projectos = projectoDAO.listaTodosProjectos();
        projecto = new Projecto();
    }

    public Projecto getProjecto() {
        return projecto;
    }

    public void setProjecto(Projecto projecto) {
        this.projecto = projecto;
    }

    public List<Projecto> getProjectos() {
        return projectos;
    }

    public void setProjectos(List<Projecto> projectos) {
        this.projectos = projectos;
    }

    public String getLetrasNomeDaProvincia() {
        return letrasNomeDaProvincia;
    }

    public void setLetrasNomeDaProvincia(String letrasNomeDaProvincia) {
        this.letrasNomeDaProvincia = letrasNomeDaProvincia;
    }

    public String getLetrasNomeMunicipio() {
        return letrasNomeMunicipio;
    }

    public void setLetrasNomeMunicipio(String letrasNomeMunicipio) {
        this.letrasNomeMunicipio = letrasNomeMunicipio;
    }

    public String getLetrasSector() {
        return letrasSector;
    }

    public void setLetrasSector(String letrasSector) {
        this.letrasSector = letrasSector;
    }

    public String guardar() {

        projectoDAO.save(projecto);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String operacao = "Guardar";

        FacesMessage facesMessage
                = new FacesMessage(operacao, "Projecto Guardado com sucesso" + " ");

        facesContext.addMessage(null, facesMessage);

        return "novo-projecto";
    }

    public String editar() {
        projectoDAO.edit(projecto);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String operacao = "Editar";

        FacesMessage facesMessage
                = new FacesMessage(operacao, " Dados do Projecto editados com sucesso" + " ");

        facesContext.addMessage(null, facesMessage);
        projecto = new Projecto();
        return "novo-projecto";
    }

    public String prepararEditar() {

        return "editar_projecto";
    }

    public String eliminar() {
        projectoDAO.delete(projecto);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String operacao = "Eliminar";

        FacesMessage facesMessage
                = new FacesMessage(operacao, " Dados do Projecto eliminados com sucesso" + " ");

        facesContext.addMessage(null, facesMessage);

        return "novo-projecto";
    }

    public List<Projecto> getMunicipiosPorLetra() {
        municipioComLetras = new ArrayList<>();
        municipioComLetras = projectoDAO.listaProjectosByMunicipio(letrasNomeMunicipio);

        return municipioComLetras;
    }
    
    public List<Projecto> getProvinciasPorLetra() {
        provinciaComLetras = new ArrayList<>();
        provinciaComLetras = projectoDAO.listaProjectosByProvincia(letrasNomeDaProvincia);

        return provinciaComLetras;
    }
    
     public List<Projecto> getSectoresPorLetra() {
        sectorComLetras = new ArrayList<>();
        sectorComLetras = projectoDAO.listaProjectosBySector(letrasSector);

        return sectorComLetras;
    }


}
