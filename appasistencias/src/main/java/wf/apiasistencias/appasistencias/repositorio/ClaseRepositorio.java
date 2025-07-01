package wf.apiasistencias.appasistencias.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wf.apiasistencias.appasistencias.modelo.Clase;

import java.util.List;

@Repository
public interface ClaseRepositorio extends JpaRepository<Clase, Integer> {
    @Query("SELECT c FROM Clase c " +
            "JOIN c.dia d " +
            "JOIN c.materia m " +
            "WHERE LOWER(c.grupo) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(c.hora.Descripcion) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(d.Descripcion) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(m.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) " +
            "OR LOWER(m.folio) LIKE LOWER(CONCAT('%', :texto, '%')) ")
    List<Clase> buscarPorTexto(@Param("texto") String texto);


}

