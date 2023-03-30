package com.example.santa.service

import com.example.santa.model.Grouppa
import com.example.santa.model.GroupResponse
import com.example.santa.model.Participant
import com.example.santa.model.ParticipantResponse
import com.example.santa.repository.GroupRepository
import com.example.santa.repository.ParticipantRepository
import org.springframework.stereotype.Service

@Service
class GroupService(
    val groupRepository: GroupRepository,
    val participantRepository: ParticipantRepository
) {
    fun postGroup(grouppa: Grouppa): Long {
        return groupRepository.save(grouppa).id
    }

    fun getGroups(): List<GroupResponse> {
        return groupRepository.findAll().map{mapToGroupResponse(it)}
    }

    fun mapToGroupResponse(grouppa: Grouppa): GroupResponse {
        return GroupResponse(grouppa.id, grouppa.name, grouppa.description)
    }

    fun mapToParticipantResponse(participant: Participant): ParticipantResponse {
        return ParticipantResponse(participant.id, participant.name, participant.wish)
    }

    fun getGroup(id: Long): Grouppa {
        val group = groupRepository.findById(id).get()
        //if
        group.participants.map { mapToParticipantResponse(it) }
        return group
    }

    fun putGroup(id: Long, grouppa: Grouppa): Grouppa {
        val oldGroup = groupRepository.findById(id).get()
        return groupRepository.save(Grouppa(id,grouppa.name, grouppa.description, oldGroup.participants))
    }

    fun deleteGroup(id: Long) {
        groupRepository.deleteById(id)
    }

    fun tossGroup(id: Long): List<Participant>? {
        val group = groupRepository.findById(id).get()
        if (group.participants.size < 3) {
            return null
        }
        else {
            val result: MutableList<Participant> = mutableListOf()
            val parts = group.participants
            for (i in parts.indices) {
                val newPart = parts[i]
                val recPart = parts[(i+1).mod(parts.size)]
                result.add(
                    Participant(newPart.id, newPart.name, newPart.wish,
                    ParticipantResponse(recPart.id, recPart.name, recPart.wish)
                    )
                )
            }
            return result
        }
    }

    fun getRecipient(groupId: Long, participantId: Long): ParticipantResponse? {
        val recipient = participantRepository.findById(participantId).get().recipient
        return recipient?.let { ParticipantResponse(it.id, it.name, it.wish) }
    }
}