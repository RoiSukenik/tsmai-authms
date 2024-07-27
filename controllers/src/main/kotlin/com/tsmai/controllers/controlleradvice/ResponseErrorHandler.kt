package com.tsmai.controllers.controlleradvice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.tsmai.authms.services.exceptions.UserExistsError

@ControllerAdvice
open class ResponseErrorHandler {

    @ExceptionHandler(UserExistsError::class)
    protected fun handleUserExistsException(e: UserExistsError, webRequest: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = "Could not register user: ${e.customMessage}"
        return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyOfResponse)
    }
}