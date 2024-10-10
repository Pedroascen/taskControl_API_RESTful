package sv.ascen2k.taskcontrol.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Usuario {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String correo;
    private String clave;
    @Column(name = "fecha_crea")
    private LocalDateTime fechaCreacion;
    @Column(name = "vigente")
    private Boolean esVigente;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarea> tareas = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Rol rol;
     public enum Rol{
        ADMIN,
        NORMAL
    }
}
