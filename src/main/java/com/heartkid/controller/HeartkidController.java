package com.heartkid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.service.HeartKidRegistrationService;
import com.heartkid.service.HeartkidMailingService;
import com.heartkid.util.Dategenerator;
import com.heartkid.util.RandomNumGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class HeartkidController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(HeartkidController.class);

    @Autowired
    private Dategenerator dategenerator;

    @Autowired
    private RandomNumGenerator randomnumber;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private HeartkidMailingService mailingclass;

    @Autowired
    private HeartKidRegistrationService heartKidRegistrationService;


    @RequestMapping(value = "heartkid/regcount", method = RequestMethod.GET)
    public String registrationcount() {
        String regcount = null;
        try {
            regcount = heartKidRegistrationService.retrieveRegistrationCount();
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }
        return regcount;
    }


    @RequestMapping(value = "heartkid/referencegen", method = RequestMethod.GET)
    public String generatereference() {
        return heartKidRegistrationService.generateReferenceNumber();
    }


    @RequestMapping(value = "heartkid/personalinfo", method = RequestMethod.POST)
    public String savepersonalInfo(@RequestBody RegisterDtoEntity personalinfo) {
        String personalinfoJSON = null;
        try {
        	if(personalinfo.getUpdateddate()=="admin")
        	{
        		personalinfo.setUpdateddate(dategenerator.dategenerator());	
        	}
        	else
        	{
            personalinfo.setRegistrationdate(dategenerator.dategenerator());
        	}
        	LOGGER.info("Name of the request ::" + personalinfo.getFirstname() + " " + personalinfo.getLastname());
            LOGGER.info("Referencenumber of the request :::::::::::" + personalinfo.getReferencenumber());

            if (personalinfo != null) {
                heartKidRegistrationService.saveRegistrationInformation(personalinfo);
                personalinfoJSON = mapper.writeValueAsString(personalinfo);
            }
            System.out.println(personalinfoJSON);

        } catch (NullPointerException exception) {
            return "{'value':'failure'}";
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }
        return personalinfoJSON;
    }


    @RequestMapping(value = "heartkid/diseasequant", method = RequestMethod.POST)
    public String savediseasequant(@RequestBody RegisterDtoEntity diseasequant) {
        try {
            LOGGER.info("Referencenumber of the request :::::::::::" + diseasequant.getId());
            diseasequant.setRegistrationdate(dategenerator.dategenerator());
            if (diseasequant != null) {
                heartKidRegistrationService.saveRegistrationInformation(diseasequant);
                LOGGER.info("disease quantification added SUCCESS");
            } else
                System.out.println("Disease quantification is null");
        } catch (NullPointerException nullpointer) {
            return "No values present in the diserase quantification object: " + nullpointer.toString();
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return "User succesfully created! (id =" + diseasequant.getId() + ")";
    }


    @RequestMapping(value = "heartkid/burdendisease", method = RequestMethod.POST)
    public String saveburdendisease(@RequestBody RegisterDtoEntity burdendisease) {
        try {
            burdendisease.setRegistrationdate(dategenerator.dategenerator());

            if (burdendisease != null) {
                heartKidRegistrationService.saveRegistrationInformation(burdendisease);
                System.out.println("SUCCESS");
            } else
                System.out.println("burden disease is null");
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return "User succesfully created! (id =" + burdendisease.getId() + ")";
    }


    @RequestMapping(value = "heartkid/producteducation", method = RequestMethod.POST)
    public String producteducation(@RequestBody RegisterDtoEntity producteduentity) {
        try {
            producteduentity.setRegistrationdate(dategenerator.dategenerator());
            if (producteduentity != null) {
                heartKidRegistrationService.saveRegistrationInformation(producteduentity);
                System.out.println("SUCCESS");
            } else
                System.out.println("burden disease is null");
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return "User succesfully created! (id =" + producteduentity.getId() + ")";
    }


    @RequestMapping(value = "heartkid/qualitycare", method = RequestMethod.POST)
    public String qualitycare(@RequestBody RegisterDtoEntity qualitycareentity) {
        try {

            if (qualitycareentity != null) {
                qualitycareentity.setRegistrationdate(dategenerator.dategenerator());

                heartKidRegistrationService.saveRegistrationInformation(qualitycareentity);
                System.out.println("SUCCESS");
            } else
                LOGGER.info("quality care is null");
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return "User succesfully created! (id =" + qualitycareentity.getId() + ")";
    }


    @RequestMapping(value = "heartkid/outhospital", method = RequestMethod.POST)
    public String outhospital(@RequestBody RegisterDtoEntity outhospitalentity) throws MessagingException {
        String response = null;
        try {
            if (outhospitalentity != null) {
                outhospitalentity.setRegistrationdate(dategenerator.dategenerator());
                outhospitalentity.setSurveystatus("success");
                RegisterDtoEntity resp = heartKidRegistrationService.saveRegistrationInformation(outhospitalentity);
                System.out.println("MAIL TO USER " + resp.getEmail());
                if (resp.getSurveystatus() == "success") {
                    response = "success";
                    try {
                        //mailingclass.mailingservice(resp.getEmail());
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
    public String updaterecordheartkid(@RequestParam(value = "updaterecordref", defaultValue = "") String updaterecordref, @RequestBody RegisterDtoEntity registration) {
        try {
            if (registration != null) {
                heartKidRegistrationService.updateRegistrationRecord(registration);
                System.out.println("SUCCESS");
            } else
                LOGGER.info("registration is null");
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return "User record edited successfully ! (reference Id is = " + updaterecordref + ")";
    }


    @RequestMapping(value = "heartkid/sendmail", method = RequestMethod.GET)
    public String sendmail() {


        String response1 = mailingclass.mailingservice("nirmakumar@yahoo.com");
        return response1;
    }
}