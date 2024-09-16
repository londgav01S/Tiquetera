package co.edu.uniquindio.tiqueteo.Services;

import co.edu.uniquindio.tiqueteo.Dto.LocalityDto;
import co.edu.uniquindio.tiqueteo.Model.Locality;

public interface iLocalityService {
    public Locality createLocality(LocalityDto locality);
    public Locality updateLocality(LocalityDto locality);
    public void deleteLocality(Long id);
    public Locality getLocalityById(Long id);

}
