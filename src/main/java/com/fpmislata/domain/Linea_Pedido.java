/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "lineas_pedido")
@NamedQueries({
@NamedQuery(name = "Linea_Pedido.findAll", query = "SELECT l FROM Linea_Pedido l ORDER BY l.id")})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Linea_Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_lineas_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "pedido")
    @XmlTransient
    private Pedido pedido;
    
    @Column(nullable=false, length=50)
    private String articulo;
    
    @Column(nullable=false)
    private int cantidad;
    
    @Column(nullable=false)
    private float precio;

    public Linea_Pedido() {
    }

    public Linea_Pedido(Pedido pedido, String articulo, int cantidad, float precio) {
        this.pedido = pedido;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
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
        final Linea_Pedido other = (Linea_Pedido) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Linea_Pedido{" + "id=" + id + ", pedido=" + pedido + ", articulo=" + articulo + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    

}
