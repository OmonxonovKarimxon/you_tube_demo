package com.company.dto.report;

import com.company.dto.ProfileDTO;
import com.company.enums.ReportType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportResponseDTO {
//  id,profile(id,name,surname,photo(id,url)),content,
//            entity_id(channel/video)),type(channel,video)
    private  Integer id;
    private String entityId;
    private String content;
    private ReportType type;
    private ProfileDTO profileDTO;
}
