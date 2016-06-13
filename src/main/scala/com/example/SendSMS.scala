package com.example

import java.util

import com.twilio.sdk.TwilioRestClient
import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair

/**
  * Created by janwillem on 13/06/16.
  */
object SendSMS {
  val ACCOUNT_SID = "AC11fff088b4b447c02ff1f9ac3fc768c5"
  val AUTH_TOKEN = "44f97e75bf1ae8cc79e57e8d02de2734"
  val client: TwilioRestClient = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
  var params: util.List[NameValuePair] = new util.ArrayList[NameValuePair]
  params.add(new BasicNameValuePair("Body", "Ha vuile gek!"));
  params.add(new BasicNameValuePair("To", "+31648175991"));
  params.add(new BasicNameValuePair("From", "+32460202167"));

  def sendMessage = {
    val messageFactory = client.getAccount().getMessageFactory()
    val message = messageFactory.create(params)
    message.getSid()
  }

}
