
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase Administrador que hereda de Usuario, esta clase será la encargada de la
 * gestion de proveedores.
 *
 *
 * @author Fredy
 */
@XmlRootElement
public class Administrador extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    //Lista de Proveedores gestionados

    private Set<Proveedor> proveedores;

    private Set<Vendedor> vendedores;

    public Administrador(Set<Proveedor> proveedores, Set<Vendedor> vendedores) {
        this.proveedores = proveedores;
        this.vendedores = vendedores;
    }

    public Administrador() {

    }

    /**
     * Devuelve la lista de vendedores gestionados
     *
     * @return vendedores
     */
    public Set<Vendedor> getVendedores() {
        return vendedores;
    }

    /**
     * Inserta una lista de vendedores
     *
     * @param vendedores
     */
    public void setVendedores(Set<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    /**
     * Devuelve la lista de proveedores gestionados
     *
     * @return proveedores
     */
    public Set<Proveedor> getProveedores() {
        return proveedores;
    }

    /**
     * Inserta una lista de proveedores
     *
     * @param proveedores
     */
    public void setProveedores(Set<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

}
