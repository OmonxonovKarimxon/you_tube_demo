package com.company.service;


import com.company.dto.report.ReportCreateDTO;
import com.company.dto.report.ReportResponseDTO;
import com.company.dto.video.VideoShortInfoDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.ReportEntity;
import com.company.mapper.VideoShortInfo;
import com.company.repository.ReportRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ProfileService profileService;

    public ReportService(ReportRepository reportRepository, ProfileService profileService) {
        this.reportRepository = reportRepository;
        this.profileService = profileService;
    }

    public String create(ReportCreateDTO dto) {
        ProfileEntity profileEntity = profileService.currentUser();
        ReportEntity entity = new ReportEntity();
        entity.setContent(dto.getContent());
        entity.setProfileId(profileEntity.getId());
        entity.setEntityId(dto.getEntityId());
        entity.setReportType(dto.getType());
        reportRepository.save(entity);

        return "SUCCESSFULLY";
    }

    public PageImpl<ReportResponseDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReportEntity> reportInfoPage = reportRepository.pagination(pageable);
        List<ReportResponseDTO> dtoList = new LinkedList<>();
        reportInfoPage.getContent().forEach(entity -> {
            dtoList.add(entityToDTO(entity));
        });
        return new PageImpl(dtoList, pageable, reportInfoPage.getTotalElements());

    }


    public ReportResponseDTO entityToDTO(ReportEntity entity) {
        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setId(entity.getId());
        dto.setProfileDTO(profileService.profileInfo(entity.getProfile()));
        dto.setContent(entity.getContent());
        dto.setType(entity.getReportType());
        return dto;
    }

    public String deleteById(Integer reportId) {
        reportRepository.delete(reportId);
        return "DELETED";
    }

    public List<ReportResponseDTO> getByUserId(Integer userId) {
        List<ReportEntity> entityList = reportRepository.findByProfileIdAndVisible(userId, true);
        List<ReportResponseDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(entityToDTO(entity));
        });
        return dtoList;
    }
}
