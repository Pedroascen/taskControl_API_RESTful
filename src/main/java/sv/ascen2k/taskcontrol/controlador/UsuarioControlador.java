package sv.ascen2k.taskcontrol.controlador;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sv.ascen2k.taskcontrol.modelo.Usuario;
import sv.ascen2k.taskcontrol.repositorio.UsuarioRepositorio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUser(@PathVariable Integer id){
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return usuario.orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario){
        usuario.setfechaCreacion(LocalDateTime.now());
        return usuarioRepositorio.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario userform){
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(EntityNotFoundException::new);
        usuario.setNombre(userform.getNombre());
        usuario.setCorreo(userform.getCorreo());
        usuario.setClave(userform.getClave());
        usuario.setRol(userform.getRol());
        return usuarioRepositorio.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        usuarioRepositorio.deleteById(id);
    }
}
