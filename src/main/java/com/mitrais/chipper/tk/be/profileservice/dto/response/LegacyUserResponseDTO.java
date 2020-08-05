package com.mitrais.chipper.tk.be.profileservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegacyUserResponseDTO {
    private Long userId;
    private String email;
    private String passwordHashed;
    private String provider;
    private String uid;
    private String messagingToken;
    private String dataState;
    private String createdBy;
    private String lastModifiedBy;
    @Temporal(TIMESTAMP)
    private Date createdDate;
    @Temporal(TIMESTAMP)
    private Date lastModifiedDate;
    @Temporal(TIMESTAMP)
    private Date logout;
}
