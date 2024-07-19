package sv.ascen2k.taskcontrol.servicio;

import sv.ascen2k.taskcontrol.modelo.Tarea;

import java.util.List;

public interface TareaServicio {
    Tarea saveTarea(Tarea tarea);
    List<Tarea> getAllTareas();
    List<Tarea> getTareasActivasNoActivas(Boolean activo);
    Tarea getTareaById(Integer id);
}
