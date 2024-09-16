package co.edu.uniquindio.tiqueteo.Services.Implementation;

import co.edu.uniquindio.tiqueteo.Dto.LocalityDto;
import co.edu.uniquindio.tiqueteo.Model.Locality;
import co.edu.uniquindio.tiqueteo.Services.iLocalityService;
import org.springframework.stereotype.Service;

@Service
public class LocalityServiceImplementation implements iLocalityService {
    @Override
    public Locality createLocality(LocalityDto locality) {
        return null;
    }

    @Override
    public Locality updateLocality(LocalityDto locality) {
        return null;
    }

    @Override
    public void deleteLocality(Long id) {

    }

    @Override
    public Locality getLocalityById(Long id) {
        return null;
    }
}
