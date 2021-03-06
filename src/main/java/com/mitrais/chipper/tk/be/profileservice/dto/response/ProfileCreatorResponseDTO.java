package com.mitrais.chipper.tk.be.profileservice.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.mitrais.chipper.tk.be.profileservice.common.Gender;

import java.util.HashMap;

@Data
@Builder
@ApiModel(description = "All details about Creator Profile. ")
public class ProfileCreatorResponseDTO {
    @ApiModelProperty(notes = "Profile full name")
    private String fullName;

    @ApiModelProperty(notes = "Profile birth of date")
    private String age;

    @ApiModelProperty(notes = "Profile gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ApiModelProperty(notes = "Profile photo profile data byte")
    private String photoProfileUrl;

    @ApiModelProperty(notes = "Profile about me")
    private String aboutMe;

    @ApiModelProperty(notes = "Profile interest")
    private String interest;

    @ApiModelProperty(notes = "Rating")
    private HashMap<String, Double> ratingData;
}
