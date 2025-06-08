package com.safezone.web.controller;

import com.safezone.web.model.Report;
import com.safezone.web.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public String exibirFormulario(Model model) {
        model.addAttribute("report", new Report());
        return "reports";
    }

    @PostMapping
    public String enviarRelatorio(@ModelAttribute Report report) {
        reportService.salvar(report);
        return "redirect:/home";
    }
    @GetMapping("/lista")
    public String listarRelatorios(Model model) {
        model.addAttribute("relatorios", reportService.listarTodos());
        return "report-list";
    }

}