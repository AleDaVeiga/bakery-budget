package com.nexxera.bakerybudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nexxera.bakerybudget.model.BillPay;
import com.nexxera.bakerybudget.model.DeductBill;
import com.nexxera.bakerybudget.service.BillPayService;
import com.nexxera.bakerybudget.service.DeductBillService;

@Controller
@RequestMapping(value = "/bill-pay")
public class DeductBillController {
	@Autowired
	private DeductBillService deductBillService;
	@Autowired
	private BillPayService billPayService;

	@GetMapping(value = "/{bill_pay_id}/deduct-bill/")
	public String create(@PathVariable Long bill_pay_id, Model model) {
		model.addAttribute("billPay", billPayService.findOne(bill_pay_id));
		model.addAttribute("deductBillForm", new DeductBill());
		return "bill-pay/deduct-bill/new";
	}

	@PostMapping(path = "/{bill_pay_id}/deduct-bill/")
	@Transactional
	public String create(@PathVariable Long bill_pay_id, @ModelAttribute("deductBillForm") DeductBill deductBillForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		BillPay billPay = billPayService.findOne(bill_pay_id);
		model.addAttribute("billPay", billPay);
		deductBillForm.setBillPay(billPay);
		DeductBill createReturn = deductBillService.create(deductBillForm);
		redirectAttributes.addFlashAttribute("messageSuccess", "Baixa inserida com sucesso.");
		return "redirect:/bill-pay/"+ bill_pay_id +"/deduct-bill/" + createReturn.getId();
	}

	@GetMapping(path = "/{bill_pay_id}/deduct-bill/{id}")
	public String update(@PathVariable Long bill_pay_id, @PathVariable Long id, Model model) {
		model.addAttribute("billPay", billPayService.findOne(bill_pay_id));
		if (!model.containsAttribute("deductBillForm")) {
			DeductBill deductBill = deductBillService.findOne(id);
			model.addAttribute("deductBillForm", deductBill);
		}
		model.addAttribute("billPayList", billPayService.findAll());
		return "bill-pay/deduct-bill/edit";
	}

	@PutMapping(path = "/{bill_pay_id}/deduct-bill/{id}")
	@Transactional
	public String update(@PathVariable Long bill_pay_id, @PathVariable Long id, @ModelAttribute("deductBillForm") DeductBill deductBillForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		BillPay billPay = billPayService.findOne(bill_pay_id);
		model.addAttribute("billPay", billPay);
		deductBillService.update(deductBillForm);
		deductBillForm.setBillPay(billPay);
		redirectAttributes.addFlashAttribute("messageSuccess", "Baixa atualizada com sucesso.");
		return "redirect:/bill-pay/"+ bill_pay_id +"/deduct-bill/" + id;
	}

	@GetMapping(path = "/{bill_pay_id}/deduct-bills")
	@Transactional(readOnly = true)
	public String findAll(@PathVariable Long bill_pay_id, Model model) {
		model.addAttribute("billPay", billPayService.findOne(bill_pay_id));
		model.addAttribute("deductBill", deductBillService.findAll(bill_pay_id));
		return "bill-pay/deduct-bill/list";
	}
}
