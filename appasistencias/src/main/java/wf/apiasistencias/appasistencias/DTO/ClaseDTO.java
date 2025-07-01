package wf.apiasistencias.appasistencias.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ClaseDTO {
    private Integer id;
    private String grupo;
    private String dia;
    private String hora;
    private String materia;
    private String folio;

    // Constructor
    public ClaseDTO(Integer id, String grupo, String dia, String hora, String materia, String folio) {
        this.id = id;
        this.grupo = grupo;
        this.dia = dia;
        this.hora = hora;
        this.materia = materia;
        this.folio = folio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}


