package com.zako.webetu.auth.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class UserAuth(
    @PrimaryKey @SerialName("userId") val id: Int = 0,
    val uuid: String = "",
    val token: String = "",
    @SerialName("idIndividu") val idPersonal: Int = 0,
    @SerialName("etablissementId") val idOrganization: Int = 0,
    @SerialName("userName") val registrationNumber: String = "",
    @SerialName("expirationDate") val expirationDate: String = "",
)
