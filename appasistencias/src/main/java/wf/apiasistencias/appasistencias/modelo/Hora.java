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
public class Hora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idHora;
    String Descripcion;

    @OneToMany(mappedBy = "hora", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Clase> clases;
}
