package sv.ascen2k.taskcontrol.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.ascen2k.taskcontrol.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
}
