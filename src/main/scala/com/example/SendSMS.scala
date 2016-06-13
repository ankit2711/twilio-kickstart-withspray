package com.example

import java.util

import com.example.messages.Sms
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

  def sendMessage(sms: Sms): String = {
    val messageFactory = client.getAccount().getMessageFactory()
    var params: util.List[NameValuePair] = new util.ArrayList[NameValuePair]
    params.add(new BasicNameValuePair("Body", sms.message));
    params.add(new BasicNameValuePair("To", sms.mobileNumber));
    params.add(new BasicNameValuePair("From", sms.requester));
    val message = messageFactory.create(params)
    message.getSid()


  }

}
