package com.example.projetintegr.controllers;

import com.example.projetintegr.dao.CertifRepository;
import com.example.projetintegr.dao.ClubRepository;
import com.example.projetintegr.entities.Certif;
import com.example.projetintegr.entities.Club;
import com.example.projetintegr.service.CertifService;
import com.example.projetintegr.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class CatController {
    @Autowired
    ClubService clubService;
    @Autowired
    CertifService certifService;
    @Autowired
    ClubRepository clubRepository;
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

        if (bindingResult.hasErrors()) {
            return "createClub";
        }

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
    //Table
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
    //CERTIFICATION
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
   /* @RequestMapping("/certification")
    public String Certification(@RequestParam(name = "id", required = false) Long id, ModelMap modelMap) {
        Certif certif = certifService.getCertifById(id);
        modelMap.addAttribute("certif", certif);
        return "createCertif";
    }*/
    @RequestMapping("/saveCertif")
    public String saveCertif(@Valid Certif certif,
                             BindingResult bindingResult,
                             @RequestParam(value = "id", required = false) Long clubId,
                             @RequestParam("image") MultipartFile file,
                             @RequestParam("dateCreation") String date,
                             ModelMap modelMap) throws IOException, ParseException {
   /*     Long clubIdStr = null;
        if (clubId != null) {
            clubIdStr = Long.parseLong(String.valueOf(clubId));
        } else {
            modelMap.addAttribute("errorMessage", "Erreur : le club est null.");
        }
        if (bindingResult.hasErrors()) {
            return "createCertif";
        }*/

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

}