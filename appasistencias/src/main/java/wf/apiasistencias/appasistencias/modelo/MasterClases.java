package wf.apiasistencias.appasistencias.modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MasterClases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMasterClases;

    @ManyToOne
    @JoinColumn(name = "idClases", nullable = false)
    Clase clase;

    @ManyToOne
    @JoinColumn(name = "idAlumno", nullable = false)
    Alumno alumno;

    @OneToMany(mappedBy = "masterClases", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Asistencias> asistencias;

}
