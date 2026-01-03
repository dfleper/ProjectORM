package aed.map;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DOMINGO
 */
@Entity
@Table(catalog = "concesionario", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"matricula"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coche.findAll", query = "SELECT c FROM Coche c")
    , @NamedQuery(name = "Coche.findByIdCoche", query = "SELECT c FROM Coche c WHERE c.idCoche = :idCoche")
    , @NamedQuery(name = "Coche.findByMarca", query = "SELECT c FROM Coche c WHERE c.marca = :marca")
    , @NamedQuery(name = "Coche.findByModelo", query = "SELECT c FROM Coche c WHERE c.modelo = :modelo")
    , @NamedQuery(name = "Coche.findByColor", query = "SELECT c FROM Coche c WHERE c.color = :color")
    , @NamedQuery(name = "Coche.findByPrecio", query = "SELECT c FROM Coche c WHERE c.precio = :precio")
    , @NamedQuery(name = "Coche.findByMatricula", query = "SELECT c FROM Coche c WHERE c.matricula = :matricula")})
public class Coche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_coche", nullable = false)
    private Integer idCoche;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String marca;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String modelo;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String color;
    @Basic(optional = false)
    @Column(nullable = false)
    private float precio;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String matricula;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCoche", fetch = FetchType.EAGER)
    private Collection<Revision> revisionCollection;

    public Coche() {
    }

    public Coche(Integer idCoche) {
        this.idCoche = idCoche;
    }

    public Coche(Integer idCoche, String marca, String modelo, String color, float precio, String matricula) {
        this.idCoche = idCoche;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
        this.matricula = matricula;
    }

    public Integer getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(Integer idCoche) {
        this.idCoche = idCoche;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public Collection<Revision> getRevisionCollection() {
        return revisionCollection;
    }

    public void setRevisionCollection(Collection<Revision> revisionCollection) {
        this.revisionCollection = revisionCollection;
    }

    @Override
    public int hashCode() {
        return (idCoche != null ? idCoche.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Coche)) {
            return false;
        }
        Coche other = (Coche) object;
        if (this.idCoche == null || other.idCoche == null) {
            return false;
        }
        return this.idCoche.equals(other.idCoche);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(idCoche != null ? idCoche : "(sin id)");
        if (matricula != null && !matricula.trim().isEmpty()) {
            sb.append(" - ").append(matricula.trim());
        }
        return sb.toString();
    }

}
