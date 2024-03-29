package com.example.messengerapi.models

import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "conversations")
class Conversation(
        @ManyToOne
        @JoinColumn(name = "sender_id", referencedColumnName = "id")
        var sender: User? = null,

        @ManyToOne
        @JoinColumn(name = "recipient_id", referencedColumnName = "id")
        var recipient: User? = null,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @DateTimeFormat
        val createdAt: Date = Date.from(Instant.now())
) {
    @OneToMany(mappedBy = "conversation", targetEntity = Message::class)
    var messages: Collection<Message>? = null
}

