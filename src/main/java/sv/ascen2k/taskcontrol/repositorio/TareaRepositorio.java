package sv.ascen2k.taskcontrol.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.ascen2k.taskcontrol.modelo.Tarea;

public interface TareaRepositorio extends JpaRepository<Tarea,Integer> {
}
