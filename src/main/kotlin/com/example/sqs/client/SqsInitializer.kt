package com.example.sqs.client

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder

class SqsInitializer {

    companion object Sqs {
        private var basicAWSCredentials: AWSCredentials = ProfileCredentialsProvider("profile snozaki-private").credentials

        val amazonSQS: AmazonSQS = AmazonSQSClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials))
            .build()
    }

}