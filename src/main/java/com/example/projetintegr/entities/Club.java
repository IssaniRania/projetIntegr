package com.example.projetintegr.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClub;
    private String nomClub;
    private double prixClub;
    private Date dateCreation;
    private String ObjectClub;

    public Club(String nomClub, double prixClub, Date dateCreation, String objectClub) {
        this.nomClub = nomClub;
        this.prixClub = prixClub;
        this.dateCreation = dateCreation;
        ObjectClub = objectClub;
    }

    public Club() {
    }

    public Long getIdClub() {
        return idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    public String getNomClub() {
        return nomClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public double getPrixClub() {
        return prixClub;
    }

    public void setPrixClub(double prixClub) {
        this.prixClub = prixClub;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getObjectClub() {
        return ObjectClub;
    }

    public void setObjectClub(String objectClub) {
        ObjectClub = objectClub;
    }
}
