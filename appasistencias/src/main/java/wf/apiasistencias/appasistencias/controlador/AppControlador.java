package wf.apiasistencias.appasistencias.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wf.apiasistencias.appasistencias.DTO.*;
import wf.apiasistencias.appasistencias.modelo.Asistencias;
import wf.apiasistencias.appasistencias.modelo.Clase;
import wf.apiasistencias.appasistencias.modelo.Contrasenia;
import wf.apiasistencias.appasistencias.modelo.MasterClases;
import wf.apiasistencias.appasistencias.repositorio.AsistenciaRepositorio;
import wf.apiasistencias.appasistencias.repositorio.ClaseRepositorio;
import wf.apiasistencias.appasistencias.repositorio.MasterClasesRepositorio;
import wf.apiasistencias.appasistencias.response.RespuestaDTO;
import wf.apiasistencias.appasistencias.response.RespuestaGenerica;
import wf.apiasistencias.appasistencias.servicio.ClaseServicio;
import wf.apiasistencias.appasistencias.servicio.ContraseniaServicio;
import wf.apiasistencias.appasistencias.servicio.ListaAsistenciaServicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AppControlador {
    @Autowired
    private ClaseServicio claseServicio;

    @Autowired
    private ListaAsistenciaServicio listaAsistenciaServicio;

    @Autowired
    private ContraseniaServicio contraseniaServicio;

    @Autowired
    private MasterClasesRepositorio masterClasesRepositorio;

    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    @Autowired
    private ClaseRepositorio claseRepositorio;


    @GetMapping("/MostrarClases")
    public ResponseEntity<RespuestaGenerica<List<ClaseDTO>>> obtenerTodasLasClases() {
        try {
            List<ClaseDTO> clases = claseServicio.obtenerTodasLasClases();
            return ResponseEntity.ok(new RespuestaGenerica<>(true, clases));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespuestaGenerica<>(false, null));
        }
    }

    @GetMapping("/MostrarListaAsistencia/{idClase}/{fecha}")
    public ResponseEntity<RespuestaGenerica<ListaAsistenciaDTO>> mostrarListaAsistencia(
            @PathVariable Integer idClase,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        try {
            ClaseDTO clase = claseServicio.obtenerClasePorId(idClase);
            List<AlumnoAsistenciaDTO> alumnos = listaAsistenciaServicio.obtenerListaAsistencia(idClase, fecha);

            ListaAsistenciaDTO respuesta = new ListaAsistenciaDTO(clase, alumnos);

            return ResponseEntity.ok(new RespuestaGenerica<>(true, respuesta));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespuestaGenerica<>(false, null));
        }
    }

    @GetMapping("/contraseniaSemanal")
    public ResponseEntity<List<String>> obtenerTodasLasContrasenias() {
        List<Contrasenia> contrasenias = contraseniaServicio.obtenerTodasLasContrasenias();

        // Extraemos solo las contraseñas como String
        List<String> contrasenasStr = contrasenias.stream()
                .map(Contrasenia::getContrasenia)
                .collect(Collectors.toList());

        return ResponseEntity.ok(contrasenasStr);
    }

    @PostMapping("/ActualizarYVerificarAsistencias")
    public ResponseEntity<?> agregarVariasAsistencias(@RequestBody AsistenciasListaDTO payload) {
        List<String> errores = new ArrayList<>();
        int registrados = 0;

        for (Integer idMaster : payload.getIdMasterList()) {
            try {
                boolean yaRegistrado = asistenciaRepositorio.existsByMasterClases_IdMasterClasesAndFecha(
                        idMaster, LocalDate.now());

                if (yaRegistrado) {
                    errores.add("El alumno con idMaster " + idMaster + " ya está registrado.");
                } else {
                    Asistencias asistencia = new Asistencias();
                    asistencia.setFecha(LocalDate.now());

                    MasterClases master = masterClasesRepositorio.findById(idMaster)
                            .orElseThrow(() -> new RuntimeException("No se encontró master con ID " + idMaster));

                    asistencia.setMasterClases(master);
                    asistenciaRepositorio.save(asistencia);
                    registrados++;
                }
            } catch (Exception e) {
                errores.add("Error con idMaster " + idMaster + ": " + e.getMessage());
            }
        }

        String mensajeFinal;
        if (registrados == 0) {
            mensajeFinal = "No se pudo registrar ningún alumno.";
        } else if (registrados == 1) {
            mensajeFinal = "Se ha registrado el alumno correctamente.";
        } else {
            mensajeFinal = "Se han registrado todos los alumnos correctamente.";
        }

        return ResponseEntity.ok(Map.of(
                "mensaje", mensajeFinal,
                "registrados", registrados,
                "errores", errores
        ));
    }

    @GetMapping("/buscarClases")
    public ResponseEntity<Map<String, Object>> buscarClases(@RequestParam String texto) {
        List<Clase> clases = claseRepositorio.buscarPorTexto(texto);

        if (clases.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "success", false,
                    "message", "No se encontraron resultados para: " + texto
            ));
        }

        List<ClaseBusquedaDTO> resultados = clases.stream()
                .map(c -> new ClaseBusquedaDTO(
                        c.getIdClase(),
                        c.getGrupo(),
                        c.getDia().getDescripcion(),
                        c.getHora().getDescripcion(),
                        c.getMateria().getNombre(),
                        c.getMateria().getFolio()
                ))
                .collect(Collectors.toList());


        return ResponseEntity.ok(Map.of(
                "success", true,
                "results", resultados
        ));
    }


}
