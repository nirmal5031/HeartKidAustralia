package com.heartkid.controller;

import com.heartkid.model.entity.CreateAdminUser;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.service.AdminService;
import com.heartkid.service.SearchService;
import com.heartkid.util.EncrptDecryptPassword;
import com.heartkid.util.ExcelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartkidController.class);

    List<RegisterDtoEntity> searchdto = new ArrayList<RegisterDtoEntity>();
    List<CreateAdminUser> listadminuser = new ArrayList<CreateAdminUser>();
    List<CreateAdminUser> fetchadminuser = new ArrayList<CreateAdminUser>();
    RegisterDtoEntity getuserdetails = new RegisterDtoEntity();

    @Autowired
    private ExcelBuilder excelView;

    @Autowired
    private SearchService searchservice;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "heartkid/getrecord", method = RequestMethod.POST)
    public List<RegisterDtoEntity> getRecordHeartkid(@RequestBody RegisterDtoEntity searchentity) {
        try {
            LOGGER.info("Search referen" + searchentity.getReferencenumber());
            LOGGER.info("Search userti" + searchentity.getUsertype());

            searchdto = searchservice.searchheartkid(searchentity);

        } catch (Exception ex) {
            LOGGER.info("ERROR in SEARCH" + ex.toString());
        }
        return searchdto;
    }

    @RequestMapping(value = "heartkid/downloadExcel", method = RequestMethod.POST)
    public ModelAndView downloadExcel(@RequestBody RegisterDtoEntity registerdto) {

        try {
            searchdto = searchservice.searchheartkid(registerdto);
            LOGGER.info("searchdto" + searchdto.get(0).getReferencenumber());
            // return a view which will be resolved by an excel view resolver
            if (searchdto.isEmpty()) {
                LOGGER.info("SEARCH DTO is EMPTY" + searchdto.isEmpty());
            } else {
                LOGGER.info("IS not empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView(excelView, "listheartkidusers", searchdto);
    }

    @RequestMapping(value = "heartkid/deleterecord/{deleterecordref}", method = RequestMethod.GET)
    public String deleteUsersByRefNumber(@PathVariable(value = "deleterecordref") String deleterecordref) {
        try {
            LOGGER.info("delete record---" + deleterecordref);
            adminService.deleteUserByReferenceNumber(deleterecordref);
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return "User record Deleted successfully ! (Reference Id is = " + deleterecordref + ")";
    }

    @RequestMapping(value = "heartkid/createadminuser", method = RequestMethod.POST)
    public String createAdminUser(@RequestBody CreateAdminUser createuser) {

        String status = null;
        int userexist = 0;
        try {
            if (createuser != null) {
                userexist = adminService.doesUserExist(createuser.getUsername());
                LOGGER.info("=====userexist====" + userexist);
            }
            if (userexist == 0) {
                String encpass = EncrptDecryptPassword.encrypt(createuser.getPassword());
                if (encpass != null)
                    createuser.setPassword(encpass);
                adminService.saveAdminUser(createuser);
                status = "success";
            } else {
                status = "useridexist";
            }
        } catch (Exception ex) {
            status = "fail";
            LOGGER.info("ERROR in Creating user" + ex.toString());
        }

        return status;
    }

    @RequestMapping(value = "heartkid/listadminuser", method = RequestMethod.GET)
    public List<CreateAdminUser> listAdminUser() {

        return adminService.findAllUser();

    }


    @RequestMapping(value = "heartkid/deleteadminuser/{delusername}", method = RequestMethod.GET)
    public int deleteAdminUser(@PathVariable(value = "delusername") String delusername) {
        int response = 0;
        try {
            response = adminService.deleteUseradmin(delusername);
            LOGGER.info("Delete response ----" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "heartkid/fetchadminuser/{username}", method = RequestMethod.GET)
    public List<CreateAdminUser> fetchAdminUser(@PathVariable(value = "username") String username) {
        try {
            LOGGER.info("FETCH ADMIN USER" + username);

            fetchadminuser = adminService.findAdminUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fetchadminuser;
    }

    @RequestMapping(value = "heartkid/reportcount", method = RequestMethod.GET)
    public String registrationCount1() {
        String reportcount;
        String carerCountNo;
        String LovedoneNo;
        try {
            String regcountnumber = adminService.getPatientcount();
            String carercount = adminService.getCarercount();
            String Lovedone = adminService.getLovedcount();
            reportcount = regcountnumber;
            carerCountNo = carercount;
            LovedoneNo = Lovedone;
            reportcount = reportcount.concat(",");
            reportcount = reportcount.concat(carerCountNo.concat(","));
            reportcount = reportcount.concat(LovedoneNo);
        } catch (Exception ex) {
            return "Error creating the entry: " + ex.toString();
        }

        return reportcount;
    }

    @RequestMapping(value = "heartkid/reportbarcount", method = RequestMethod.GET)
    public List patientcount() {
        LOGGER.info("bar Report Screen for the request-----> ::");
        List reportcount = new ArrayList();
        List reportccount = new ArrayList();
        List reportLovedcount = new ArrayList();
        try {
            reportcount = adminService.getPatientbarcount();
            reportccount = adminService.getCarerbarcount();
            reportLovedcount = adminService.getLovedbarcount();
        } catch (Exception ex) {
            //return "Error creating the entry;
        }

        ArrayList bargraph = new ArrayList();
        bargraph.add(reportcount);
        bargraph.add(reportccount);
        bargraph.add(reportLovedcount);
        return bargraph;
    }

    @RequestMapping(value = "heartkid/getuserdetails/{id}", method = RequestMethod.GET)
    public RegisterDtoEntity getUserDetails(@PathVariable(value = "id") String id) {
        try {
            long d = Long.parseLong(id);
            getuserdetails = adminService.findUser(d);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return getuserdetails;
    }

}
