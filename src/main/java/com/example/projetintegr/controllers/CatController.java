package com.example.projetintegr.controllers;

import com.example.projetintegr.dao.*;
import com.example.projetintegr.entities.*;
import com.example.projetintegr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;





@Controller
public class CatController {
    @Autowired
    ClubService clubService;
    @Autowired
    CertifService certifService;
    @Autowired
    UsersCertifRepository usersCertifRepository;
    @Autowired
    UsersCertifService usersCertifService;

    @Autowired
    ClubRepository clubRepository;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    CertifRepository certifRepository;
@Autowired
    FormationService formationService;
  @Autowired
    UsersCLubService usersCLubService;
  @Autowired
    UsersFormationService usersFormationService;
    @Autowired
    EvenementService evenementService;
    @Autowired
    EvenementRepository evenementRepository;
    //CLUB
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("club", new Club());
        return "createClub";
    }
    @RequestMapping("/saveClub")
    public String saveClub(@Valid Club club,
                              BindingResult bindingResult,
                              @RequestParam("image") MultipartFile file,
                              ModelMap modelMap)  throws IOException {


        // Récupérer le chemin d'enregistrement de l'image
        String uploadDir = "";
        String fileName = file.getOriginalFilename();
        String filePath = Paths.get(uploadDir, fileName).toString();

        // Enregistrer l'image sur le disque
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);

        // Enregistrer le chemin de l'image dans l'objet Club
        club.setImagePath(filePath);

        Club savedClub = clubService.saveClub(club);
        String msg = "Ajout avec success " + savedClub.getIdClub();
        modelMap.addAttribute("msg", msg);
        return "CreateClub";
    }
    //liste
    @RequestMapping("/ListeClub")
    public String listeClub(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size)
    {
        Page<Club> prods = clubService.getAllClubsParPage(page, size);
        modelMap.addAttribute("clubs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);


        return "ListeClub";
    }
    //supprimer
    @RequestMapping("/supprimerClub")
    public String supprimerClub(@RequestParam("id") Long id, ModelMap
            modelMap,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "20") int size) {
        clubService.deleteClubById(id);
        Page<Club> prods = clubService.getAllClubsParPage(page,
                size);
        modelMap.addAttribute("clubs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "TableClub";
    }

    //navbar
    @RequestMapping("/Navbar")
    public String NvbarClub() {
        return "Navbar";
    }
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    /*@RequestMapping("/UserClub")
      public String UserClub() {
          return "UserClub";
      }
      @RequestMapping("/UserTableClub")
      public String UserTableClub() {
          return "UserTableClub";
      }
      @RequestMapping("/inscritClub")
      public String inscritClub() {
          return "inscritClub";
      }
      //Table
  /**club**/
    @RequestMapping("/TableClub")
    public String TableClub(ModelMap modelMap,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "20") int size) {

            Page<Club> prods = clubService.getAllClubsParPage(page, size);
            modelMap.addAttribute("clubs", prods);
            modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);


        return "TableClub";
    }

    @GetMapping("/clubs/{id}/image")
    public ResponseEntity<byte[]> getClubImage(@PathVariable Long id) throws SQLException, IOException {
        Club club = clubRepository.findById(id).orElse(null);
        if (club == null || club.getImagePath() == null) {
            return ResponseEntity.notFound().build();
        }
        Path imagePath = Paths.get(club.getImagePath());
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = Files.readAllBytes(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }



    //modifier
  @RequestMapping("/modifierClub")
    public String modifierClub(@RequestParam("id") Long id, ModelMap modelMap) {
        Club club = clubService.getClubById(id);
        modelMap.addAttribute("club", club);
        return "editerClub";
    }

    @RequestMapping("/updateClub")
    public String updateClub(@ModelAttribute("club") Club club,
                             @RequestParam("date") String date,
                             @RequestParam("id") Long id,
                             ModelMap modelMap) throws ParseException {
        Club existingClub = clubService.getClubById(id);
        existingClub.setNomClub(club.getNomClub());
        existingClub.setObjectClub(club.getObjectClub());
        existingClub.setPrixClub(club.getPrixClub());

        // Conversion de la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        existingClub.setDateCreation(dateCreation);

        clubService.updateClub(existingClub);
        List<Club> clubs = clubService.getAllClub();
        modelMap.addAttribute("clubs", clubs);
        return "TableClub";
    }

    /**evenement**/
    @RequestMapping("/evenement")
    public String Evenement(@RequestParam(name = "id", required = false) Long id, ModelMap modelMap) {
        Club club = null;
        if (id != null && !id.equals("null")) {
            try {
                Long clubId = Long.parseLong(String.valueOf(id));
                club = clubService.getClubById(clubId);
            } catch (NumberFormatException e) {
                modelMap.addAttribute("errorMessage", "Erreur : le club est null.");
            }
        }
        modelMap.addAttribute("club", club);
        return "createEvenement";

    }
    @RequestMapping("/saveEvenement")
    public String saveEvenement(@Valid Evenement evenement,
                                BindingResult bindingResult,
                                @RequestParam(value = "id", required = false) Long clubId,
                                @RequestParam("image") MultipartFile file,
                                @RequestParam("dateCreation") String date,
                                ModelMap modelMap) throws IOException, ParseException {


        // Récupérer le chemin d'enregistrement de l'image
        String uploadDir = "";
        String fileName = file.getOriginalFilename();
        String filePath = Paths.get(uploadDir, fileName).toString();

        // Enregistrer l'image sur le disque
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);

        // Enregistrer le chemin de l'image dans l'objet Certif
        evenement.setImage_evenement(filePath);
        // Récupérer le club correspondant à l'id ou créer un nouveau club si clubId est null
        Club club = null;
        if (clubId != null) {
            club = clubRepository.findById(clubId).orElse(null);
        } else {
            /*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreation = dateformat.parse(String.valueOf(date));
            certif.setDateCreation(dateCreation);*/
            evenement = new Evenement(evenement.getNomEvenement(), evenement.getPrixEvenement(), evenement.getDateCreation(), evenement.getObjectEvenement(), evenement.getImage_evenement());
        }
        // Associer le certificat avec le club
        evenement.setClub(club);

        Evenement savedevenement = evenementService.saveEvenement(evenement);
        String msg = "Ajout avec success " + savedevenement.getIdEvenement();
        modelMap.addAttribute("msg", msg);
        if (clubId != null) {
            return "redirect:/evenement?id=" + clubId;
        } else {
            return "redirect:/evenement";
        }
    }
    @RequestMapping("/TableEvenement")
    public String TableEvenement(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size)
    {
        Page<Evenement> evenement=evenementService.getAllEvenementsParPage(page, size);
        modelMap.addAttribute("evenements", evenement);
        modelMap.addAttribute("pages", new int[evenement.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "TableEvenement";
    }
    @RequestMapping("/supprimerEvenement")
    public String supprimerEvenement(@RequestParam("id") Long id, ModelMap modelMap,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        evenementService.deleteEvenementById(id);
        Page<Evenement> evenements =evenementService.getAllEvenementsParPage(page, size);
        modelMap.addAttribute("evenements", evenements);
        modelMap.addAttribute("pages", new int[evenements.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "TableEvenement";
    }/*
    @RequestMapping("/modifierEvenement")
    public String modifierEvenement(@RequestParam("id") Long id, ModelMap modelMap) {
        Evenement evenement = evenementService.getEvenementById(id);
        modelMap.addAttribute("evenement", evenement);
        return "editerEvenement";
    }*/
    @RequestMapping("/updateEvenement")
    public String updateEvenement(@ModelAttribute("evenement") Evenement evenement,
                                  @RequestParam("date") String date,
                                  @RequestParam("id") Long id,
                                  ModelMap modelMap) throws ParseException {
        Evenement existingEvenement = evenementService.getEvenementById(id);
        existingEvenement.setNomEvenement(evenement.getNomEvenement());
        existingEvenement.setObjectEvenement(evenement.getObjectEvenement());
        existingEvenement.setPrixEvenement(evenement.getPrixEvenement());

        // Conversion de la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        existingEvenement.setDateCreation(dateCreation);

       evenementService.updateEvenement(existingEvenement);
        List<Evenement> evenements = evenementService.getAllEvenement();
        modelMap.addAttribute("evenements", evenements);
        return "TableEvenement";
    }
    //modifierFormation
    @RequestMapping("/modifierEvenement")
    public String modifierEvenement(@RequestParam("id") Long id, ModelMap modelMap) {
        Evenement evenement = evenementService.getEvenementById(id);
        modelMap.addAttribute("evenement", evenement);
        return "editerEvenement";
    }
    /**CERTIFICATION**/
    @RequestMapping("/certification")
    public String Certification(@RequestParam(name = "id", required = false) Long id, ModelMap modelMap) {
        Club club = null;
        if (id != null && !id.equals("null")) {
            try {
                Long clubId = Long.parseLong(String.valueOf(id));
                club = clubService.getClubById(clubId);
            } catch (NumberFormatException e) {
                modelMap.addAttribute("errorMessage", "Erreur : le club est null.");
            }
        }
        modelMap.addAttribute("club", club);
        return "createCertif";
    }
    @RequestMapping("/saveCertif")
    public String saveCertif(@Valid Certif certif,
                             BindingResult bindingResult,
                             @RequestParam(value = "id", required = false) Long clubId,
                             @RequestParam("image") MultipartFile file,
                             @RequestParam("dateCreation") String date,
                             ModelMap modelMap) throws IOException, ParseException {


        // Récupérer le chemin d'enregistrement de l'image
        String uploadDir = "";
        String fileName = file.getOriginalFilename();
        String filePath = Paths.get(uploadDir, fileName).toString();

        // Enregistrer l'image sur le disque
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);

        // Enregistrer le chemin de l'image dans l'objet Certif
        certif.setImageCertif(filePath);
        // Récupérer le club correspondant à l'id ou créer un nouveau club si clubId est null
        Club club = null;
        if (clubId != null) {
            club = clubRepository.findById(clubId).orElse(null);
        } else {
            /*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreation = dateformat.parse(String.valueOf(date));
            certif.setDateCreation(dateCreation);*/
            certif = new Certif(certif.getNomCertif(), certif.getPrixCertif(), certif.getDateCreation(), certif.getObjectCertif(), certif.getImageCertif());
        }
        // Associer le certificat avec le club
        certif.setClub(club);

        Certif savedCertif = certifService.saveCertif(certif);
        String msg = "Ajout avec success " + savedCertif.getIdCertif();
        modelMap.addAttribute("msg", msg);
        if (clubId != null) {
            return "redirect:/certification?id=" + clubId;
        } else {
            return "redirect:/certification";
        }
    }

    @RequestMapping("/modifierCertif")
    public String modifierCertif(@RequestParam("id") Long id, ModelMap modelMap) {
        Certif certif = certifService.getCertifById(id);
        modelMap.addAttribute("certif", certif);
        return "editerCertif";
    }
    @RequestMapping("/updateCertif")
    public String updateCertif(@ModelAttribute("certif") Certif certif,
                             @RequestParam("date") String date,
                             @RequestParam("id") Long id,
                             ModelMap modelMap) throws ParseException {
        Certif existingCertif = certifService.getCertifById(id);
        existingCertif.setNomCertif(certif.getNomCertif());
        existingCertif.setObjectCertif(certif.getObjectCertif());
        existingCertif.setPrixCertif(certif.getPrixCertif());

        // Conversion de la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(date);
        existingCertif.setDateCreation(dateCreation);

        certifService.updateCertif(existingCertif);
        List<Certif> certifs = certifService.getAllCertif();
        modelMap.addAttribute("certifs", certifs);
        return "TableCertif";
    }
    @RequestMapping("/TableCertif")
    public String TableCertif(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size)
    {
        Page<Certif> cert=certifService.getAllCertifsParPage(page, size);
        modelMap.addAttribute("certifs", cert);
        modelMap.addAttribute("pages", new int[cert.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "TableCertif";
    }
    @RequestMapping("/supprimerCertif")
    public String supprimerCertif(@RequestParam("id") Long id, ModelMap modelMap,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        certifService.deleteCertifById(id);
        Page<Certif> certifs = certifService.getAllCertifsParPage(page, size);
        modelMap.addAttribute("certifs", certifs);
        modelMap.addAttribute("pages", new int[certifs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "TableCertif";
    }
    /**formation**/
    @RequestMapping("/formation")
    public String formation(@RequestParam(name = "id", required = false) Long id, ModelMap modelMap) {
        Club club = null;
        if (id != null && !id.equals("null")) {
            try {
                Long clubId = Long.parseLong(String.valueOf(id));
                club = clubService.getClubById(clubId);
            } catch (NumberFormatException e) {
                modelMap.addAttribute("errorMessage", "Erreur : le club est null.");
            }
        }
        modelMap.addAttribute("club", club);
        return "createFormation";
    }
    @RequestMapping("/TableFormation")
    public String TableFormation(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size)
    {
        Page<Formation> formations = formationService.getAllFormationsParPage(page,size);
        modelMap.addAttribute("formations", formations);
        modelMap.addAttribute("pages", new int[formations.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "TableFormation";
    }
    @RequestMapping("/saveFormation")
    public String saveFormation(@Valid Formation formation,
                                BindingResult bindingResult,
                                @RequestParam(value = "id", required = false) Long clubId,
                                @RequestParam("image") MultipartFile file,
                                @RequestParam("dateFormation") String date,
                                ModelMap modelMap) throws IOException, ParseException {


        // Récupérer le chemin d'enregistrement de l'image
        String uploadDir = "";
        String fileName = file.getOriginalFilename();
        String filePath = Paths.get(uploadDir, fileName).toString();

        // Enregistrer l'image sur le disque
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);

        // Enregistrer le chemin de l'image dans l'objet Certif
        formation.setImageFormation(filePath);
        // Récupérer le club correspondant à l'id ou créer un nouveau club si clubId est null
        Club club = null;
        if (clubId != null) {
            club = clubRepository.findById(clubId).orElse(null);
        } else {

            formation= new Formation(formation.getNomFormation(), formation.getPrixFormation(), formation.getDateFormation(), formation.getObjectFormation(), formation.getImageFormation());
        }
        // Associer le certificat avec le club
        formation.setClub(club);

        Formation savedFormation = formationService.saveFormation(formation);
        String msg = "Ajout avec success " + savedFormation.getIdFormation();
        modelMap.addAttribute("msg", msg);
        if (clubId != null) {
            return "redirect:/formation?id=" + clubId;
      } else {
            return "redirect:/formation";
        }
    }
    @RequestMapping("/updateFormation")
    public String updateFormation(@ModelAttribute("formation") Formation formation,
                                  @RequestParam("date") String date,
                                  @RequestParam("id") Long id,
                                  ModelMap modelMap) throws ParseException {
        Formation existingFormation = formationService.getFormationById(id);
        existingFormation.setNomFormation(formation.getNomFormation());
        existingFormation.setObjectFormation(formation.getObjectFormation());
        existingFormation.setPrixFormation(formation.getPrixFormation());

        // Conversion de la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormation = dateFormat.parse(date);
        existingFormation.setDateFormation(dateFormation);

        formationService.updateFormation(existingFormation);
        List<Formation> formations = formationService.getAllFormation();
        modelMap.addAttribute("formations", formations);
        return "TableFormation";
    }
    //modifierFormation
    @RequestMapping("/modifierFormation")
    public String modifierFormation(@RequestParam("id") Long id, ModelMap modelMap) {
        Formation formation = formationService.getFormationById(id);
        modelMap.addAttribute("formation", formation);
        return "editerFormation";
    }
    @RequestMapping("/supprimerFormation")
    public String supprimerFormation(@RequestParam("id") Long id, ModelMap
            modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "20") int size) {
        formationService.deleteFormationById(id);
        Page<Formation> formations = formationService.getAllFormationsParPage(page, size);
        modelMap.addAttribute("formations", formations);
        modelMap.addAttribute("pages", new int[formations.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "TableFormation";
    }
    @GetMapping("/formations/{id}/image")
    public ResponseEntity<byte[]> getFormationImage(@PathVariable Long id) throws SQLException, IOException {
        Formation formation = formationRepository.findById(id).orElse(null);
        if (formation == null || formation.getImageFormation() == null) {
            return ResponseEntity.notFound().build();
        }
        Path imagePath = Paths.get(formation.getImageFormation());
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = Files.readAllBytes(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

/**USER CLUB**/
@RequestMapping("/userclub")
public String Userclub(@RequestParam(name = "id", required = false) Long id, ModelMap modelMap) {
    Club club = null;
    if (id != null && !id.equals("null")) {
        try {
            Long clubId = Long.parseLong(String.valueOf(id));
            club = clubService.getClubById(clubId);
        } catch (NumberFormatException e) {
            modelMap.addAttribute("errorMessage", "Erreur : le club est null.");
        }
    }
    modelMap.addAttribute("club", club);
    return "createUserClub";

}

    @RequestMapping("/saveUserCLub")
    public String saveUserCLub(@Valid UsersCLub usersCLub,
                             BindingResult bindingResult,
                             @RequestParam(value = "id", required = false) Long clubId,
                             ModelMap modelMap) throws IOException, ParseException {



        // Récupérer le club correspondant à l'id ou créer un nouveau club si clubId est null
        Club club = null;
        if (clubId != null) {
            club = clubRepository.findById(clubId).orElse(null);
        } else {

            usersCLub = new UsersCLub (usersCLub.getNom(), usersCLub.getPrenom(),usersCLub.getClasse(),usersCLub.getNumero(), usersCLub.getDapartment());
        }
        // Associer le certificat avec le club
        usersCLub.setClub(club);

        UsersCLub savedUsersCLub = usersCLubService.saveUsersCLub(usersCLub);
        String msg = "Ajout avec success " + savedUsersCLub.getIdUserClub();
        modelMap.addAttribute("msg", msg);
        if (clubId != null) {
            return "redirect:/userclub?id=" + clubId;
        } else {
            return "redirect:/userclub";
        }
    }
    @RequestMapping("/updateUsersClub")
    public String updateUsersClub(@ModelAttribute("usersclub") UsersCLub usersclub,
                                  @RequestParam("id") Long id,
                                  ModelMap modelMap) throws ParseException {
        UsersCLub existingUsersCLub = usersCLubService.getUsersCLubById(id);
        existingUsersCLub.setNom(usersclub.getNom());
        existingUsersCLub.setPrenom(usersclub.getPrenom());
        existingUsersCLub.setClasse(usersclub.getClasse());
        existingUsersCLub.setDapartment(usersclub.getDapartment());
        existingUsersCLub.setNumero(usersclub.getNumero());



        usersCLubService.updateUsersCLub(existingUsersCLub);
        List<UsersCLub> usersclubs = usersCLubService.getAllUsersCLub();
        modelMap.addAttribute("usersclubs", usersclubs);
        return "UserClub";
    }
    @RequestMapping("/UserClub")
    public String UserClub(ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "20") int size) {

        Page<Club> prods = clubService.getAllClubsParPage(page, size);
        modelMap.addAttribute("clubs", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);


        return "UserClub";
    }
    @RequestMapping("/TableInscrit")
    public String TableInscrit(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size)
    {
        Page<UsersCLub> usersCLubs = usersCLubService.getAllUsersCLubsParPage(page,size);
        modelMap.addAttribute("inscritclubs", usersCLubs);
        modelMap.addAttribute("pages", new int[usersCLubs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        Page<UsersFormation> usersFormations = usersFormationService.getAllUsersFormationsParPage(page,size);
        modelMap.addAttribute("inscritclubs1", usersFormations);
        modelMap.addAttribute("pages", new int[usersFormations.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        Page<UsersCertif> usersCertifs = usersCertifService.getAllUsersCertifsParPage(page,size);
        modelMap.addAttribute("usersCertifs", usersCertifs);
        modelMap.addAttribute("pages", new int[usersCertifs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);

        return "TableInscrit";
    }
  /**UserFormation**/
    @RequestMapping("/UserFormation")
    public String UserFormation(ModelMap modelMap,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "20") int size) {

        Page<Formation> formations = formationService.getAllFormationsParPage(page, size);
        modelMap.addAttribute("formations", formations);
        modelMap.addAttribute("pages", new int[formations.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);


        return "UserFormation";
    }
    @RequestMapping("/userformation")
    public String userformation(@RequestParam(name = "id", required = false) Long id, ModelMap modelMap) {
        Formation formation = null;
        if (id != null && !id.equals("null")) {
            try {
                Long idFormation = Long.parseLong(String.valueOf(id));
                formation =formationService.getFormationById(idFormation);
            } catch (NumberFormatException e) {
                modelMap.addAttribute("errorMessage", "Erreur : le club est null.");
            }
        }
        modelMap.addAttribute("formation", formation);
        return "createUserFormation";

    }
    @RequestMapping("/saveUserFormation")
    public String saveUserFormation(@Valid UsersFormation usersFormation,
                               BindingResult bindingResult,
                               @RequestParam(value = "id", required = false) Long idFormation,
                               ModelMap modelMap) throws IOException, ParseException {



        // Récupérer le club correspondant à l'id ou créer un nouveau club si clubId est null
        Formation formation = null;
        if (idFormation != null) {
            formation = formationRepository.findById(idFormation).orElse(null);
        } else {

            usersFormation = new UsersFormation (usersFormation.getNom(), usersFormation.getPrenom(),usersFormation.getClasse(),usersFormation.getNumero(), usersFormation.getDapartment());
        }
        // Associer le certificat avec le club
        usersFormation.setFormation(formation);

        UsersFormation savedUsersFormation = usersFormationService.saveUsersFormation(usersFormation);
        String msg = "Ajout avec success " + savedUsersFormation.getIdUserFormation();
        modelMap.addAttribute("msg", msg);
        if (idFormation != null) {
            return "redirect:/userformation?id=" + idFormation;
        } else {
            return "redirect:/userformation";
        }
    }
    @RequestMapping("/updateUsersFormation")
    public String updateUsersFormation(@ModelAttribute("usersformation") UsersFormation usersFormation,
                                  @RequestParam("id") Long id,
                                  ModelMap modelMap) throws ParseException {
        UsersFormation existingUsersFormation = usersFormationService.getUsersFormationById(id);
        existingUsersFormation.setNom(usersFormation.getNom());
        existingUsersFormation.setPrenom(usersFormation.getPrenom());
        existingUsersFormation.setClasse(usersFormation.getClasse());
        existingUsersFormation.setDapartment(usersFormation.getDapartment());
        existingUsersFormation.setNumero(usersFormation.getNumero());


        usersFormationService.updateUsersFormation(existingUsersFormation);
        List<UsersFormation> usersFormations = usersFormationService.getAllUsersFormation();
        modelMap.addAttribute("usersFormations", usersFormations);
        return "UserFormation";
    }
    /**user certif**/
    @RequestMapping("/UserCertif")
    public String UserCertif(ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "20") int size) {

        Page<Certif> certifs = certifService.getAllCertifsParPage(page, size);
        modelMap.addAttribute("certifs", certifs);
        modelMap.addAttribute("pages", new int[certifs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);


        return "UserCertif";
    }
    @RequestMapping("/usercertif")
    public String usercertif(@RequestParam(name = "id", required = false) Long id, ModelMap modelMap) {
        Certif certif = null;
        if (id != null && !id.equals("null")) {
            try {
                Long idCertif = Long.parseLong(String.valueOf(id));
                certif =certifService.getCertifById(idCertif);
            } catch (NumberFormatException e) {
                modelMap.addAttribute("errorMessage", "Erreur : le club est null.");
            }
        }
        modelMap.addAttribute("certif", certif);
        return "createUserCertif";

    }
    @RequestMapping("/saveUserCertif")
    public String saveUserCertif(@Valid UsersCertif usersCertif,
                                    BindingResult bindingResult,
                                    @RequestParam(value = "id", required = false) Long idCertif,
                                    ModelMap modelMap) throws IOException, ParseException {



        // Récupérer le club correspondant à l'id ou créer un nouveau club si clubId est null
        Certif certif = null;
        if (idCertif != null) {
            certif = certifRepository.findById(idCertif).orElse(null);
        } else {

            usersCertif = new UsersCertif (usersCertif.getNom(), usersCertif.getPrenom(),usersCertif.getClasse(),usersCertif.getNumero(), usersCertif.getDapartment());
        }
        // Associer le certificat avec le club
        usersCertif.setCertif(certif);

        UsersCertif savedUsersCertif = usersCertifService.saveUsersCertif(usersCertif);
        String msg = "Ajout avec success " + savedUsersCertif.getIdUserCertif();
        modelMap.addAttribute("msg", msg);
        if (idCertif != null) {
            return "redirect:/usercertif?id=" + idCertif;
        } else {
            return "redirect:/usercertif";
        }
    }
    @RequestMapping("/updateUsersCertif")
    public String updateUsersCertif(@ModelAttribute("userscertif") UsersCertif usersCertif,
                                       @RequestParam("id") Long id,
                                       ModelMap modelMap) throws ParseException {
        UsersCertif existingUsersCertif = usersCertifService.getUsersCertifById(id);
        existingUsersCertif.setNom(usersCertif.getNom());
        existingUsersCertif.setPrenom(usersCertif.getPrenom());
        existingUsersCertif.setClasse(usersCertif.getClasse());
        existingUsersCertif.setDapartment(usersCertif.getDapartment());
        existingUsersCertif.setNumero(usersCertif.getNumero());


        usersCertifService.updatUsersCertif(existingUsersCertif);
        List<UsersCertif> usersCertifs = usersCertifService.getAllUsersCertif();
        modelMap.addAttribute("usersCertifs", usersCertifs);
        return "UserCertif";
    }
    @GetMapping("/certifs/{id}/image")
    public ResponseEntity<byte[]> getImageCertif(@PathVariable Long id) throws SQLException, IOException {
        Certif certif = certifRepository.findById(id).orElse(null);
        if (certif == null || certif.getImageCertif() == null) {
            return ResponseEntity.notFound().build();
        }
        Path imagePath = Paths.get(certif.getImageCertif());
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = Files.readAllBytes(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }


/** user events**/
@RequestMapping("/UserEvenement")
public String UserEvenement(ModelMap modelMap,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "20") int size) {

    Page<Evenement> evenements = evenementService.getAllEvenementsParPage(page, size);
    modelMap.addAttribute("evenements", evenements);
    modelMap.addAttribute("pages", new int[evenements.getTotalPages()]);
    modelMap.addAttribute("currentPage", page);


    return "UserEvenement";
}
    @GetMapping("/evenements/{id}/image")
    public ResponseEntity<byte[]> getImageEvenement(@PathVariable Long id) throws SQLException, IOException {
        Evenement evenement = evenementRepository.findById(id).orElse(null);
        if (evenement == null || evenement.getImage_evenement() == null) {
            return ResponseEntity.notFound().build();
        }
        Path imagePath = Paths.get(evenement.getImage_evenement());
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }
        byte[] imageBytes = Files.readAllBytes(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    /** searchs**/
    @RequestMapping("/search")
    public String search(@RequestParam("nom") String name, ModelMap modelMap) {
        // Rechercher les produits qui correspondent au critère de recherche
        List<Certif> certifs = certifService.findByNomCertif(name);

        // Ajouter les produits à l'objet Model pour les afficher dans la vue
        modelMap.addAttribute("certifs", certifs);
        modelMap.addAttribute("searchTerm", name);

        return "searchResults";
    }
    @RequestMapping("/searchF")
    public String searchF(@RequestParam("nom") String name, ModelMap modelMap) {

        modelMap.addAttribute("searchTermF", name);
        List<Formation> formations = formationService.findByNomFormation(name);

        // Ajouter les produits à l'objet Model pour les afficher dans la vue
        modelMap.addAttribute("formations", formations);
        // Renvoyer la vue qui affiche les résultats de la recherche
        return "searchFormation";
    }
    @RequestMapping("/searchE")
    public String searchE(@RequestParam("nom") String name, ModelMap modelMap) {
        // Rechercher les produits qui correspondent au critère de recherche
        List<Evenement> evenements = evenementService.findByNomEvenement(name);

        // Ajouter les produits à l'objet Model pour les afficher dans la vue
        modelMap.addAttribute("evenements", evenements);
        modelMap.addAttribute("searchTermE", name);

        return "searchEvent";
    }
    @RequestMapping("/searchCL")
    public String searchCL(@RequestParam("nom") String name, ModelMap modelMap) {
        // Rechercher les produits qui correspondent au critère de recherche
        List<Club> clubs = clubService.findByNomClub(name);

        // Ajouter les produits à l'objet Model pour les afficher dans la vue
        modelMap.addAttribute("clubs", clubs);
        modelMap.addAttribute("searchTermCL", name);

        return "searchClub";
    }
}