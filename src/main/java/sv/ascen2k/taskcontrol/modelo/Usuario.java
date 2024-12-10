package sv.ascen2k.taskcontrol.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 4, max = 25)
    private String nombre;
    @Email
    @Size(min = 10, max = 65)
    private String correo;
    @NotBlank
    @Size(min = 8, max = 255)
    private String clave;
    @Column(name = "fecha_crea")
    private LocalDateTime fechaCreacion;
    @Column(name = "vigente")
    private Boolean esVigente;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarea> tareas = new ArrayList<>();
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;
     public enum Rol{
        ADMIN,
        NORMAL
    }
}
