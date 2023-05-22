package com.example.projetintegr.entities;

import javax.persistence.*;
import java.util.List;
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @Column(name = "username")
    private String NomUser;
    private String Email;
    private String Password;
    @OneToMany(mappedBy = "users")
    private List<Club> clubs;
    public Users() {
    }
    public Users(String nomUser, String email, String password, List<Club> clubs ){
        NomUser = nomUser;
        Email = email;
        Password = password;
        this.clubs = clubs;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return NomUser;
    }

    public void setNomUser(String nomUser) {
        this.NomUser = nomUser;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}
