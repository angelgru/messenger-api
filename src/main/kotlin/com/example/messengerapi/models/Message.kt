package com.example.messengerapi.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "messages")
class Message(
        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        var sender: User? = null,

        @ManyToOne
        @JoinColumn(name = "recipient_id", referencedColumnName = "id")
        var recipient: User? = null,

        var body: String? = "",

        @ManyToOne
        @JoinColumn(name = "conversation_id", referencedColumnName = "id")
        var conversation: Conversation? = null,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @DateTimeFormat
        var createdAt: Date = Date.from(Instant.now())
)