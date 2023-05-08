package com.example.projetintegr.entities;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
@Entity
public class Certif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertif;
    @NotNull
    @Size(min = 2,max = 30)
    private String nomCertif;
    @Min(value = 10)
    @Max(value = 100)
    private double prixCertif;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateCreation;
    @NotNull
    @Size(min = 2,max = 30)
    private String ObjectCertif;
    // Ajouter un attribut pour stocker le chemin de l'image
    @Column(name = "image_certif")
    private String imageCertif;
    @ManyToOne
    private Users users;
    @ManyToOne
    private Club club;

    public Certif() {
    }
    public Certif(String nomCertif, double prixCertif, Date dateCreation, String objectCertif, String imageCertif, Users users, Club club) {
        this.nomCertif = nomCertif;
        this.prixCertif = prixCertif;
        this.dateCreation = dateCreation;
        ObjectCertif = objectCertif;
        this.imageCertif = imageCertif;
        this.users = users;
        this.club = club;
    }

    public Certif(String nomCertif, double prixCertif, Date dateCreation, String objectCertif, String imageCertif, Users users) {
        this.nomCertif = nomCertif;
        this.prixCertif = prixCertif;
        this.dateCreation = dateCreation;
        ObjectCertif = objectCertif;
        this.imageCertif = imageCertif;
        this.users = users;
    }

    public Certif(String nomCertif, double prixCertif, Date dateCreation, String objectCertif, String imageCertif) {
        this.nomCertif = nomCertif;
        this.prixCertif = prixCertif;
        this.dateCreation = dateCreation;
        ObjectCertif = objectCertif;
        this.imageCertif = imageCertif;
    }

    public Long getIdCertif() {
        return idCertif;
    }

    public void setIdCertif(Long idCertif) {
        this.idCertif = idCertif;
    }

    public String getNomCertif() {
        return nomCertif;
    }

    public void setNomCertif(String nomCertif) {
        this.nomCertif = nomCertif;
    }

    public double getPrixCertif() {
        return prixCertif;
    }

    public void setPrixCertif(double prixCertif) {
        this.prixCertif = prixCertif;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getObjectCertif() {
        return ObjectCertif;
    }

    public void setObjectCertif(String objectCertif) {
        ObjectCertif = objectCertif;
    }

    public String getImageCertif() {
        return imageCertif;
    }

    public void setImageCertif(String imageCertif) {
        this.imageCertif = imageCertif;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
