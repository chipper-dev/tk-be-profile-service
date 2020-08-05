package com.mitrais.chipper.tk.be.profileservice.service;

import java.io.FileNotFoundException;
import java.util.List;

import com.mitrais.chipper.tk.be.profileservice.dto.request.LegacyProfileRequestDTO;
import com.mitrais.chipper.tk.be.profileservice.entity.Profile;

public interface ClientService {

	public Profile findByUserId(Long userId);

	public List<Profile> findAllProfiles();

	public Profile findById(Long id);

	public Profile findByPhotoProfileName(String photoProfileName) throws FileNotFoundException;

	public Profile save(LegacyProfileRequestDTO requestDTO);

	public void deleteByUserId(Long userId);
}
