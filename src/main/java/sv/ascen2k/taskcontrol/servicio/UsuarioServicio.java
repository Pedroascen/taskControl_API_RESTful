package sv.ascen2k.taskcontrol.servicio;

import sv.ascen2k.taskcontrol.modelo.Usuario;

import java.util.List;

public interface UsuarioServicio {
    Usuario saveUsuario(Usuario usuario);
    List<Usuario> getAllUsuarios();
    List<Usuario> getUsuariosActivosNoActivos(Boolean activo);
    Usuario getUsuarioById(Integer id);
}
