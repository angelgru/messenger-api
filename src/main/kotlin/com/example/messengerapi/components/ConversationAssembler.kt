package com.example.messengerapi.components

import com.example.messengerapi.helpers.objects.ConversationListVO
import com.example.messengerapi.helpers.objects.ConversationVO
import com.example.messengerapi.helpers.objects.MessageVO
import com.example.messengerapi.models.Conversation
import com.example.messengerapi.services.ConversationService
import org.springframework.stereotype.Component

@Component
class ConversationAssembler(val conversationService: ConversationService,
                            val messageAsembler: MessageAsembler) {
    fun toConversationVO(conversation: Conversation, userId: Long): ConversationVO {
        val conversationMessages: ArrayList<MessageVO> = ArrayList()
        conversation.messages?.mapTo(conversationMessages) { messageAsembler.toMessageVO(it)}
        return ConversationVO(
                conversation.id,
                conversationService.nameSecondParty(conversation, userId),
                conversationMessages)
    }

    fun toConversationListVO(conversations: ArrayList<Conversation>,
                             userId: Long): ConversationListVO {
        val conversationVOList = conversations.map { toConversationVO(it, userId) }
        return ConversationListVO(conversationVOList)
    }
}