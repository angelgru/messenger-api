package com.example.messengerapi.controllers

import com.example.messengerapi.components.UserAssembler
import com.example.messengerapi.helpers.objects.UserListVO
import com.example.messengerapi.helpers.objects.UserVO
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.UserRepository
import com.example.messengerapi.services.UserService
import com.example.messengerapi.services.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["/users"])
class UserController(val userService: UserServiceImpl,
                     val userAssembler: UserAssembler,
                     val userRepository: UserRepository) {

    @PostMapping(value = ["/registrations"])
    fun create(@Validated @RequestBody userDetails: User): ResponseEntity<UserVO> {
        val user = userService.attemptRegistration(userDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping(value = ["/{user_id"])
    fun show(@PathVariable("user_id") userId: Long): ResponseEntity<UserVO> {
        val user = userService.retrieveUserData(userId)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping(value = ["/details"])
    fun echoDetails(request: HttpServletRequest): ResponseEntity<UserVO> {
        val user = userService.retrieveUserData(request.userPrincipal.name) as User
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    fun index(request: HttpServletRequest): ResponseEntity<UserListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val users = userService.listUsers(user)
        return ResponseEntity.ok(userAssembler.toUserListVO(users))
    }

    @PutMapping
    fun update(@RequestBody updateDetails: User,
               request: HttpServletRequest): ResponseEntity<UserVO> {
        val currentUser = userRepository.findByUsername(request.userPrincipal.name) as User
        userService.updateUserStatus(currentUser, updateDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(currentUser))
    }
}