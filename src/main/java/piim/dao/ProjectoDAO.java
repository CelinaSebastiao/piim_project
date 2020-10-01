/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import piim.modelo.Municipio;
import piim.modelo.Projecto;
import piim.modelo.Provincia;
import piim.modelo.Sector;
import piim.util.ConexaoDB;

/**
 *
 * @author Celina Sebastião
 */
public class ProjectoDAO {
    
    String INSERT = "INSERT INTO projecto(nome_projecto,custo_projecto,data_inicio,data_termino,id_sector,id_municipio)VALUES(?,?,?,?,?,?)";
    String UPDATE = "UPDATE projecto SET nome_projecto = ?, custo_projecto = ?, data_inicio = ?, data_termino = ?, id_sector = ?, id_municipio = ? WHERE id_projecto = ?";
    String DELETE = " DELETE FROM projecto WHERE id_projecto = ?";
    String SELECT_ALL = "SELECT id_projecto, nome_projecto, custo_projecto, data_inicio, data_termino,pr.nome_provincia, m.nome_municipio, s.nome_sector FROM projecto p  INNER JOIN municipio m ON p.id_municipio = m.id_municipio INNER JOIN provincia pr ON m.id_provincia = pr.id_provincia INNER JOIN sector s ON  p.id_sector = s.id_sector";
    String SELECT_BY_MUNICIPIO = "SELECT nome_projecto, custo_projecto, data_inicio, data_termino,pr.nome_provincia, m.nome_municipio,  s.nome_sector FROM projecto p  INNER JOIN municipio m ON p.id_municipio = m.id_municipio INNER JOIN provincia pr ON m.id_provincia = pr.id_provincia INNER JOIN sector s ON  p.id_sector = s.id_sector WHERE m.nome_municipio LIKE ?";
    String SELECT_BY_PROVINCIA = "SELECT nome_projecto, custo_projecto, data_inicio, data_termino,pr.nome_provincia, m.nome_municipio,  s.nome_sector FROM projecto p  INNER JOIN municipio m ON p.id_municipio = m.id_municipio INNER JOIN provincia pr ON m.id_provincia = pr.id_provincia INNER JOIN sector s ON  p.id_sector = s.id_sector WHERE pr.nome_provincia LIKE ?";
    String SELECT_BY_SECTOR = "SELECT nome_projecto, custo_projecto, data_inicio, data_termino,pr.nome_provincia, m.nome_municipio,  s.nome_sector FROM projecto p  INNER JOIN municipio m ON p.id_municipio = m.id_municipio INNER JOIN provincia pr ON m.id_provincia = pr.id_provincia INNER JOIN sector s ON  p.id_sector = s.id_sector WHERE s.nome_sector LIKE ?";

     public void save(Projecto p) {
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1,p.getNomeProjecto());
            
            ps.setDouble(2,p.getCustoProjecto());
            ps.setDate(3, new java.sql.Date(p.getDataInicio().getTime()));
            ps.setDate(4, new java.sql.Date(p.getDataTermino().getTime()));
           
            ps.setInt(5, p.getSector().getIdSector());
            ps.setInt(6, p.getMunicipio().getIdMunicipio());
            ps.executeUpdate();
        } catch (SQLException e) {

            System.err.println("Erro ao inserir dados:"
                    + " ProjectoDAO:save" + e.getLocalizedMessage());
        }

    }
    
    public void edit(Projecto p) {
        PreparedStatement ps = null;
        Connection conn = null;
    
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, p.getNomeProjecto());
            ps.setDouble(2, p.getCustoProjecto());
            ps.setDate(3, new java.sql.Date(p.getDataInicio().getTime()));
            ps.setDate(4, new java.sql.Date(p.getDataTermino().getTime()));
            
            ps.setInt(5, p.getSector().getIdSector());
            ps.setInt(6, p.getMunicipio().getIdMunicipio());
            ps.executeUpdate();
        } catch (SQLException e) {

            System.err.println("Erro ao actualizar dados:"
                    + " ProjectoDAO:save" + e.getLocalizedMessage());
        }

    }

      public void delete(Projecto f) {
        PreparedStatement ps = null;
        Connection conn = null;
    
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, f.getIdProjecto());
            ps.executeUpdate();
        } catch (SQLException e) {

            System.err.println("Erro ao eliminar dados:"
                    + "ProjectoDAO:save" + e.getLocalizedMessage());
        }

    }

    public List<Projecto> listaTodosProjectos() {
        List<Projecto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Projecto f = new Projecto();
                f.setIdProjecto(rs.getInt(1));
                f.setNomeProjecto(rs.getString("nome_projecto"));
                f.setCustoProjecto(rs.getDouble("custo_projecto"));
                f.setDataInicio(rs.getDate("data_inicio"));
                f.setDataTermino(rs.getDate("data_termino"));
                
                Provincia p = new Provincia();
                p.setNomeProvincia(rs.getString("nome_provincia"));

                Municipio municipio = new Municipio();
                municipio.setNomeMunicipio(rs.getString("nome_municipio"));
                municipio.setProvincia(p);
                
                f.setMunicipio(municipio);
                Sector sector = new Sector();
                sector.setNomeSector(rs.getString("nome_sector"));
                f.setSector(sector);

                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:"
                    + "ProjectoDAO:"
                    + "listaProjectos" + ex.getLocalizedMessage());
        }

        return lista;
    }

    
     public List<Projecto> listaProjectosByProvincia(String provincia) {
        List<Projecto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(SELECT_BY_PROVINCIA);
            
            ps.setString(1, "%"+provincia+"%");
            
            rs = ps.executeQuery();
            while (rs.next()) {
               Projecto f = new Projecto();
                
                f.setNomeProjecto(rs.getString("nome_projecto"));
                f.setCustoProjecto(rs.getDouble("custo_projecto"));
                f.setDataInicio(rs.getDate("data_inicio"));
                f.setDataTermino(rs.getDate("data_termino"));
                
                Provincia p = new Provincia();
                p.setNomeProvincia(rs.getString("nome_provincia"));

                Municipio municipio = new Municipio();
                municipio.setNomeMunicipio(rs.getString("nome_municipio"));
                municipio.setProvincia(p);
                
                f.setMunicipio(municipio);
                Sector sector = new Sector();
                sector.setNomeSector(rs.getString("nome_sector"));
                f.setSector(sector);

                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:"
                    + "MunicipioDAO:"
                    + "listaMunicipiosByProvincia" + ex.getLocalizedMessage());
        }

        return lista;
    }
    
    
     
      public List<Projecto> listaProjectosByMunicipio(String municipio) {
        List<Projecto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(SELECT_BY_MUNICIPIO);
            
            ps.setString(1, "%"+municipio+"%");
            
            rs = ps.executeQuery();
            while (rs.next()) {
               Projecto f = new Projecto();
             
                f.setNomeProjecto(rs.getString("nome_projecto"));
                f.setCustoProjecto(rs.getDouble("custo_projecto"));
                f.setDataInicio(rs.getDate("data_inicio"));
                f.setDataTermino(rs.getDate("data_termino"));
                
                Provincia p = new Provincia();
                p.setNomeProvincia(rs.getString("nome_provincia"));

                Municipio municipio1 = new Municipio();
                municipio1.setNomeMunicipio(rs.getString("nome_municipio"));
                municipio1.setProvincia(p);
                
                f.setMunicipio(municipio1);
                Sector sector = new Sector();
                sector.setNomeSector(rs.getString("nome_sector"));
                f.setSector(sector);

                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:"
                    + "MunicipioDAO:"
                    + "listaMunicipiosByMunicipio" + ex.getLocalizedMessage());
        }

        return lista;
    }
    
   public List<Projecto> listaProjectosBySector(String s) {
        List<Projecto> lista = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(SELECT_BY_SECTOR);
            
            ps.setString(1, "%"+s+"%");
            
            rs = ps.executeQuery();
            while (rs.next()) {
               Projecto f = new Projecto();
                f.setNomeProjecto(rs.getString("nome_projecto"));
                f.setCustoProjecto(rs.getDouble("custo_projecto"));
                f.setDataInicio(rs.getDate("data_inicio"));
                f.setDataTermino(rs.getDate("data_termino"));
                
                Provincia p = new Provincia();
                p.setNomeProvincia(rs.getString("nome_provincia"));

                Municipio municipio1 = new Municipio();
                municipio1.setNomeMunicipio(rs.getString("nome_municipio"));
                municipio1.setProvincia(p);
                
                f.setMunicipio(municipio1);
                Sector sector = new Sector();
                sector.setNomeSector(rs.getString("nome_sector"));
                f.setSector(sector);

                lista.add(f);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:"
                    + "MunicipioDAO:"
                    + "listaMunicipiosBySector" + ex.getLocalizedMessage());
        }

        return lista;
    }
    
 
  
    
}
