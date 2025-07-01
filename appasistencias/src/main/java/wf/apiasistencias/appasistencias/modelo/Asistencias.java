package wf.apiasistencias.appasistencias.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Asistencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idAsistencia;

    private LocalDate fecha;


    @ManyToOne
    @JoinColumn(name = "idMaster", nullable = false)
    MasterClases masterClases;
}
