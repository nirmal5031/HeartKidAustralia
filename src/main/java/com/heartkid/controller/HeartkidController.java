package com.heartkid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.service.HeartKidRegistrationService;
import com.heartkid.service.HeartkidMailingService;
import com.heartkid.util.Dategenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class HeartkidController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartkidController.class);

    @Autowired
    private Dategenerator dategenerator;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private HeartkidMailingService mailingclass;

    @Autowired
    private HeartKidRegistrationService heartKidRegistrationService;

    @RequestMapping(value = "heartkid/regcount", method = RequestMethod.GET)
    public String registrationCount() {
        String regcount = null;
        try {
            regcount = heartKidRegistrationService.retrieveRegistrationCount();
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }
        return regcount;
    }

    @RequestMapping(value = "heartkid/referencegen", method = RequestMethod.GET)
    public String generateReference() {
        return heartKidRegistrationService.generateReferenceNumber();
    }

    @RequestMapping(value = "heartkid/personalinfo", method = RequestMethod.POST)
    public String savePersonalInfo(@RequestBody RegisterDtoEntity personalinfo) {
        String personalinfoJSON = null;
        try {
            if (personalinfo.getUpdateddate() == "admin") {
                personalinfo.setUpdateddate(dategenerator.dategenerator());
            } else {
                personalinfo.setRegistrationdate(dategenerator.dategenerator());
            }
            LOGGER.info("Name of the request ::" + personalinfo.getFirstname() + " " + personalinfo.getLastname());
            LOGGER.info("Referencenumber of the request :::::::::::" + personalinfo.getReferencenumber());

            if (personalinfo != null) {
                heartKidRegistrationService.saveRegistrationInformation(personalinfo);
                personalinfoJSON = mapper.writeValueAsString(personalinfo);
            }

        } catch (NullPointerException exception) {
            return "{'value':'failure'}";
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }
        return personalinfoJSON;
    }

    @RequestMapping(value = "heartkid/outhospital", method = RequestMethod.POST)
    public String finalConfirmation(@RequestBody RegisterDtoEntity outhospitalentity) throws MessagingException {
        String response = null;
        try {
            if (outhospitalentity != null) {
                outhospitalentity.setRegistrationdate(dategenerator.dategenerator());
                outhospitalentity.setSurveystatus("success");
                RegisterDtoEntity resp = heartKidRegistrationService.saveRegistrationInformation(outhospitalentity);
                LOGGER.info("MAIL TO USER " + resp.getEmail());
                if (resp.getSurveystatus() == "success") {
                    response = "success";
                    try {
                        // mailingclass.mailingservice(resp.getEmail());
                    } catch (Exception mex) {
                        mex.printStackTrace();
                    }
                } else {
                    response = "failure";
                }

            } else
                LOGGER.info("burden disease is null");
        } catch (Exception ex) {
            response = "failure";
            LOGGER.info(ex.toString());
        }

        return response;
    }

    @RequestMapping(value = "heartkid/updaterecord", method = RequestMethod.POST)
    public String updateRecordHeartkid(
            @RequestParam(value = "updaterecordref", defaultValue = "") String updaterecordref,
            @RequestBody RegisterDtoEntity registration) {
        try {
            if (registration != null) {
                heartKidRegistrationService.saveRegistrationInformation(registration);
                LOGGER.info("SUCCESS");
            } else
                LOGGER.info("registration is null");
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return "User record edited successfully ! (reference Id is = " + updaterecordref + ")";
    }

}