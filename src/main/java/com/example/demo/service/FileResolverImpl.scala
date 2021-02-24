package com.example.demo.service

import java.io.PrintWriter

import com.example.demo.dao.{DataModel, DataRepo}
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import scala.io.Source

/**
 * @author Greg Adler
 */
@Service
   class FileResolverImpl(@Value("${file.url}")
                                  val fileUrl:String,
                                  val restTemplate: RestTemplate,
                                  val repo: DataRepo) extends FileResolver {


  var filePath: String = "./content.txt"
  
  override def uploadFile(): Unit = {
    val content = restTemplate.getForObject(fileUrl, classOf[String])
    Some(new PrintWriter(filePath)).foreach{p => p.write(content); p.close()}
  }

  @Transactional
  override def processFile(): Unit = {
    val listOfModels = Source.fromFile(filePath).mkString.split(", ").map { s =>
      var model = new DataModel()
      model.setContent(s)
      model
    }.toList
    listOfModels.foreach(m=>repo.save(m))
  }
}


