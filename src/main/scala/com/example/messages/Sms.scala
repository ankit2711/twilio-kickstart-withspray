package com.example.messages
import com.example.messages.Sms._

object Sms {
  def isMobileNumberValid(s: String) = s.matches("(00|\\+|)[1-9][0-9]{1,14}")
  def requireNonEmpty(value: String, name: String) = require(value.trim.nonEmpty, name + " should not be empty")
  def requireNonEmpty(value: Option[String], name: String) = require(value.exists(_.trim.nonEmpty), name + " should not be empty")

  val gsm7bitRegEx = """^[A-Za-z0-9 \r\n@£$¥èéùìòÇØøÅå\u0394_\u03A6\u0393\u039B\u03A9\u03A0\u03A8\u03A3\u0398\u039EÆæßÉ!"#$%&amp;'()*+,\-./:;&lt;=&gt;?¡ÄÖÑÜ§¿äöñüà^{}\\\[~\]|\u20AC]*$"""
  def isGsm7Bit(s: String) = s.matches(gsm7bitRegEx)
}

case class Sms(requester: String, mobileNumber: String, message: String) {
  requireNonEmpty(requester, "requester")
  require(isMobileNumberValid(mobileNumber), "Mobile number is invalid. It should be in E.164 format. like 31612345678, 0031612345678 or +31612345678. No other characters are allowed.")
  requireNonEmpty(message, "message")
  require(message.length <= 160, "Message length may not be longer than 160 characters")

  def messageIsGsm7Bit = isGsm7Bit(message)
  def numberWithoutPrefix = {
    if (mobileNumber.startsWith("00")) mobileNumber.stripPrefix("00")
    else if (mobileNumber.startsWith("+")) mobileNumber.stripPrefix("+")
    else mobileNumber
  }
}
