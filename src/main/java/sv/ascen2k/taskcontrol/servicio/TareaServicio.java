package sv.ascen2k.taskcontrol.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.ascen2k.taskcontrol.modelo.Tarea;
import java.util.List;

public interface TareaServicio {
    Tarea saveTarea(Tarea tarea);
    List<Tarea> getAllTareas();
    List<Tarea> getTareasVigentes(Boolean vigente);
    List<Tarea> getTareasByUsuarios(Integer id);
    Tarea getTareaById(Integer id);
    Page<Tarea> getPeageableTareas(Pageable pageable);
}
