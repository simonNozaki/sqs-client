package com.example.sqs.client

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder

/**
 * アプリケーション名
 */
const val APP_NAME: String = "sqs-client"

/**
 * AWSアカウントプロファイル名。
 */
const val AWS_PROFILE_NAME: String = "snozaki-private"

/**
 * SQSインスタンス初期化クラス。
 */
class SqsInitializer {

    companion object Sqs {
        // a credential with a local profile
        private var basicAWSCredentials: AWSCredentials = ProfileCredentialsProvider(AWS_PROFILE_NAME).credentials

        // build a SQS client
        val amazonSQS: AmazonSQS = AmazonSQSClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials))
            .build()
    }

}