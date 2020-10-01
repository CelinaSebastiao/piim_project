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
import piim.modelo.Sector;
import piim.util.ConexaoDB;

/**
 *
 * @author Celina Sebastião
 */
public class SectorDAO {
    
     String SELECT_ALL = "SELECT id_sector, nome_sector FROM sector";
    
    public List<Sector> listaSectores() {
        List<Sector> lista = new ArrayList<>();
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = ConexaoDB.ligarBD();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sector p = new Sector();
                p.setIdSector(rs.getInt("id_sector"));
                p.setNomeSector(rs.getString("nome_sector"));
                
                lista.add(p);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados:"
                    + "SectorDAO:"
                    + "listaSectores" + ex.getLocalizedMessage());
        }

        return lista;
    }

    
}
