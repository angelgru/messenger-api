package com.example.messengerapi.repositories

import com.example.messengerapi.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

    fun findByPhoneNumber(phoneNumber: String): User?
}