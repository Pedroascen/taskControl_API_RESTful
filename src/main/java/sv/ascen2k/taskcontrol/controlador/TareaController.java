package sv.ascen2k.taskcontrol.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.ascen2k.taskcontrol.modelo.Tarea;
import sv.ascen2k.taskcontrol.modelo.Usuario;
import sv.ascen2k.taskcontrol.repositorio.TareaRepositorio;
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

    @GetMapping("/usuario/{id}")
    public List<Tarea> porUsuario(@PathVariable Integer id){
        return tareaServicio.getTareasByUsuarios(id);
    }

    /*@GetMapping("/usuario/{id}")
    public ResponseEntity<List<Tarea>> getTareasByUsuarioId(@PathVariable Integer id) {
        return new ResponseEntity<>(tareaRepositorio.findByUsuarioId(id), HttpStatus.OK);
    }*/

    @GetMapping("/activas={vigente}")
    public List<Tarea> vigentes(@PathVariable Boolean vigente){
        return tareaServicio.getTareasVigentes(vigente);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea){
        Usuario usuario = usuarioServicio.getUsuarioById(tarea.getUsuario().getId());
        tarea.setUsuario(usuario);
        tarea.setFechaInicio(LocalDateTime.now());
        tarea.setFechaEstimada(tarea.getFechaEstimada());
        tarea.setEstado(Tarea.Estado.CREADO);
        tarea.setVigente(true);
        return tareaServicio.saveTarea(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable Integer id, @RequestBody Tarea form){
        Tarea tarea = tareaServicio.getTareaById(id);
        tarea.setTitulo(form.getTitulo());
        tarea.setDescripcion(form.getDescripcion());
        tarea.setFechaEstimada(form.getFechaEstimada());
        tarea.setPrioridad(form.getPrioridad());
        tarea.setEstado(form.getEstado());
        if(form.getEstado()==Tarea.Estado.FINALIZADO){
            tarea.setFechaFin(LocalDateTime.now());
        }
        return tareaServicio.saveTarea(tarea);
    }
}
