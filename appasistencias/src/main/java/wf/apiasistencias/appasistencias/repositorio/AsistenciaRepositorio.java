package wf.apiasistencias.appasistencias.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wf.apiasistencias.appasistencias.modelo.Asistencias;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepositorio extends JpaRepository<Asistencias, Integer> {

    boolean existsByMasterClases_IdMasterClasesAndFecha(Integer idMaster, LocalDate fecha);

    @Query(value = """
    SELECT
      @rownum := @rownum + 1 AS numero_lista,
      sub.matricula,
      sub.nombre_completo,
      sub.asistencia,
      sub.id_master_clases
    FROM (
      SELECT
        al.matricula,
        CONCAT(al.primer_apellido, ' ', al.segundo_apellido, ' ', al.nombre) AS nombre_completo,
        CASE 
          WHEN asi.id_asistencia IS NOT NULL THEN TRUE
          ELSE FALSE
        END AS asistencia,
        mc.id_master_clases
      FROM master_clases mc
      INNER JOIN alumno al ON mc.id_alumno = al.id_alumno
      LEFT JOIN asistencias asi 
        ON asi.id_master = mc.id_master_clases
        AND DATE(asi.fecha) = :fecha
      WHERE mc.id_clases = :idClase
      ORDER BY nombre_completo ASC
    ) sub
    JOIN (SELECT @rownum := 0) r;
    """, nativeQuery = true)
    List<Object[]> DevolverLista(@Param("idClase") Integer idClase, @Param("fecha") LocalDate fecha);

}

