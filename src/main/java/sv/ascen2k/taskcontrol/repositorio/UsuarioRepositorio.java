package sv.ascen2k.taskcontrol.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.ascen2k.taskcontrol.modelo.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    List<Usuario> findByEsVigente(Boolean activo);
    Optional<Usuario> findByIdAndEsVigente(Integer id, Boolean vigente);
}
