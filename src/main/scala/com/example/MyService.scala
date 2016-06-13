package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class MyServiceActor extends Actor with MyService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val accountSid = "AC11fff088b4b447c02ff1f9ac3fc768c5"
  val token = "44f97e75bf1ae8cc79e57e8d02de2734"


  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    } ~
      path("voice") {
        get {
          complete {
            <Response>
              <Say>I just responded to a phone call. Huzzah!</Say>
              <Play loop="2">
                https://dl.dropboxusercontent.com/u/11489766/twilio/elearning/epic_sax.mp3
              </Play>
            </Response>
          }
        }
      } ~ path("sms") {
      get {
        complete {
          <Response>
            <Message>Hallo daar</Message>
          </Response>
        }
      }
    } ~ path("send-sms") {
      get {
        complete {
          SendSMS.sendMessage
        }
      }
    }
}