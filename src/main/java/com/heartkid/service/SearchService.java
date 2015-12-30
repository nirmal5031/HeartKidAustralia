package com.heartkid.service;

import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.HeartkidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    HeartkidRepository searchrepository;

    List<RegisterDtoEntity> searchvalue = new ArrayList<RegisterDtoEntity>();
    String referencenumber;
    String usertype;
    String surgeryheld;
    String surgerydelay;
    String countrybirth;
    String surveystatus;
    String trvlsurg;
    String sex;
    String anxietycond;
    String state;
    String contctviaemail;
    String contctviaphone;
    String surgerydelaycount;
    String heartconds;
    String ethnicity;
    String conditioncalld;
    String age;
    String agesplit[];
    String age1;
    String age2;

    public List<RegisterDtoEntity> searchheartkid(RegisterDtoEntity searchentity) {
        try {
            if (searchentity != null) {
                referencenumber = searchentity.getReferencenumber();
                usertype = searchentity.getUsertype();
                surgeryheld = searchentity.getSurgeryheld();
                surgerydelay = searchentity.getSurgerydelay();
                countrybirth = searchentity.getCountrybirth();
                surveystatus = searchentity.getSurveystatus();
                trvlsurg = searchentity.getTraveldistdoc();
                anxietycond = searchentity.getAnxietycond();
                state = searchentity.getState();
                contctviaemail = searchentity.getContctviaemail();
                contctviaphone = searchentity.getContctviaphone();
                surgerydelaycount = searchentity.getSurgerydelaycount();
                heartconds = searchentity.getHeartconds();
                conditioncalld = searchentity.getConditioncalld();
                ethnicity = searchentity.getEthnicity();
                sex = searchentity.getSex();
                age = searchentity.getAge();
                if (age == " - ") {
                    System.out.println("NULLE");
                    age1 = null;
                    age2 = null;
                } else {
                    String[] agesplit = age.split("-", 0);
                    String ageval1 = agesplit[0];
                    String ageval2 = agesplit[1];
                    if (ageval1.equalsIgnoreCase("empty")) {
                        age1 = null;
                        age2 = null;

                    } else {
                        age1 = ageval1;
                        age2 = ageval2;
                    }
                }
            }

            if (referencenumber == "" && surveystatus == null && surgeryheld == null && surgerydelay == null
                    && trvlsurg == null && countrybirth == null && ethnicity == null && conditioncalld == null
                    && heartconds == null && surgerydelaycount == null && anxietycond == null && state == null
                    && usertype == null && sex == null && age1 == null && age2 == null && contctviaemail == null
                    && contctviaphone == null) {
                searchvalue = (List<RegisterDtoEntity>) searchrepository.findAll();
            } else {
                if (referencenumber == "")
                    referencenumber = null;

                searchvalue = searchrepository.findbysearchheartkid(referencenumber, countrybirth, surveystatus,
                        surgeryheld, surgerydelay, trvlsurg, anxietycond, state, surgerydelaycount, heartconds,
                        usertype, conditioncalld, ethnicity, sex, age1, age2, contctviaemail, contctviaemail);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return searchvalue;
    }

}
