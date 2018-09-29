package com.validator.testEncryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validator.testEncryption.aliyun.TestAliEncryptionController;

@RestController
public class TestEncryptionController {
	
	@Autowired
	private TestAliEncryptionController aliController;

  @RequestMapping("/testEncryptionDemo")
  public String testEncryption() throws Exception {
    BlobBasics basicSamples = new BlobBasics();
    return basicSamples.runSamples();
  }
  
  
  @RequestMapping("/testEncryption")
  public String testAzureEncryptionPerform() throws Exception {

	String result ="<h1>Azure Data Encryption Test</h1>";
    BlobBasics azureTest = new BlobBasics();
    result += azureTest.testEncryptionPerform();
    
    result += "<br>************************************************************************************";
    result += "<h1>ALi Data Encryption Test</h1>";
    result += aliController.testAliEncryption();
    return result;
  }
  
  @RequestMapping("/testAzureEncryptionPerform")
  public String testEncryptionPerform() throws Exception {

    BlobBasics basicSamples = new BlobBasics();
    return basicSamples.testEncryptionPerform();
  }
}
