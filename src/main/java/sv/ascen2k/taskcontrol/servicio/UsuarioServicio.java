package sv.ascen2k.taskcontrol.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.ascen2k.taskcontrol.modelo.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    List<Usuario> getUsuariosVigentes(Boolean activo);
    Usuario getUsuarioById(Integer id);
    Usuario getUsuarioByIdAndEsVigente(Integer id, Boolean vigencia);
    boolean existsByCorreo(String email);
    boolean existsByCorreoAndIdNot(String email,Integer id);
    Page<Usuario> getPeageableUsuarios(Pageable pageable);
}
