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

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.service.BillPayService;
import com.nexxera.bakerybudget.service.BusinessService;

@Controller
public class BillPayController {
	@Autowired
	private BillPayService billPayService;
	@Autowired
	private BusinessService businessService;

	@GetMapping(value = "/bill-pay/")
	public String create(Model model) {
		model.addAttribute("billPayForm", new BillPay());
		model.addAttribute("businessList", businessService.findAll());
		return "bill-pay/new";
	}

	@PostMapping(path = "/bill-pay/")
	@Transactional
	public String create(@ModelAttribute("billPayForm") BillPay billPayForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		BillPay createReturn = billPayService.createBill(billPayForm);
		redirectAttributes.addFlashAttribute("messageSuccess", "Conta a pagar inserida com sucesso.");
		return "redirect:/bill-pay/" + createReturn.getId();
	}

	@GetMapping(path = "/bill-pay/{id}")
	public String update(@PathVariable Long id, Model model) {
		if (!model.containsAttribute("billPayForm")) {
			BillPay billPay = billPayService.findOne(id);
			model.addAttribute("billPayForm", billPay);
		}
		model.addAttribute("businessList", businessService.findAll());
		return "bill-pay/edit";
	}

	@PutMapping(path = "/bill-pay/{id}")
	@Transactional
	public String update(@PathVariable Long id, @ModelAttribute("billPayForm") BillPay billPayForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		billPayService.update(billPayForm);
		redirectAttributes.addFlashAttribute("messageSuccess", "Conta a pagar atualizada com sucesso.");
		return "redirect:/bill-pay/" + id;
	}

	@DeleteMapping(path = "/bill-pay/{id}")
	@Transactional
	public String remove(@PathVariable Long id) {
		BillPay billPay = billPayService.findOne(id);
		billPayService.remove(billPay);
		return "redirect:/bill-pay";
	}

	@GetMapping(path = "/bill-pay")
	@Transactional(readOnly = true)
	public String findAll(Model model) {
		model.addAttribute("billPay", billPayService.findAll());
		return "bill-pay/list";
	}
}