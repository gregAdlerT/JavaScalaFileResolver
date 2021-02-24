package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.dao.DataModel;
import com.example.demo.dao.DataRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
//@ContextConfiguration(classes = DemoApplication.class)
class FileResolverImplTest {
    @Autowired
    private   DataRepo repo;
    private String content="Some, text, was, uploaded";
    private  RestTemplate restTemplateMock=Mockito.mock(RestTemplate.class);
    private  FileResolver service=new FileResolverImpl("",restTemplateMock, repo);


    @Test
    void uploadFile() {
        Mockito.when(restTemplateMock.getForObject("", String.class)).thenReturn(content);
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