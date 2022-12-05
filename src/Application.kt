package com.autumnsun

import com.autumnsun.model.message
import com.google.gson.JsonObject
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.sessions.*
import io.ktor.websocket.*
import io.ktor.http.cio.websocket.*
import java.time.*
import io.ktor.auth.*
import io.ktor.client.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.*
import io.ktor.util.Identity.encode
import kotlinx.serialization.encodeToString
import java.util.*
import javax.swing.text.html.parser.Parser

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(io.ktor.websocket.WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
       json()
    }



    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/session/increment") {
            val session = call.sessions.get<MySession>() ?: MySession()
            call.sessions.set(session.copy(count = session.count + 1))
            call.respondText("Counter is ${session.count}. Refresh to increment.")
        }

        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket ("/successPage"){
            println("login page!")
            val thisConnection = Connection(this)
            connections += thisConnection
            val context="{\"is_request\":true,\"id\":8,\"params\"" +
                    ":[{\"username\":\"demo\",\"password\":\"123456\"}],\"method\":\"Authenticate\"}"


            try {

                send("You are connected! ")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    send(WebSocket.loginResponse)
                    if(receivedText.equals(WebSocket.loginRequest, ignoreCase = true)){
                        send(
                            WebSocket.loginResponse
                        )
                    }else{
                        kotlinx.serialization.json.Json.encodeToString(message(false,"invalid message",0))
                    }
               }

            } catch (e: Exception) {
                println(e.localizedMessage)
            }
        }


        webSocket ("/homePage"){
            println("home page!")
            val thisConnection = Connection(this)
            connections += thisConnection

            try {
                send("You are connected! ")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    if(receivedText.equals(WebSocket.homeRequest, ignoreCase = true)) {
                        send(
                            WebSocket.homeResponse
                        )
                    }else{
                        kotlinx.serialization.json.Json.encodeToString(message(false,"invalid message",0))
                    }
                }


            } catch (e: Exception) {
                println(e.localizedMessage)
            }

        }

        webSocket ("/ligthingPage") {
            println("lighting page!")
            val thisConnection = Connection(this)
            connections += thisConnection

            try {

                send("You are connected! ")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    if(receivedText.equals(WebSocket.lightRequest, ignoreCase = true)) {
                        send(
                            WebSocket.lightResponse
                        )
                    }else
                        kotlinx.serialization.json.Json.encodeToString(message(false,"invalid message",0))
                }


            } catch (e: Exception) {
                println(e.localizedMessage)
            }

        }
        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}

data class MySession(val count: Int = 0)

