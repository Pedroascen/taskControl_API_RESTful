package sv.ascen2k.taskcontrol.servicio.implementacion;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sv.ascen2k.taskcontrol.modelo.Usuario;
import sv.ascen2k.taskcontrol.repositorio.UsuarioRepositorio;
import sv.ascen2k.taskcontrol.servicio.UsuarioServicio;

import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public List<Usuario> getUsuariosVigentes(Boolean activo) {
        return usuarioRepositorio.findByEsVigente(activo);
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return usuarioRepositorio.findById(id).orElseThrow(()->new EntityNotFoundException("Usuario no encontrado..."));
    }

    @Override
    public Usuario getUsuarioByIdAndEsVigente(Integer id, Boolean vigencia) {
        return usuarioRepositorio.findByIdAndEsVigente(id,vigencia).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<Usuario> getPeageableUsuarios(Pageable pageable) {
        return usuarioRepositorio.findAll(pageable);
    }
}
