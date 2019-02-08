package com.example.messengerapi.repositories

import com.example.messengerapi.models.Message
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository: CrudRepository<Message, Long> {

    fun findByConversationId(conversationId: Long): List<Message>
}