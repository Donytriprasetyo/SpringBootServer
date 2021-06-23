/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.ksm.controllers;

import com.ksm.ksm.models.Belonging;
import com.ksm.ksm.models.Person;
import com.ksm.ksm.services.BelongingService;
import com.ksm.ksm.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dony Tri P
 */
@Controller
@RequestMapping("belonging")
public class BelongingController {
    @Autowired
    private BelongingService belongingService;
    @Autowired
    private PersonService personService;
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("belongings", belongingService.getAll());
        return "belonging";
    }
    
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("belonging", belongingService.getById(id));
        return "detail-belonging";
    }
    
    //Cretae
    
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("belonging", new Belonging());
        model.addAttribute("persons",  personService.getAll());
        model.addAttribute("title", "KSM - Person Form");
        model.addAttribute("toPage", "/belonging");
        model.addAttribute("mainPage", "Belonging");
        return "create-form-belonging";
    }
    
    @PostMapping("/create")
    public String create(@ModelAttribute Belonging belonging, Model model) {
        belongingService.create(belonging);
        return "redirect:/belonging";
    }
   
    //Update
    
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable("id")Integer id, Model model) {
        model.addAttribute("persons", personService.getAll());
        model.addAttribute("belonging", belongingService.getById(id));
        model.addAttribute("title", "KSM - Belonging Update Form");
        return "update-form-belonging";
    }
    
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id")Integer id, 
        @ModelAttribute Belonging belonging, Model model) {
        Person person = personService.getById(belonging.getId());
        belonging.setPerson(person);
        belongingService.update(id, belonging);
        return "redirect:/belonging";
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        belongingService.delete(id);
        return "redirect:/belonging";
    }
}
