package sv.ascen2k.taskcontrol.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Tarea {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descripcion;
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;
    @Column(name = "fecha_estimada")
    private LocalDate fechaEstimada;
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;
    @Enumerated(EnumType.STRING)
    private Prioridad prioridad;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Boolean vigente;

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

    //gettesr and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(LocalDate fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getVigente() {
        return vigente;
    }
    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }
}
