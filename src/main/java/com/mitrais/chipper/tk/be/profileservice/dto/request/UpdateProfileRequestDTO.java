package com.mitrais.chipper.tk.be.profileservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateProfileRequestDTO {

	private MultipartFile image;
	private String city;
	private String aboutMe;
	private String interest;

}
