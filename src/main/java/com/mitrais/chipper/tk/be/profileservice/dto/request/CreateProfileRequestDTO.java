package com.mitrais.chipper.tk.be.profileservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mitrais.chipper.tk.be.profileservice.common.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CreateProfileRequestDTO {

    private String fullname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String dob;
    private Gender gender;
    private String email;
}
