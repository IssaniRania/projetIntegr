package com.example.projetintegr.entities;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClub;
    private String name;
    @NotNull
    @Size(min = 2,max = 30)
    private String nomClub;
    @Min(value = 10)
    @Max(value = 100)
    private double prixClub;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dateCreation;
    @NotNull
    @Size(min = 2,max = 30)
    private String ObjectClub;
    // Ajouter un attribut pour stocker le chemin de l'image
    @Column(name = "image_path")
    private String imagePath;
    @ManyToOne
    private Users users;
    @OneToMany(mappedBy = "club")
    private List<Certif> certifs;
    @OneToMany(mappedBy = "club")
    private List<Formation> formations;
    @OneToMany(mappedBy = "club")
    private List<Certif> evenements;

    public Club(String nomClub, double prixClub, Date dateCreation, String objectClub,String imagePath) {
        this.nomClub = nomClub;
        this.prixClub = prixClub;
        this.dateCreation = dateCreation;
        ObjectClub = objectClub;
        this.imagePath = imagePath;
    }

    public Club(String nomClub, double prixClub, Date dateCreation, String objectClub, String imagePath, Users users, List<Certif> certifs) {
        this.nomClub = nomClub;
        this.prixClub = prixClub;
        this.dateCreation = dateCreation;
        ObjectClub = objectClub;
        this.imagePath = imagePath;
        this.users = users;
        this.certifs = certifs;
    }

    public Club(List<Formation> formations) {
        this.formations = formations;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public Club(String name, String nomClub, double prixClub, Date dateCreation, String objectClub, String imagePath, Users users, List<Certif> certifs, List<Formation> formations, List<Certif> evenements) {
        this.name = name;
        this.nomClub = nomClub;
        this.prixClub = prixClub;
        this.dateCreation = dateCreation;
        ObjectClub = objectClub;
        this.imagePath = imagePath;
        this.users = users;
        this.certifs = certifs;
        this.formations = formations;
        this.evenements = evenements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Certif> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Certif> evenements) {
        this.evenements = evenements;
    }

    public Club() {
    }

    public List<Certif> getCertifs() {
        return certifs;
    }

    public void setCertifs(List<Certif> certifs) {
        this.certifs = certifs;
    }

    public Club(String nomClub, double prixClub, Date dateCreation, String objectClub, String imagePath, Users users) {
        this.nomClub = nomClub;
        this.prixClub = prixClub;
        this.dateCreation = dateCreation;
        ObjectClub = objectClub;
        this.imagePath = imagePath;
        this.users = users;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
