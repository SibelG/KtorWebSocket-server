package com.autumnsun

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
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.http.ContentType.Application.Json
import java.util.*

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
        gson {
        }
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

            try {

                send("You are connected! ")
                for (frame in incoming) {


                        send(

                            "{\"id\":792,\"params\":" +
                                    "[\"demo\"],\"method\":\"OnAuthenticated\",\"error\":null,\"is_request\":true}"
                        )
                        //it.session.send(textWithUsername)

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
                    send(
                        "{\"id\":5,\"params\":[{\"data\":[{\"id\":" +
                                "\"0516e98c-012d-4487-9007-9cb4cebb0de5\",\"name\"" + ":" +
                                "\"Panel\r\nKombi\",\"type_id\":\"7\",\"bridge_device_id\":\"b01073b2-f142-4a79-907e-a90f0b061290\",\"current\r\n_value\":0,\"slot\":0,\"is_active\":true,\"temperature_settings\":" +
                                "{\"has_heating\":true,\"has_cooling\":true,\"bridge_device_id\":\"b01073b2-f142-4a79-907e-a90f0b061290\",\"virtual_control_id\":\"\",\"inp\r\nut_id\":\"2e7069aa-283b-4fbc-9ff5-b61985ec70f8\",\"is_mode_heating\":true,\"whole\":24,\"fraction\r\n\":0},\"area_id\":\"14a46e23-ca1a-4d4a-9b26-9c548a5605e8\",\"parameters\":{\"default_value\":0,\"\r\noutput_number\":0,\"should_output_reverse\":false,\"should_remember_last_value\":false}},{\"id\":\r\n\"a2830d60-ddff-4dad-8f3d-dfca0ded2462\",\"name\":\"Panel\r\nLamba\",\"type_id\":\"1\",\"bridge_device_id\":\"b01073b2-f142-4a79-907e-a90f0b061290\",\"current\r\n_value\":1,\"slot\":1,\"is_active\":true,\"area_id\":\"14a46e23-ca1a-4d4a-9b26-9c548a5605e8\",\"par\r\nameters\":{\"default_value\":0,\"end_time\":\"\",\"is_notification\":false,\"output_number\":99,\"should_\r\noutput_reverse\":false,\"should_remember_last_value\":true,\"start_time\":\"\"}}]}],\"method\":\"GetC\r\nontrolList\"," +
                                "\"error\":null,\"is_request\":false}"
                    )
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
                    send(
                        "{\"id\":852,\"params\":[{\"entity\":" +
                                "{\"id\":\"a2830d60-ddff-4dad-8f3d-dfca0ded2462\"," +
                                "\"name\":\"Panel\r\nLamba\",\"type_id\":\"1\"," +
                                "\"bridge_device_id\":\"b01073b2-f142-4a79-907e-a90f0b061290\"," +
                                "\"current\r\n_value\":1,\"slot\":1,\"is_active\":true," +
                                "\"area_id\":\"14a46e23-ca1a-4d4a-9b26-9c548a5605e8\"," +
                                "\"par\r\nameters\":{\"default_value\":0,\"end_time\":" +
                                "\"\",\"is_notification\":false,\"output_number\":99," +
                                "\"should_\r\noutput_reverse\":false,\"should_remember_last_value\":true," +
                                "\"start_time\":\"\"}},\"type\":\"control\"}],\"\r\nmethod\":" +
                                "\"OnEntityUpdated\"," + "\"error\":null,\"is_request\":true}"
                    )
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

