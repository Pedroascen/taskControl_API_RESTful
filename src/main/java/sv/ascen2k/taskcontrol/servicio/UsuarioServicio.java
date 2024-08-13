package sv.ascen2k.taskcontrol.servicio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.ascen2k.taskcontrol.modelo.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    List<Usuario> getUsuariosActivosNoActivos(Boolean activo);
    Usuario getUsuarioById(Integer id);
    Page<Usuario> getPeageableUsuarios(Pageable pageable);
}
