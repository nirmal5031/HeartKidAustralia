package com.heartkid.controller;

import com.heartkid.RestControllerTest;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.util.RequestBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HeartkidControllerTest extends RestControllerTest {

    public static final String REGISTRATION_COUNT_URL = "/heartkid/regcount";
    public static final String REFERENCE_NO_GENERATOR_URL = "/heartkid/referencegen";
    public static final String PERSONAL_INFO_URL = "/heartkid/personalinfo";
    public static final String DISEASE_QUANT_URL = "/heartkid/diseasequant";
    public static final String BURDEN_DISEASE_URL = "/heartkid/burdendisease";
    public static final String PRODUCT_EDUCATION_URL = "/heartkid/producteducation";
    public static final String QUALITY_CARE_URL = "/heartkid/qualitycare";
    public static final String OUT_HOSPITAL_URL = "/heartkid/outhospital";

    @Before
    public void setUp() throws Exception {
        initializeMockMvc();
    }

    @Test
    public void returnRegistrationCount() throws Exception {
        mockMvc.perform(get(REGISTRATION_COUNT_URL)).andExpect(status().isOk());
    }

    @Test
    public void returnRegistrationCountNotNull() throws Exception {
        MvcResult result = mockMvc.perform(get(REGISTRATION_COUNT_URL))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void returnBadRequestForPOST() throws Exception {
        mockMvc.perform(post(REGISTRATION_COUNT_URL))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void returnReferenceNumber() throws Exception {
        mockMvc.perform(get(REFERENCE_NO_GENERATOR_URL))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void returnReferenceNumberNotNull() throws Exception {
        MvcResult result = mockMvc.perform(get(REFERENCE_NO_GENERATOR_URL))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result.getResponse().getContentAsString());
    }

    @Test
    public void returnBadRequestForReferenceNoGeneratorForPOST() throws Exception {
        mockMvc.perform(post(REFERENCE_NO_GENERATOR_URL))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void saveDiseaseQuantInformation() throws Exception {

        mockMvc.perform(post(DISEASE_QUANT_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void returnBadRequestSaveDiseaseQuantInformationForEmpty() throws Exception {

        mockMvc.perform(post(DISEASE_QUANT_URL)
                .content(json(""))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnBadRequestSaveDiseaseQuantInformationViolation() throws Exception {

        mockMvc.perform(post(DISEASE_QUANT_URL)
                .content(json("-1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnErrorSaveDiseaseQuantInformationForGET() throws Exception {

        mockMvc.perform(get(DISEASE_QUANT_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void savePersonalInformation() throws Exception {

        RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();

        mockMvc.perform(post(PERSONAL_INFO_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void returnBadRequestSavePersonalInformationForEmpty() throws Exception {

        mockMvc.perform(post(PERSONAL_INFO_URL)
                .content(json(""))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnBadRequestSavePersonalInformationViolation() throws Exception {

        mockMvc.perform(post(PERSONAL_INFO_URL)
                .content(json("-1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnErrorSavePersonalInformationForGET() throws Exception {

        mockMvc.perform(get(PERSONAL_INFO_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void saveBurdenDiseaseInformation() throws Exception {

        mockMvc.perform(post(BURDEN_DISEASE_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void returnBadRequestSaveBurdenDiseaseInformationForEmpty() throws Exception {

        mockMvc.perform(post(BURDEN_DISEASE_URL)
                .content(json(""))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnBadRequestSaveBurdenDiseaseInformationViolation() throws Exception {

        mockMvc.perform(post(BURDEN_DISEASE_URL)
                .content(json("-1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnErrorSaveBurdenDiseaseInformationForGET() throws Exception {

        mockMvc.perform(get(BURDEN_DISEASE_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void saveProductEducationInformation() throws Exception {

        mockMvc.perform(post(PRODUCT_EDUCATION_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void returnBadRequestSaveProductEducationInformationForEmpty() throws Exception {

        mockMvc.perform(post(PRODUCT_EDUCATION_URL)
                .content(json(""))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnBadRequestSaveProductEducationInformationViolation() throws Exception {

        mockMvc.perform(post(PRODUCT_EDUCATION_URL)
                .content(json("-1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnErrorSaveProductEducationInformationForGET() throws Exception {

        mockMvc.perform(get(PRODUCT_EDUCATION_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void saveQualityCareInformation() throws Exception {

        mockMvc.perform(post(QUALITY_CARE_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void returnBadRequestSaveQualityCareInformationForEmpty() throws Exception {

        mockMvc.perform(post(QUALITY_CARE_URL)
                .content(json(""))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnBadRequestSaveQualityCareInformationViolation() throws Exception {

        mockMvc.perform(post(QUALITY_CARE_URL)
                .content(json("-1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnErrorSaveQualityCareInformationForGET() throws Exception {

        mockMvc.perform(get(QUALITY_CARE_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void saveOutHospitalInformation() throws Exception {

        mockMvc.perform(post(OUT_HOSPITAL_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void returnBadRequestSaveOutHospitalInformationForEmpty() throws Exception {

        mockMvc.perform(post(OUT_HOSPITAL_URL)
                .content(json(""))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnBadRequestSaveOutHospitalInformationViolation() throws Exception {

        mockMvc.perform(post(OUT_HOSPITAL_URL)
                .content(json("-1"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void returnErrorSaveOutHospitalInformationForGET() throws Exception {

        mockMvc.perform(get(OUT_HOSPITAL_URL)
                .content(json(new RequestBuilder().defaultValues()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());

    }
}
