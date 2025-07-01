package wf.apiasistencias.appasistencias.DTO;

import java.util.List;

public class ListaAsistenciaDTO {
    private ClaseDTO clase;
    private List<AlumnoAsistenciaDTO> alumnos;

    public ListaAsistenciaDTO() {}

    public ListaAsistenciaDTO(ClaseDTO clase, List<AlumnoAsistenciaDTO> alumnos) {
        this.clase = clase;
        this.alumnos = alumnos;
    }

    public ClaseDTO getClase() {
        return clase;
    }

    public void setClase(ClaseDTO clase) {
        this.clase = clase;
    }

    public List<AlumnoAsistenciaDTO> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<AlumnoAsistenciaDTO> alumnos) {
        this.alumnos = alumnos;
    }
}

