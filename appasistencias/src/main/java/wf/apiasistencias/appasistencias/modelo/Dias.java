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
public class Dias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idDia;
    String Descripcion;

    @OneToMany(mappedBy = "dia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Clase> clases;
}
