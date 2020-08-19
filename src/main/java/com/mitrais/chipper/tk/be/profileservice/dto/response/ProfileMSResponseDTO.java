package com.mitrais.chipper.tk.be.profileservice.dto.response;

import java.time.LocalDate;
import java.util.Date;

import com.mitrais.chipper.tk.be.profileservice.common.Gender;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileMSResponseDTO {
	private Long id;
	private Long userId;
	private String fullName;
	private LocalDate dob;
	private Gender gender;
	private String city;
	private String aboutMe;
	private String interest;
	private byte[] photoProfile;
	private String photoProfileFilename;
	protected boolean deleted;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;

	public ProfileMSResponseDTO(ProfileMSResponseDTO profile) {
		this.id = profile.getId();
		this.userId = profile.getUserId();
		this.fullName = profile.getFullName();
		this.dob = profile.getDob();
		this.gender = profile.getGender();
		this.city = profile.getCity();
		this.aboutMe = profile.getAboutMe();
		this.interest = profile.getInterest();
		this.photoProfile = profile.getPhotoProfile();
		this.photoProfileFilename = profile.getPhotoProfileFilename();
		this.deleted = profile.isDeleted();
		this.createdBy = profile.getCreatedBy();
		this.createdDate = profile.getCreatedDate();
		this.lastModifiedBy = profile.getLastModifiedBy();
		this.lastModifiedDate = profile.getLastModifiedDate();
	}
}
