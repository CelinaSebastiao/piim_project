/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piim.converters;

import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import piim.modelo.Sector;

/**
 *
 * @author Celina Sebastião
 */
@FacesConverter("sectorConverter")
public class SectorConverter implements Converter {

    private static Map<String, Sector> map = new HashMap<String, Sector>();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return map.get(value);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Sector) {
            Sector m = (Sector) value;
            map.put(String.valueOf(m.getIdSector()), m);
            return String.valueOf(m.getIdSector());
        } else {
            return "";
        }

    }

}
