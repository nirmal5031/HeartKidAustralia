package com.heartkid.controller;

import java.util.ArrayList;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.heartkid.model.entity.CreateAdminUser;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.CreateAdminRepository;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.service.LoginService;
import com.heartkid.service.SearchService;
import com.heartkid.util.EncrptDecryptPassword;
import com.heartkid.util.ExcelBuilder;

@RestController
public class AdminController {
	@Autowired
	private CreateAdminRepository createadminrepository;
	@Autowired
	private ExcelBuilder excelView;
	@Autowired
	private SearchService searchservice;
	@Autowired
	private HeartkidRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeartkidController.class);

	List<RegisterDtoEntity> searchdto = new ArrayList<RegisterDtoEntity>();

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

	@RequestMapping(value = "heartkid/getrecordinexcel", method = RequestMethod.POST)
	public List<RegisterDtoEntity> getRecordHeartkidExcel(@RequestBody RegisterDtoEntity searchentity) {

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
			repository.deleteUsersByRefNumber(deleterecordref);
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
				userexist = createadminrepository.adminuserexist(createuser.getUsername());
				LOGGER.info("=====userexist====" + userexist);
			}
			if (userexist == 0) {
				String encpass = EncrptDecryptPassword.encrypt(createuser.getPassword());
				if (encpass != null)
					createuser.setPassword(encpass);
				createadminrepository.save(createuser);
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


	List<CreateAdminUser> listadminuser = new ArrayList<CreateAdminUser>();

	@RequestMapping(value = "heartkid/listadminuser", method = RequestMethod.GET)
	public List<CreateAdminUser> listAdminUser() {

		listadminuser = (List<CreateAdminUser>) createadminrepository.findAll();

		return listadminuser;

	}

	@RequestMapping(value = "heartkid/deleteadminuser/{delusername}", method = RequestMethod.GET)
	public int deleteAdminUser(@PathVariable(value = "delusername") String delusername) {
		int response = 0;
		try {

			response = createadminrepository.deleteUsersadmin(delusername);
			LOGGER.info("Delete response ----" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	List<CreateAdminUser> fetchadminuser = new ArrayList<CreateAdminUser>();

	@RequestMapping(value = "heartkid/fetchadminuser/{username}", method = RequestMethod.GET)
	public List<CreateAdminUser> fetchAdminUser(@PathVariable(value = "username") String username) {
		try {
			LOGGER.info("FETCH ADMIN USER" + username);

			fetchadminuser = (List<CreateAdminUser>) createadminrepository.findOne(username);
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
			String regcountnumber = repository.patientcount();
			String carercount = repository.carercount();
			String Lovedone = repository.lovedcount();
			reportcount = regcountnumber;
			carerCountNo = carercount;
			LovedoneNo = Lovedone;
			reportcount = reportcount.concat(",");
			reportcount = reportcount.concat(carerCountNo.concat(","));
			reportcount = reportcount.concat(LovedoneNo);
		} 
		catch (Exception ex) {
			return "Error creating the entry: " + ex.toString();
		}
		
		return reportcount;
	}

	RegisterDtoEntity getuserdetails = new RegisterDtoEntity();

	@RequestMapping(value = "heartkid/getuserdetails/{id}", method = RequestMethod.GET)
	public RegisterDtoEntity getUserDetails(@PathVariable(value = "id") String id) {
		try {
			long d = Long.parseLong(id);
			getuserdetails = repository.findOne(d);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return getuserdetails;
	}
}
