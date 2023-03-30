package com.example.santa.model

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table
data class Grouppa (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val description: String?,

    @OneToMany(cascade=[CascadeType.ALL])
    @JoinColumn
    @OnDelete(action=OnDeleteAction.CASCADE)
    val participants: MutableList<Participant> = mutableListOf()
)