package com.example.projetintegr.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormation;
    @NotNull
    @Size(min = 2,max = 30)
    private String nomFormation;
    @Min(value = 10)
    @Max(value = 100)
    private double prixFormation;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFormation;
    @NotNull
    @Size(min = 2,max = 30)
    private String ObjectFormation;
    // Ajouter un attribut pour stocker le chemin de l'image
    @Column(name = "image_formation")
    private String imageFormation;
    @ManyToOne
    private Users users;
    @ManyToOne
    private Club club;
    public Formation(String nomFormation, double prixFormation, Date dateFormation, String objectFormation, String imageFormation) {
        this.nomFormation = nomFormation;
        this.prixFormation = prixFormation;
        this.dateFormation = dateFormation;
        ObjectFormation = objectFormation;
        this.imageFormation = imageFormation;
    }

    public Formation(String nomFormation, double prixFormation, Date dateFormation, String objectFormation, String imageFormation, Users users, Club club) {
        this.nomFormation = nomFormation;
        this.prixFormation = prixFormation;
        this.dateFormation = dateFormation;
        ObjectFormation = objectFormation;
        this.imageFormation = imageFormation;
        this.users = users;
        this.club = club;
    }

    public Formation() {

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

    public Long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public double getPrixFormation() {
        return prixFormation;
    }

    public void setPrixFormation(double prixFormation) {
        this.prixFormation = prixFormation;
    }

    public Date getDateFormation() {
        return dateFormation;
    }

    public void setDateFormation(Date dateFormation) {
        this.dateFormation = dateFormation;
    }

    public String getObjectFormation() {
        return ObjectFormation;
    }

    public void setObjectFormation(String objectFormation) {
        ObjectFormation = objectFormation;
    }

    public String getImageFormation() {
        return imageFormation;
    }

    public void setImageFormation(String imageFormation) {
        this.imageFormation = imageFormation;
    }
}
