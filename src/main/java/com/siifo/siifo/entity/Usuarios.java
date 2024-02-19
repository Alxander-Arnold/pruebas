package com.siifo.siifo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import java.util.List;

@Data
@Entity(name="usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoIdentificacion;
    private int numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private int telefonoUsuario;
    private String correoUsuario;
    private String passwordUsuario;

    //relacionamos la tabla rol
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="usuarios_rol",
    joinColumns = @JoinColumn(name="usuarios_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="rol_idRol", referencedColumnName = "idRol")
    )
    private List<Rol> rol;
}
