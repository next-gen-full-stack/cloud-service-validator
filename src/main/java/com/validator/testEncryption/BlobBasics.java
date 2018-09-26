package com.validator.testEncryption;

import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This sample illustrates basic usage of the various Blob Primitives provided in the Storage Client
 * Library including CloudBlobContainer, CloudBlockBlob and CloudBlobClient.
 */
public class BlobBasics {

  public String runSamples() throws Exception {
    // Create a blob client for interacting with the blob service
    CloudBlobClient blobClient = BlobClientProvider.getBlobClientReference();
    String containerName = "testencrypt";
    CloudBlobContainer container = blobClient.getContainerReference(containerName);

    File sampleFile = null;
    sampleFile = File.createTempFile("sampleFile", ".txt");
    System.out.println(">> Creating a sample file at: " + sampleFile.toString());
    Writer output = new BufferedWriter(new FileWriter(sampleFile));
    output.write("Hello Azure!");
    output.close();

    System.out.println("\n\tStart Uploading a sample file as a block blob.");
    SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
    String blockBlobName = "helloSample" + df.format(new Date());
    CloudBlockBlob blockBlob1 = container.getBlockBlobReference(blockBlobName);
    blockBlob1.uploadFromFile(sampleFile.getPath());
    System.out.println("Successfully uploaded the blob.");
    return "Successfully uploaded the blob in container:["
        + containerName
        + "] with file name:["
        + blockBlobName
        + "]";
  }
}
