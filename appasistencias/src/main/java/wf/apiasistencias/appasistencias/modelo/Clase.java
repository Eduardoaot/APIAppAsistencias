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
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idClase;

    String grupo;

    @ManyToOne
    @JoinColumn(name = "idHora", nullable = false)
    Hora hora;

    @ManyToOne
    @JoinColumn(name = "idDia", nullable = false)
    Dias dia;

    @ManyToOne
    @JoinColumn(name = "idMateria", nullable = false)
    Materia materia;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MasterClases> MasterClases;
}
