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
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idMateria;

    String nombre;
    String folio;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Clase> clases;
}
