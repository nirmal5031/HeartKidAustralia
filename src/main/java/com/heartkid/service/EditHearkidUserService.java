package com.heartkid.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heartkid.model.dto.RegisterDto;
import com.heartkid.repository.HeartkidRepository;
@Service
public class EditHearkidUserService {

	RegisterDto registerdto =new RegisterDto();
	
	@Autowired
	HeartkidRepository registerrepo;
	
	public  RegisterDto editheartkiduser(String referencenumber)
	{
		System.out.println("Inside edituser service----");
		RegisterDto registereditdto = registerrepo.findByreferenceNum(referencenumber);
		return registereditdto;
		
	}
	/*public int updateheartkiduser(String firstname, String lastname, Long updaterecordref)
	{
		System.out.println("Inside updateheartkiduser  service----"+firstname+"------------referencenumber-------------"+updaterecordref);
		int udpatedquery = registerrepo.updateByreferenceNum(firstname,lastname,updaterecordref);
		System.out.println("registration first name ---->"+udpatedquery);
		return udpatedquery;
		
	}*/
	public int updateByreferenceNum(String firstname, String lastname, String updaterecordref) {
		// TODO Auto-generated method stub
		System.out.println("Inside updateheartkiduser  service----"+firstname+"------------referencenumber-------------"+updaterecordref);
		int udpatedquery = registerrepo.updateByreferenceNum(firstname,lastname,updaterecordref);
		System.out.println("registration first name ---->"+udpatedquery);
		return udpatedquery;
	
	}

}
