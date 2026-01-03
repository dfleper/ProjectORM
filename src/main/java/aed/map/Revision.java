package aed.map;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DOMINGO
 */
@Entity
@Table(catalog = "concesionario", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revision.findAll", query = "SELECT r FROM Revision r")
    , @NamedQuery(name = "Revision.findByIdRevision", query = "SELECT r FROM Revision r WHERE r.idRevision = :idRevision")
    , @NamedQuery(name = "Revision.findByFiltro", query = "SELECT r FROM Revision r WHERE r.filtro = :filtro")
    , @NamedQuery(name = "Revision.findByAceite", query = "SELECT r FROM Revision r WHERE r.aceite = :aceite")
    , @NamedQuery(name = "Revision.findByFrenos", query = "SELECT r FROM Revision r WHERE r.frenos = :frenos")
    , @NamedQuery(name = "Revision.findByFecha", query = "SELECT r FROM Revision r WHERE r.fecha = :fecha")})
public class Revision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_revision", nullable = false)
    private Integer idRevision;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String filtro;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String aceite;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String frenos;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_coche", referencedColumnName = "id_coche", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Coche idCoche;

    public Revision() {
    }

    public Revision(Integer idRevision) {
        this.idRevision = idRevision;
    }

    public Revision(Integer idRevision, String filtro, String aceite, String frenos, Date fecha) {
        this.idRevision = idRevision;
        this.filtro = filtro;
        this.aceite = aceite;
        this.frenos = frenos;
        this.fecha = fecha;
    }

    public Integer getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(Integer idRevision) {
        this.idRevision = idRevision;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getAceite() {
        return aceite;
    }

    public void setAceite(String aceite) {
        this.aceite = aceite;
    }

    public String getFrenos() {
        return frenos;
    }

    public void setFrenos(String frenos) {
        this.frenos = frenos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Coche getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(Coche idCoche) {
        this.idCoche = idCoche;
    }

    @Override
    public int hashCode() {
        return (idRevision != null ? idRevision.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Revision)) {
            return false;
        }
        Revision other = (Revision) object;
        if (this.idRevision == null || other.idRevision == null) {
            return false;
        }
        return this.idRevision.equals(other.idRevision);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(idRevision != null ? idRevision : "(sin id)");
        if (fecha != null) {
            sb.append(" - ").append(fecha);
        }
        return sb.toString();
    }

}
