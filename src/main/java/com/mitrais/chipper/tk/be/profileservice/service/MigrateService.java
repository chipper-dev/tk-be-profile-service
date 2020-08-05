package com.mitrais.chipper.tk.be.profileservice.service;

public interface MigrateService {
	
	void fetchAndSaveFromLegacy(String header, Long userId);
}
