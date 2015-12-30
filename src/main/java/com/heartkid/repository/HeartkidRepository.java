package com.heartkid.repository;

import com.heartkid.model.entity.RegisterDtoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface HeartkidRepository extends CrudRepository<RegisterDtoEntity, Long> {

    @Query("select l from RegisterDtoEntity l where l.referencenumber = :referencenumber")
    RegisterDtoEntity findByreferenceNum(
            @Param(value = "referencenumber") final String referencenumber);

    @Modifying
    @Query("delete from RegisterDtoEntity u where  u.referencenumber = :deleterecordref")
    void deleteUsersByRefNumber(@Param(value = "deleterecordref") final String deleterecordref);

    @Query("select count(usertype) as pga from RegisterDtoEntity u where  usertype='patient'")
    String patientcount();

    @Query("select count(usertype) as pga from RegisterDtoEntity u where  usertype='carer'")
    String carercount();

    @Query("select count(usertype) as pga from RegisterDtoEntity u where  usertype='Filling this form on behalf of your passed loved one'")
    String lovedcount();

    @Query("select usertype, count(usertype),monthname(registrationdate),year(registrationdate),registrationdate from RegisterDtoEntity u where usertype in('Patient') group by monthname(registrationdate),year(registrationdate) order by cast(registrationdate as date) asc ")
    public List patientbarcount();

    @Query("select usertype, count(usertype),monthname(registrationdate),year(registrationdate),registrationdate from RegisterDtoEntity u where usertype='Carer' group by monthname(registrationdate),year(registrationdate) order by cast(registrationdate as date) asc ")
    public List carerbarcount();

    @Query("select usertype, count(usertype),monthname(registrationdate),year(registrationdate),registrationdate from RegisterDtoEntity u where usertype like '%loved one%' group by monthname(registrationdate),year(registrationdate) order by cast(registrationdate as date) asc ")
    public List lovedbarcount();


    @Query("select count(u) from RegisterDtoEntity u")
    int registationcount();

    @Query("select count(u) from RegisterDtoEntity u where u.referencenumber=:referencenumber")
    int referenceexist(@Param(value = "referencenumber") final String referencenumber);

    @Query("SELECT t FROM RegisterDtoEntity t WHERE (:referencenumber IS NULL OR t.referencenumber= :referencenumber) AND (:surgeryheld IS NULL OR t.surgeryheld= :surgeryheld) AND (:usertype IS NULL OR t.usertype= :usertype) AND (:surgerydelay IS NULL OR t.surgerydelay= :surgerydelay) AND (:countrybirth IS NULL OR t.countrybirth= :countrybirth) AND (:surveystatus IS NULL OR t.surveystatus= :surveystatus) AND (:trvlsurg IS NULL OR t.trvlsurg= :trvlsurg) AND (:state IS NULL OR t.state= :state) AND (:anxietycond IS NULL OR t.anxietycond= :anxietycond) AND  (:surgerydelaycount IS NULL OR t.surgerydelaycount= :surgerydelaycount) AND (:heartconds IS NULL OR t.heartconds= :heartconds) AND (:conditioncalld IS NULL OR t.conditioncalld= :conditioncalld) AND (:ethnicity IS NULL OR t.ethnicity= :ethnicity) AND  (:sex IS NULL OR t.sex= :sex) AND (:age1 IS NULL OR t.age BETWEEN :age1 AND :age2) AND  (:contctviaemail IS NULL OR t.contctviaemail= :contctviaemail) AND  (:contctviaphone IS NULL OR t.contctviaphone= :contctviaphone)")
    public List<RegisterDtoEntity> findbysearchheartkid(@Param("referencenumber") String referencenumber, @Param("countrybirth") String countrybirth, @Param("surveystatus") String surveystatus, @Param("surgeryheld") String surgeryheld, @Param("surgerydelay") String surgerydelay, @Param("trvlsurg") String trvlsurg, @Param("anxietycond") String anxietycond, @Param("state") String state, @Param("surgerydelaycount") String surgerydelaycount, @Param("heartconds") String heartconds, @Param("usertype") String usertype, @Param("conditioncalld") String conditioncalld, @Param("ethnicity") String ethnicity, @Param("sex") String sex, @Param("age1") String age1, @Param("age2") String age2, @Param("contctviaemail") String contctviaemail, @Param("contctviaphone") String contctviaphone);

}
