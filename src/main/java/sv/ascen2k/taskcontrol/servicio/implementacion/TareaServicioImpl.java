package sv.ascen2k.taskcontrol.servicio.implementacion;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import sv.ascen2k.taskcontrol.modelo.Tarea;
import sv.ascen2k.taskcontrol.repositorio.TareaRepositorio;
import sv.ascen2k.taskcontrol.servicio.TareaServicio;

import java.util.List;

public class TareaServicioImpl implements TareaServicio {

    private final TareaRepositorio tareaRepositorio;

    @Autowired
    public TareaServicioImpl(TareaRepositorio tareaRepositorio){
        this.tareaRepositorio = tareaRepositorio;
    }

    @Override
    public Tarea saveTarea(Tarea tarea) {
        return tareaRepositorio.save(tarea);
    }

    @Override
    public List<Tarea> getAllTareas() {
        return tareaRepositorio.findAll();
    }

    @Override
    public List<Tarea> getTareasActivasNoActivas(Boolean activo) {
        return tareaRepositorio.findByActivo(activo);
    }

    @Override
    public Tarea getTareaById(Integer id) {
        return tareaRepositorio.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
