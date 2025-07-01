package wf.apiasistencias.appasistencias.DTO;

public class AlumnoAsistenciaDTO {
    private int numeroLista;
    private String nombre;
    private boolean asistencia;
    private String matricula;
    private Integer idMasterClases;

    public AlumnoAsistenciaDTO(int numeroLista, String nombre, String matricula, boolean asistencia, Integer idMasterClases) {
        this.numeroLista = numeroLista;
        this.nombre = nombre;
        this.matricula = matricula;
        this.asistencia = asistencia;
        this.idMasterClases = idMasterClases;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(int numeroLista) {
        this.numeroLista = numeroLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getIdMasterClases() {
        return idMasterClases;
    }

    public void setIdMasterClases(Integer matricula) {
        this.idMasterClases = idMasterClases;
    }
}
