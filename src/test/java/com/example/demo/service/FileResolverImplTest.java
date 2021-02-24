package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.dao.DataModel;
import com.example.demo.dao.DataRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import scala.math.Equiv;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Greg Adler
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class FileResolverImplTest {

    private static String content="Some, text, was, uploaded";
    
    @Autowired
    private   DataRepo repo;
    @MockBean
    private RestTemplate restTemplateMock;
    @Autowired
    private  FileResolver service;
    
    @BeforeEach
    public void init(){
        Mockito.when(restTemplateMock.getForObject("http://fff", String.class)).thenReturn(content);
    }


    @Test
    void uploadFile() {
        service.uploadFile();
    }

    @Test
    void processFile() {       
        service.processFile();
        Iterable<DataModel> actual = repo.findAll();
        String[] contentArr = content.split(", ");
        int ind=0;
        for (DataModel dataModel : actual) {
            Assert.assertEquals(dataModel.getContent(), contentArr[ind]);
            ind++;
        }
        Assert.assertEquals(ind,contentArr.length);
    }
}