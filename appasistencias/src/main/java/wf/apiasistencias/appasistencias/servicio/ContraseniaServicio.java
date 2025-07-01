package wf.apiasistencias.appasistencias.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wf.apiasistencias.appasistencias.modelo.Contrasenia;
import wf.apiasistencias.appasistencias.repositorio.ContraseniaRepositorio;

import java.util.List;
import java.util.Optional;

@Service
public class ContraseniaServicio {

    @Autowired
    private ContraseniaRepositorio contraseniaRepositorio;

    public List<Contrasenia> obtenerTodasLasContrasenias() {
        return contraseniaRepositorio.findAll();
    }
}


