package wf.apiasistencias.appasistencias.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wf.apiasistencias.appasistencias.modelo.Contrasenia;

@Repository
public interface ContraseniaRepositorio extends JpaRepository<Contrasenia, Integer> {
}

