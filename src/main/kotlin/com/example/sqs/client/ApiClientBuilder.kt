package com.example.sqs.client

import com.amazonaws.opensdk.config.ConnectionConfiguration
import com.amazonaws.opensdk.config.TimeoutConfiguration
import com.example.apigateway.ApiQueueGateway
import com.example.apigateway.model.Empty
import com.example.apigateway.model.PostMessagesRequest
import com.example.apigateway.model.PostMessagesResult


/**
 * APIクライアントビルダ。コンストラクタは不可視。
 */
class ApiClientBuilder private constructor() {

    /**
     * API呼び出し実装のシングルトンクラス。
     * @sample ApiClientBuilder.initSdkClient() ...
     */
    companion object ApiQueueGatewayImpl {
        private lateinit var apiQueueGateway: ApiQueueGateway
        private lateinit var postMessagesResult: PostMessagesResult

        /**
         * SDKを初期化する
         */
        fun initSdkClient(): ApiQueueGatewayImpl {
            apiQueueGateway = ApiQueueGateway.builder()
                .connectionConfiguration(
                    ConnectionConfiguration().maxConnections(100).connectionMaxIdleMillis(1000)
                )
                .timeoutConfiguration(
                    TimeoutConfiguration().httpRequestTimeout(3000).totalExecutionTimeout(10000).socketTimeout(2000)
                )
                .build()
            return ApiQueueGatewayImpl
        }

        /**
         * リクエストを発行する。
         */
        fun postMessage(messageBody: String, messageGroupId: String, messageDuplicationId: String): ApiQueueGatewayImpl {
            // Construct a request
            val request: PostMessagesRequest = PostMessagesRequest().apply {
                messageBody(messageBody)
                messageDeduplicationId(messageDuplicationId)
                messageGroupId(messageGroupId)
            }

            try {
                println("${concatThreadInfo()} : API Gatewayにリクエストを送信します。")
                postMessagesResult = apiQueueGateway.postMessages(request)
                println("${concatThreadInfo()} : API Gatewayにリクエストを送信しました。")
            }catch (e: Exception) {
                println("${concatThreadInfo()} : 例外が発生しました。")
                e.printStackTrace()
            }
            return ApiQueueGatewayImpl
        }

        /**
         * APIレスポンスのモデルを標準出力する
         */
        fun peak(): ApiQueueGatewayImpl {
            println("${concatThreadInfo()} : ${this.postMessagesResult.empty}")
            return ApiQueueGatewayImpl
        }

        /**
         * APIレスポンスのHTTPボディを返却する。
         * API Gatewayクライアントのインスタンスを破棄し、メモリを開放します。
         * @return Empty?
         */
        fun returnEmpty(): Empty? {
            apiQueueGateway.shutdown()
            return this.postMessagesResult.empty
        }
    }

}