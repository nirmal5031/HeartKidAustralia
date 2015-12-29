package com.heartkid.service;

import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.repository.PersonalInfoRepository;
import com.heartkid.util.RandomNumGenerator;
import com.heartkid.util.ReferenceNumGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartKidRegistrationService {

    @Autowired
    private PersonalInfoRepository personalRepository;

    @Autowired
    private RandomNumGenerator randomNumber;

    @Autowired
    private HeartkidRepository heartkidRepository;

    public String retrieveRegistrationCount() {
        int regCountNumber = personalRepository.registationcount();
        return Integer.toString(regCountNumber);
    }

    public String generateReferenceNumber() {
        int randomCount = randomNumber.randomcountgenerator();
        return randomNumber.generateRandomString(randomCount).toUpperCase();
    }

    public RegisterDtoEntity saveRegistrationInformation(RegisterDtoEntity registerDtoEntity) {
        return personalRepository.save(registerDtoEntity);
    }

    public RegisterDtoEntity updateRegistrationRecord(RegisterDtoEntity registerDtoEntity) {
        return heartkidRepository.save(registerDtoEntity);
    }
}
