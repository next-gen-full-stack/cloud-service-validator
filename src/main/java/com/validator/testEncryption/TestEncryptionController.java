package com.validator.testEncryption;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEncryptionController {

  @RequestMapping("/testEncryption")
  public String testEncryption() throws Exception {

    BlobBasics basicSamples = new BlobBasics();
    return basicSamples.runSamples();
  }
  
  @RequestMapping("/testEncryptionPerform")
  public String testEncryptionPerform() throws Exception {

    BlobBasics basicSamples = new BlobBasics();
    return basicSamples.testEncryptionPerform();
  }
}
