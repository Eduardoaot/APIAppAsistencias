package wf.apiasistencias.appasistencias.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wf.apiasistencias.appasistencias.DTO.AlumnoAsistenciaDTO;
import wf.apiasistencias.appasistencias.modelo.Asistencias;
import wf.apiasistencias.appasistencias.modelo.MasterClases;
import wf.apiasistencias.appasistencias.repositorio.MasterClasesRepositorio;
import wf.apiasistencias.appasistencias.repositorio.AsistenciaRepositorio;
import wf.apiasistencias.appasistencias.response.RespuestaDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListaAsistenciaServicio {

    @Autowired
    private MasterClasesRepositorio masterClasesRepositorio;

    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    public List<AlumnoAsistenciaDTO> obtenerListaAsistencia(Integer idClase, LocalDate fecha) {
        List<Object[]> resultados = asistenciaRepositorio.DevolverLista(idClase, fecha);
        List<AlumnoAsistenciaDTO> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            int numeroLista = ((Number) fila[0]).intValue();
            String matricula = (String) fila[1];
            String nombre = (String) fila[2];

            Object asistenciaObj = fila[3];
            boolean asistencia = false;
            if (asistenciaObj instanceof Boolean) {
                asistencia = (Boolean) asistenciaObj;
            } else if (asistenciaObj instanceof Number) {
                asistencia = ((Number) asistenciaObj).intValue() != 0;
            }

            Integer idMasterClases = (Integer) fila[4];

            AlumnoAsistenciaDTO dto = new AlumnoAsistenciaDTO(numeroLista, matricula, nombre, asistencia, idMasterClases);
            lista.add(dto);
        }

        return lista;
    }

    public RespuestaDTO guardarAsistencia(Integer idMaster) throws Exception {
        LocalDate hoy = LocalDate.now();

        // Verificar si ya existe una asistencia para este idMaster y fecha
        boolean existe = asistenciaRepositorio.existsByMasterClases_IdMasterClasesAndFecha(idMaster, hoy);

        if (existe) {
            return new RespuestaDTO(false, "Su usuario ya está registrado");
        }

        MasterClases mc = masterClasesRepositorio.findById(idMaster)
                .orElseThrow(() -> new Exception("MasterClase no encontrada con id: " + idMaster));

        Asistencias asistencia = new Asistencias();
        asistencia.setMasterClases(mc);
        asistencia.setFecha(hoy);

        asistenciaRepositorio.save(asistencia);

        return new RespuestaDTO(true, "El alumno ha sido ingresado correctamente");
    }
}
