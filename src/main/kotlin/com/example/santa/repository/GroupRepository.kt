package com.example.santa.repository

import com.example.santa.model.Grouppa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository: JpaRepository<Grouppa, Long> {
}