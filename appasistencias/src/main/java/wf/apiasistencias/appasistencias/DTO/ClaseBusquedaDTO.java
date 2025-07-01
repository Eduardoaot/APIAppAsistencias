package wf.apiasistencias.appasistencias.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClaseBusquedaDTO {
    private Integer id;
    private String grupo;
    private String dia;
    private String hora;
    private String materia;
    private String folio;
}

