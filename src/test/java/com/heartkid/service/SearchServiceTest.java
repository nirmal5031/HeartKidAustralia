package com.heartkid.service;

import com.heartkid.ServiceTest;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.util.RequestBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class SearchServiceTest extends ServiceTest {

    @Autowired
    HeartkidRepository heartkidRepository;

    @Autowired
    SearchService searchService;

    @Test
    public void searchRecord() throws Exception {

        RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        assertThat(searchService.searchheartkid(registerDtoEntity), hasSize(1));
    }

    @Test
    public void returnNoRecords() throws Exception {

        assertThat(searchService.searchheartkid(null), hasSize(0));
    }
}
