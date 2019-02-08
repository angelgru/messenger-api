package com.example.messengerapi.models

import com.example.messengerapi.listeners.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
@EntityListeners(UserListener::class)
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @DateTimeFormat
        var createdAt: Date = Date.from(Instant.now()),

        @Column(unique = true)
        @Size(min = 2)
        var username: String = "",

        @Size(min = 8, max = 15)
        @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
        var phoneNumber: String = "",

        @Size(min = 60, max = 60)
        var password: String = "",

        var status: String = "Available",

        @Pattern(regexp = "\\A(activated|deactivated)\\z")
        var accountStatus: String = "activated"
) {
//    Collection of sent messages
    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    private var sentMessages: Collection<Message>? = null

//    Collection of received messages
    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var receivedMessages: Collection<Message>? = null

}