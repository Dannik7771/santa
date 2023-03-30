package com.example.santa.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table
data class ParticipantResponse(
    @Id
    val id: Long,
    val name: String,
    val wish: String?
)
