package com.nexxera.bakerybudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexxera.bakerybudget.model.Business;
import com.nexxera.bakerybudget.service.BusinessService;

@Controller
public class BusinessController {
	@Autowired
	private BusinessService businessService;

	@GetMapping(value = "/business/")
	public String create(Model model) {
		model.addAttribute("businessForm", new Business());
		return "business/new";
	}

	@PostMapping(path = "/business/")
	@Transactional
	public String create(@ModelAttribute("businessForm") Business businessForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		Business createReturn = businessService.create(businessForm);
		redirectAttributes.addFlashAttribute("messageSuccess", "Filial inserida com sucesso.");
		return "redirect:/business/" + createReturn.getId();
	}

	@GetMapping(path = "/business/{id}")
	public String update(@PathVariable Long id, Model model) {
		if (!model.containsAttribute("businessForm")) {
			Business business = businessService.findOne(id);
			model.addAttribute("businessForm", business);
		}
		return "business/edit";
	}

	@PutMapping(path = "/business/{id}")
	@Transactional
	public String update(@PathVariable Long id, @ModelAttribute("businessForm") Business businessForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		businessService.update(businessForm);
		redirectAttributes.addFlashAttribute("messageSuccess", "Filial atualizada com sucesso.");
		return "redirect:/business/" + id;
	}

	@DeleteMapping(path = "/business/{id}")
	@Transactional
	public String remove(@PathVariable Long id) {
		Business business = businessService.findOne(id);
		businessService.remove(business);
		return "redirect:/business";
	}

	@GetMapping(path = "/business")
	@Transactional(readOnly = true)
	public String findAll(Model model) {
		model.addAttribute("business", businessService.findAll());
		return "business/list";
	}
}