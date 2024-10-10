package sv.ascen2k.taskcontrol.servicio.implementacion;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sv.ascen2k.taskcontrol.modelo.Tarea;
import sv.ascen2k.taskcontrol.repositorio.TareaRepositorio;
import sv.ascen2k.taskcontrol.servicio.TareaServicio;

import java.util.List;

@Service
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
        return tareaRepositorio.findByEsVigente(true);
    }

    @Override
    public List<Tarea> getTareasVigentes(Boolean vigente) {
        return tareaRepositorio.findByEsVigente(vigente);
    }

    @Override
    public List<Tarea> getTareasByUsuarios(Integer id) {
        return tareaRepositorio.findByUsuarioId(id);
    }

    @Override
    public Tarea getTareaById(Integer id) {
        return tareaRepositorio.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Tarea getTareaByIdAndEsVigencia(Integer id, Boolean vigencia) {
        return tareaRepositorio.findByIdAndEsVigente(id,vigencia).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Tarea> getPeageableTareas(Pageable pageable) {
        return tareaRepositorio.findAll(pageable);
    }
}
