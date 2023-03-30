package com.example.santa.service

import com.example.santa.model.Participant
import com.example.santa.repository.GroupRepository
import com.example.santa.repository.ParticipantRepository
import org.springframework.stereotype.Service

@Service
class ParticipantService(
    val participantRepository: ParticipantRepository,
    val groupRepository: GroupRepository
) {
    fun postParticipant(id: Long, participant: Participant): Long {
        val group = groupRepository.findById(id).get()
        group.participants.add(participant)
        return groupRepository.save(group).participants.last().id
    }

    fun deleteParticipant(id: Long, participantId: Long) {
        return participantRepository.deleteById(participantId)
    }
}