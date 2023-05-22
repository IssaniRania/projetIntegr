package com.example.projetintegr.controllers;

import com.example.projetintegr.entities.Club;
import com.example.projetintegr.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CatController {
    @Autowired
    ClubService clubService;

    @RequestMapping("/showCreate")
    public String showCreate() {
        return "createClub";
    }
    @RequestMapping("/saveClub")
    public String saveProduit(@ModelAttribute("club") Club club,
                              @RequestParam("date") String date,
                              ModelMap modelMap) throws ParseException
    {
        //conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        club.setDateCreation(dateCreation);
        Club saveClub = clubService.saveClub(club);
        String msg ="Ajout avec success "+saveClub.getIdClub();
        modelMap.addAttribute("msg", msg);
        return "createClub";
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
        return "ListeClub";
    }
    //navbar
    @RequestMapping("/Navbar")
    public String NvbarClub() {
        return "Navbar";
    }
    //modifier
    @RequestMapping("/modifierClub")
    public String modifierClub(@RequestParam("id") Long id,ModelMap modelMap)
    {
        Club c= clubService.getClub(id);
        modelMap.addAttribute("club", c);
        return "editerClub";
    }
    @RequestMapping("/updateClub")
    public String updateClub(@ModelAttribute("club") Club club,
                             @RequestParam("date") String date,
                             ModelMap modelMap) throws ParseException
    {
        //conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        club.setDateCreation(dateCreation);

        clubService.updateClub(club);
        List<Club> prods = clubService.getAllClub();
        modelMap.addAttribute("clubs", prods);

        return "createClub";
    }


}