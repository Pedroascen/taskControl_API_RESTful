package sv.ascen2k.taskcontrol.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sv.ascen2k.taskcontrol.modelo.Tarea;
import sv.ascen2k.taskcontrol.modelo.Usuario;
import sv.ascen2k.taskcontrol.servicio.TareaServicio;
import sv.ascen2k.taskcontrol.servicio.UsuarioServicio;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaServicio tareaServicio;
    private final UsuarioServicio usuarioServicio;

    @Autowired
    public TareaController(TareaServicio tareaServicio, UsuarioServicio usuarioServicio) {
        this.tareaServicio = tareaServicio;
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    public List<Tarea> obtenerTodas(){
        return tareaServicio.getAllTareas();
    }

    @GetMapping("/paginar")
    Page<Tarea> paginar(@PageableDefault(size = 5, sort = "nombre", direction = Sort.Direction.ASC) Pageable pageable){
        return tareaServicio.getPeageableTareas(pageable);
    }

    @GetMapping("/{id}")
    public Tarea obtenerTarea(@PathVariable Integer id){
        return tareaServicio.getTareaById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea){
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setFechaEstimada(tarea.getFechaEstimada());
        tarea.setEstado(Tarea.Estado.CREADO);
        tarea.setEsVigente(true);
        return tareaServicio.saveTarea(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Integer id, @RequestBody Tarea form){
        Tarea tarea = tareaServicio.getTareaById(id);
        tarea.setTitulo(form.getTitulo());
        tarea.setDescripcion(form.getDescripcion());
        tarea.setFechaEstimada(form.getFechaEstimada());
        tarea.setPrioridad(form.getPrioridad());
        return tareaServicio.saveTarea(tarea);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        Tarea tarea = tareaServicio.getTareaById(id);
        if(tarea != null){
            tarea.setEsVigente(false);
            tareaServicio.saveTarea(tarea);
        }
    }

    @PutMapping("/{idTarea}/asignarresponsable/{idUsuario}")
    public Tarea asignarResponsable(@PathVariable Integer idTarea,@PathVariable Integer idUsuario){
        Usuario usuario = usuarioServicio.getUsuarioByIdAndEsVigente(idUsuario,true);
        Tarea tarea = tareaServicio.getTareaByIdAndEsVigencia(idTarea,true);
        if(usuario != null && tarea != null){
            tarea.setUsuario(usuario);
            tarea.setEstado(Tarea.Estado.ASIGNADO);
            tarea.setFechaAsignacion(LocalDateTime.now());
            tareaServicio.saveTarea(tarea);
        }
        return tarea;
    }

    @GetMapping("/usuario/{id}")
    public List<Tarea> porUsuario(@PathVariable Integer id){
        return tareaServicio.getTareasByUsuarios(id);
    }

    /*@GetMapping("/usuario/{id}")
    public ResponseEntity<List<Tarea>> getTareasByUsuarioId(@PathVariable Integer id) {
        return new ResponseEntity<>(tareaRepositorio.findByUsuarioId(id), HttpStatus.OK);
    }*/

    /*@GetMapping("/activas={vigente}")
    public List<Tarea> vigentes(@PathVariable Boolean vigente){
        return tareaServicio.getTareasVigentes(vigente);
    }*/
}
