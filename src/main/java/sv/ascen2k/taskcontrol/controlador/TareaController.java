package sv.ascen2k.taskcontrol.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.ascen2k.taskcontrol.modelo.Tarea;
import sv.ascen2k.taskcontrol.repositorio.TareaRepositorio;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaRepositorio tareaRepositorio;

    @GetMapping
    public List<Tarea> tareas(){
        return tareaRepositorio.findAll();
    }
    @GetMapping("/usuario/{id}")
    public List<Tarea> tareasByUser(@PathVariable Integer id){
        return tareaRepositorio.findByUsuarioId(id);
    }

    /*@GetMapping("/usuario/{id}")
    public ResponseEntity<List<Tarea>> getTareasByUsuarioId(@PathVariable Integer id) {
        return new ResponseEntity<>(tareaRepositorio.findByUsuarioId(id), HttpStatus.OK);
    }*/
}
