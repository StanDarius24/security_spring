package com.stannis.backendauth.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
data class UserDto(

    val id: Long,

    val login: String,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val token: String,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    val password: CharArray

)

