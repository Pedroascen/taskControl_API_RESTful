package sv.ascen2k.taskcontrol.controlador;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sv.ascen2k.taskcontrol.modelo.Tarea;
import sv.ascen2k.taskcontrol.modelo.Usuario;
import sv.ascen2k.taskcontrol.repositorio.UsuarioRepositorio;
import sv.ascen2k.taskcontrol.servicio.UsuarioServicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {
    private final UsuarioServicio usuarioServicio;
    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/paginar")
    Page<Usuario> paginarUser(@PageableDefault(size = 5, sort = "nombre", direction = Sort.Direction.ASC) Pageable pageable){
        return usuarioServicio.getPeageableUsuarios(pageable);
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioServicio.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUser(@PathVariable Integer id){
        return usuarioServicio.getUsuarioById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario){
        usuario.setfechaCreacion(LocalDateTime.now());
        usuario.setActivo(true);
        return usuarioServicio.saveUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario userform){
        Usuario usuario = usuarioServicio.getUsuarioById(id);
        usuario.setNombre(userform.getNombre());
        usuario.setCorreo(userform.getCorreo());
        usuario.setClave(userform.getClave());
        usuario.setRol(userform.getRol());
        return usuarioServicio.saveUsuario(usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        Usuario usuario = usuarioServicio.getUsuarioById(id);
        if(usuario!=null){
            usuario.setActivo(false);
            usuarioServicio.saveUsuario(usuario);
        }
    }

    @GetMapping("/{id}/tareas")
    public List<Tarea> getTareasByUser(@PathVariable Integer id){
        Usuario usuario = usuarioServicio.getUsuarioById(id);
        return usuario.getTareas();
    }
}
