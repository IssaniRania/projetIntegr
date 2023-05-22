package com.example.projetintegr.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;
    @NotNull
    @Size(min = 2,max = 30)
    private String nomEvenement;
    @Min(value = 10)
    @Max(value = 100)
    private double prixEvenement;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateCreation;
    @NotNull
    @Size(min = 2,max = 30)
    private String ObjectEvenement;
    // Ajouter un attribut pour stocker le chemin de l'image
    @Column(name = "image_evenement")
    private String image_evenement;
    @ManyToOne
    private Users users;
    @ManyToOne
    private Club club;

    public Evenement(String nomEvenement, double prixEvenement, Date dateCreation, String objectEvenement, String image_evenement, Users users, Club club) {
        this.nomEvenement = nomEvenement;
        this.prixEvenement = prixEvenement;
        this.dateCreation = dateCreation;
        ObjectEvenement = objectEvenement;
        this.image_evenement = image_evenement;
        this.users = users;
        this.club = club;
    }

    public Evenement(String nomEvenement, double prixEvenement, Date dateCreation, String objectEvenement, String image_evenement) {
        this.nomEvenement = nomEvenement;
        this.prixEvenement = prixEvenement;
        this.dateCreation = dateCreation;
        ObjectEvenement = objectEvenement;
        this.image_evenement = image_evenement;
    }

    public Evenement() {

    }

    public Long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Long idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public double getPrixEvenement() {
        return prixEvenement;
    }

    public void setPrixEvenement(double prixEvenement) {
        this.prixEvenement = prixEvenement;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getObjectEvenement() {
        return ObjectEvenement;
    }

    public void setObjectEvenement(String objectEvenement) {
        ObjectEvenement = objectEvenement;
    }

    public String getImage_evenement() {
        return image_evenement;
    }

    public void setImage_evenement(String image_evenement) {
        this.image_evenement = image_evenement;
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
