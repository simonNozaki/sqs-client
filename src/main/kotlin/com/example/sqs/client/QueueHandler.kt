package com.example.sqs.client

import com.amazonaws.services.sqs.model.CreateQueueRequest
import com.amazonaws.services.sqs.model.MessageAttributeValue
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.amazonaws.services.sqs.model.SendMessageResult

private val attribute: MutableMap<String, String> = mutableMapOf()
private val messageAttributes: MutableMap<String, MessageAttributeValue> = mutableMapOf()
private val createQueueRequest: CreateQueueRequest = CreateQueueRequest("first-queue.fifo").withAttributes(attribute)

fun main(args: Array<String>) {

    attribute["FifoQueue"] = "true"
    messageAttributes["AttributeOne"] = MessageAttributeValue().withStringValue("Message").withDataType("String")

    try {
        val queueUrl: String = SqsInitializer.amazonSQS.createQueue(createQueueRequest).queueUrl

        println("キューにメッセージを送信します。")

        val sendMessageFifoQueue: SendMessageRequest = SendMessageRequest()
            .withQueueUrl(queueUrl)
            .withMessageBody("client first message")
            .withMessageGroupId("group-1")
            .withMessageDeduplicationId("group-1")
            .withMessageAttributes(messageAttributes)

        val sendMessageResult: SendMessageResult = SqsInitializer.amazonSQS.sendMessage(sendMessageFifoQueue)

        println("キューにメッセージを送信しました。")

        val sequenceNumber = sendMessageResult.sequenceNumber
        val messageId = sendMessageResult.messageId
        println("SendMessage succeed with messageId $messageId, sequence number $sequenceNumber")
    } catch (e: Exception) {
        println("error occurred ... $e.message")
        e.printStackTrace()
    }
}