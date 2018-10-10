package com.validator.testEncryption;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

/**
 * This sample illustrates basic usage of the various Blob Primitives provided
 * in the Storage Client Library including CloudBlobContainer, CloudBlockBlob
 * and CloudBlobClient.
 */
public class BlobBasics {
    
    
    public String runSamples() throws Exception {
        // Create a blob client for interacting with the blob service
        CloudBlobClient blobClient = new BlobClientProvider().getBlobClientReference();
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
        return "Successfully uploaded the blob in container:[" + containerName + "] with file name:" + blockBlobName;
    }
    
    
    public String testEncryptionPerform() throws Exception {
        
        
        System.out.println("\tCreating sample files 128M in size for upload demonstration.");
        File sampleFile = createTempLocalFile("blockblob-", ".tmp", (10000 * 1024) );
        
        // Create a blob client for interacting with the blob service
        CloudBlobClient blobClient = new BlobClientProvider().getBlobClientReference();
        String containerName = "testencrypt";
        CloudBlobContainer container = blobClient.getContainerReference(containerName);
        
        System.out.println("\n\tStart Uploading blockbloc ...");
        SimpleDateFormat df = new SimpleDateFormat("MMddHHmmss");
        String blockBlobName = "blockblob" + df.format(new Date());
        CloudBlockBlob blockBlob1 = container.getBlockBlobReference(blockBlobName);
        
        long start = Calendar.getInstance().getTimeInMillis();
        
        blockBlob1.uploadFromFile(sampleFile.getPath());
        
        long end = Calendar.getInstance().getTimeInMillis();
        
        double spentTime = (double) end - start;
        
        
        System.out.println("Successfully uploaded the blob.");
        String retStr = "Successfully uploaded the blob in container:[" + containerName + "] with file name:" + blockBlobName;
        retStr += "<br> File size: 10.0M <br>Spent time: " + Math.round(spentTime) + "ms";
        return retStr;
    }
    
    /**
     * Creates and returns a temporary local file for use by the sample.
     *
     * @param tempFileNamePrefix The prefix string to be used in generating the file's name.
     * @param tempFileNameSuffix The suffix string to be used in generating the file's name.
     * @param bytesToWrite The number of bytes to write to file.
     * @return The newly created File object
     */
    public static File createTempLocalFile(String tempFileNamePrefix, String tempFileNameSuffix, int bytesToWrite) throws IOException, IllegalArgumentException{
        
        File tempFile = null;
        FileOutputStream tempFileOutputStream = null;
        try {
            // Create the temporary file
            tempFile = File.createTempFile(tempFileNamePrefix, tempFileNameSuffix);
            
            // Write random bytes to the file if requested
            Random random = new Random();
            byte[] randomBytes = new byte[4096];
            tempFileOutputStream = new FileOutputStream(tempFile);
            while (bytesToWrite > 0) {
                random.nextBytes(randomBytes);
                tempFileOutputStream.write(randomBytes, 0, (bytesToWrite > 4096) ? 4096 : bytesToWrite);
                bytesToWrite -= 4096;
            }
        }
        finally {
            // Close the file output stream writer
            if (tempFileOutputStream != null) {
                tempFileOutputStream.close();
            }
            
            // Set the temporary file to delete on exit
            if (tempFile != null) {
                tempFile.deleteOnExit();
            }
        }
        return tempFile;
    }
    
    
}
