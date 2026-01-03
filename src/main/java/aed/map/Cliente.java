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
    @UniqueConstraint(columnNames = {"dni"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
    , @NamedQuery(name = "Cliente.findByDni", query = "SELECT c FROM Cliente c WHERE c.dni = :dni")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByApellido1", query = "SELECT c FROM Cliente c WHERE c.apellido1 = :apellido1")
    , @NamedQuery(name = "Cliente.findByApellido2", query = "SELECT c FROM Cliente c WHERE c.apellido2 = :apellido2")
    , @NamedQuery(name = "Cliente.findByTipoVia", query = "SELECT c FROM Cliente c WHERE c.tipoVia = :tipoVia")
    , @NamedQuery(name = "Cliente.findByNombreVia", query = "SELECT c FROM Cliente c WHERE c.nombreVia = :nombreVia")
    , @NamedQuery(name = "Cliente.findByNumeroVia", query = "SELECT c FROM Cliente c WHERE c.numeroVia = :numeroVia")
    , @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String dni;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String nombre;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String apellido1;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String apellido2;
    @Basic(optional = false)
    @Column(name = "tipo_via", nullable = false, length = 20)
    private String tipoVia;
    @Basic(optional = false)
    @Column(name = "nombre_via", nullable = false, length = 30)
    private String nombreVia;
    @Basic(optional = false)
    @Column(name = "numero_via", nullable = false, length = 10)
    private String numeroVia;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.EAGER)
    private Collection<Coche> cocheCollection;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String dni, String nombre, String apellido1, String apellido2, String tipoVia, String nombreVia, String numeroVia, String telefono) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.tipoVia = tipoVia;
        this.nombreVia = nombreVia;
        this.numeroVia = numeroVia;
        this.telefono = telefono;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getNumeroVia() {
        return numeroVia;
    }

    public void setNumeroVia(String numeroVia) {
        this.numeroVia = numeroVia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<Coche> getCocheCollection() {
        return cocheCollection;
    }

    public void setCocheCollection(Collection<Coche> cocheCollection) {
        this.cocheCollection = cocheCollection;
    }

    @Override
    public int hashCode() {
        return (idCliente != null ? idCliente.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        // Si no hay ID, no consideramos las entidades iguales (evita falsos positivos)
        if (this.idCliente == null || other.idCliente == null) {
            return false;
        }
        return this.idCliente.equals(other.idCliente);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(idCliente != null ? idCliente : "(sin id)");
        if (nombre != null && !nombre.trim().isEmpty()) {
            sb.append(" - ").append(nombre.trim());
        }
        if (apellido1 != null && !apellido1.trim().isEmpty()) {
            sb.append(" ").append(apellido1.trim());
        }
        if (apellido2 != null && !apellido2.trim().isEmpty()) {
            sb.append(" ").append(apellido2.trim());
        }
        return sb.toString();
    }

}
