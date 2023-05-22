package com.example.projetintegr.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity

public class UsersFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserFormation;
    private  String  nom;

    private String prenom;
    private  String  dapartment;
    private  String  classe;
    @NotNull
    @Size(min = 8,max = 8)
    private  String  numero;
    @ManyToOne
    private Formation formation;

    public UsersFormation(String nom, String prenom, String dapartment, String classe, String numero, Formation formation) {
        this.nom = nom;
        this.prenom = prenom;
        this.dapartment = dapartment;
        this.classe = classe;
        this.numero = numero;
        this.formation = formation;
    }

    public UsersFormation(String nom, String prenom, String dapartment, String classe, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.dapartment = dapartment;
        this.classe = classe;
        this.numero = numero;
    }

    public UsersFormation() {
    }

    public Long getIdUserFormation() {
        return idUserFormation;
    }

    public void setIdUserFormation(Long idUserFormation) {
        this.idUserFormation = idUserFormation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDapartment() {
        return dapartment;
    }

    public void setDapartment(String dapartment) {
        this.dapartment = dapartment;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
