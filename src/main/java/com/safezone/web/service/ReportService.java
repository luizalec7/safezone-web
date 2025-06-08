package com.safezone.web.service;

import com.safezone.web.model.Report;
import com.safezone.web.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public void salvar(Report report) {
        reportRepository.save(report);
    }

    public List<Report> listarTodos() {
        return reportRepository.findAll();
    }
}