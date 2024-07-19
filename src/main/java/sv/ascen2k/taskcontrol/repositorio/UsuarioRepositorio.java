package sv.ascen2k.taskcontrol.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.ascen2k.taskcontrol.modelo.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    List<Usuario> findByActivo(Boolean activo);
}
