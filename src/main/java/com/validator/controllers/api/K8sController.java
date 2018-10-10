package com.validator.controllers.api;

import com.validator.beans.*;
import com.validator.beans.configuration.GlobalConfiguration;
import com.validator.beans.configuration.K8sAliCloudConfiguration;
import com.validator.beans.configuration.K8sAzureConfiguration;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@ComponentScan("com.validator.beans")
public class K8sController {
    
    private K8sValidationResult k8sValidationResultAzure;
    private K8sValidationResult k8sValidationResultAliCloud;
    private K8sAzureConfiguration k8sConfigurationAzure;
    private K8sAliCloudConfiguration k8sConfigurationAliCloud;
    private GlobalConfiguration globalConfiguration;
    private RestTemplate restTemplate;
    
    public K8sController(
                         K8sValidationResult k8sValidationResult,
                         K8sValidationResult k8sValidationResultAliCloud,
                         K8sAzureConfiguration k8sConfigurationAzure,
                         K8sAliCloudConfiguration k8sConfigurationAliCloud,
                         GlobalConfiguration globalConfiguration) {
        this.restTemplate = new RestTemplate();
        this.k8sValidationResultAzure = k8sValidationResult;
        this.k8sValidationResultAliCloud = k8sValidationResultAliCloud;
        this.k8sConfigurationAliCloud = k8sConfigurationAliCloud;
        this.k8sConfigurationAzure = k8sConfigurationAzure;
        this.globalConfiguration = globalConfiguration;
    }
    
    /**
     * This is in the environment that we're going to access and validate
     *
     * @param request
     * @return
     */
    @RequestMapping("/api/v1/ping/azure/k8s")
    K8sValidationResult azureK8s(HttpServletRequest request) {
        
        if (this.globalConfiguration.getLocation().equals(this.globalConfiguration.getAzure())) {
            this.k8sValidationResultAzure.setResponseTime(0);
            this.k8sValidationResultAzure.setLocation(this.globalConfiguration.getAzure());
            return this.k8sValidationResultAzure;
        }
        
        try {
            LocalDateTime oldDate = LocalDateTime.now();
            
            this.k8sValidationResultAzure =
            this.restTemplate.getForObject(
                                           this.k8sConfigurationAzure.getApiUrl(), K8sValidationResult.class);
            Duration duration = Duration.between(oldDate, LocalDateTime.now());
            
            this.k8sValidationResultAzure.setResponseTime(duration.toMillis());
            
        } catch (RestClientException ex) {
            this.k8sValidationResultAzure.setAccessibility(false);
        }
        
        return this.k8sValidationResultAzure;
    }
    
    /**
     * This is in the environment that we're going to access and validate
     *
     * @param request
     * @return
     */
    @GetMapping("/api/v1/ping/alicloud/k8s")
    K8sValidationResult alicloudK8s(HttpServletRequest request) {
        
        this.k8sValidationResultAliCloud.setResponseTime(0);
        this.k8sValidationResultAliCloud.setLocation(this.globalConfiguration.getAlicloud());
        
        if (this.globalConfiguration.getLocation().equals(this.globalConfiguration.getAlicloud())) {
            return this.k8sValidationResultAliCloud;
        }
        
        try {
            
            LocalDateTime oldDate = LocalDateTime.now();
            
            this.k8sValidationResultAliCloud =
            this.restTemplate.getForObject(
                                           this.k8sConfigurationAliCloud.getApiUrl(), K8sValidationResult.class);
            Duration duration = Duration.between(oldDate, LocalDateTime.now());
            
            this.k8sValidationResultAliCloud.setResponseTime(duration.toMillis());
            
        } catch (RestClientException ex) {
            this.k8sValidationResultAliCloud.setAccessibility(false);
        }
        return this.k8sValidationResultAliCloud;
    }
}
