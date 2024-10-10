package sv.ascen2k.taskcontrol.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
public class Tarea {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descripcion;
    @Column(name = "fecha_crea")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_estimada")
    private LocalDate fechaEstimada;
    @Column(name = "fecha_asigna")
    private LocalDateTime fechaAsignacion;
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(name = "vigente")
    private Boolean esVigente;//mejorar Variable
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "responsable_id",referencedColumnName = "id")
    private Usuario usuario;

    public enum Prioridad{
        NORMAL,
        IMPORTANTE,
        URGENTE
    }
    public enum Estado{
        CREADO,
        ASIGNADO,
        FINALIZADO
    }
}
