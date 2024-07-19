package sv.ascen2k.taskcontrol.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.ascen2k.taskcontrol.modelo.Tarea;

import java.util.List;

@Repository
public interface TareaRepositorio extends JpaRepository<Tarea,Integer> {
    List<Tarea> findByUsuarioId(Integer idResponsable);
    List<Tarea> findByActivo(Boolean activo);
}
