package com.example.sqs.client

import java.util.*

/**
 * リクエスト実行情報。
 */
fun concatThreadInfo(): String = "${Date().time} [ ${Thread.currentThread().name} ] $APP_NAME ${Throwable().stackTrace[1].className}#${Throwable().stackTrace[1].methodName}"