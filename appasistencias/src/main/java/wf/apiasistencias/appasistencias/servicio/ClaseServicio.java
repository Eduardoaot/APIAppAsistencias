package wf.apiasistencias.appasistencias.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wf.apiasistencias.appasistencias.DTO.ClaseDTO;
import wf.apiasistencias.appasistencias.modelo.Clase;
import wf.apiasistencias.appasistencias.repositorio.ClaseRepositorio;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaseServicio {

    @Autowired
    private ClaseRepositorio claseRepositorio;

    public List<ClaseDTO> obtenerTodasLasClases() {
        List<Clase> clases = claseRepositorio.findAll();
        return clases.stream()
                .map(clase -> new ClaseDTO(
                        clase.getIdClase(),
                        clase.getGrupo(),
                        clase.getDia().getDescripcion(),
                        clase.getHora().getDescripcion(),
                        clase.getMateria().getNombre(),
                        clase.getMateria().getFolio()
                ))
                .collect(Collectors.toList());
    }

    public ClaseDTO obtenerClasePorId(Integer idClase) {
        Clase clase = claseRepositorio.findById(idClase)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada con id: " + idClase));

        return new ClaseDTO(
                clase.getIdClase(),
                clase.getGrupo(),
                clase.getDia().getDescripcion(),
                clase.getHora().getDescripcion(),
                clase.getMateria().getNombre(),
                clase.getMateria().getFolio()
        );
    }
}

