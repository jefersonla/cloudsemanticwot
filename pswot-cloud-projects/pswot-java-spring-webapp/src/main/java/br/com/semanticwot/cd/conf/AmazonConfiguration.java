/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.semanticwot.cd.conf;

import org.springframework.context.annotation.Bean;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author nailton
 */
public class AmazonConfiguration {
    
    @Bean
    @Profile("dev")
    public AmazonS3Client s3Ninja() {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAIOSFODNN7EXAMPLE",
                "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
        AmazonS3Client newClient = new AmazonS3Client(credentials,
                new ClientConfiguration());
        newClient.setS3ClientOptions(new S3ClientOptions()
                .withPathStyleAccess(true));
        
        newClient.setEndpoint("http://localhost:9444/s3");
        return newClient;

    }
    
    @Bean
    @Profile("prod")
    public AmazonS3Client s3Amazon() {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAIOSFODNN7EXAMPLE",
                "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
        AmazonS3Client newClient = new AmazonS3Client(credentials,
                new ClientConfiguration());
        newClient.setS3ClientOptions(new S3ClientOptions()
                .withPathStyleAccess(true));
        
        return newClient;

    }
    
}
