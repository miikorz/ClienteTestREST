/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "pedidos")
@NamedQueries({
@NamedQuery(name = "pedido.findAll", query = "SELECT p FROM Pedido p ORDER BY p.id")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_pedidos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false, length=50)
    private String descripcion;
    
    @Column(nullable=false)
    private int estado;
    
    @OneToMany (mappedBy="pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Linea_Pedido> lineas;

    public Pedido() {
        lineas = new HashSet<Linea_Pedido>();
    }

    public Pedido(String descripcion, int estado) {
        this.descripcion = descripcion;
        this.estado = estado;
        lineas = new HashSet<Linea_Pedido>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Set<Linea_Pedido> getLineas() {
        return lineas;
    }

    public void setLineas(Set<Linea_Pedido> lineas) {
        this.lineas = lineas;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", lineas=" + lineas + '}';
    }


    
    
    
}
