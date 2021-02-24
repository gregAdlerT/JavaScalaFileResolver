package com.example.demo.service

import scala.collection.JavaConverters.asJavaCollectionConverter
import com.example.demo.dao.{DataModel, DataRepo}
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

/**
 * @author Greg Adler
 */
@Service
   class FileResolverImpl(@Value("${file.url}")
                                  val fileUrl:String,
                                  val restTemplate: RestTemplate,
                                  val repo: DataRepo) extends FileResolver {


  var content: String = null
  
  override def uploadFile(): Unit = {
    content = restTemplate.getForObject(fileUrl, classOf[String])
    println(content)
  }

  @Transactional
  override def processFile(): Unit = {
    val listOfModels = content.split(", ").map { s =>
      var model = new DataModel()
      model.setContent(s)
      model
    }.toList
    listOfModels.foreach(m=>repo.save(m))
  }
}


