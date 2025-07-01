package wf.apiasistencias.appasistencias.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wf.apiasistencias.appasistencias.modelo.MasterClases;

@Repository
public interface MasterClasesRepositorio extends JpaRepository<MasterClases, Integer> {
    // Aquí puedes agregar consultas personalizadas si las necesitas
}

