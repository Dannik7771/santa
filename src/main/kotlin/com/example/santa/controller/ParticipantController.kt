package com.example.santa.controller

import com.example.santa.model.Participant
import com.example.santa.service.ParticipantService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
class ParticipantController(
    val participantService: ParticipantService
) {
    @Operation(summary = "Добавить участника в группу по id")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Участник успешно добавлен в группу")])
    @PostMapping("/group/{id}/participant")
    fun postParticipant(@PathVariable("id") id: Long, @RequestBody participant: Participant): Long {
        return participantService.postParticipant(id, participant)
    }

    @Operation(summary = "Удалить участника из группы")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Удаление участника из группы успешно")])
    @DeleteMapping("/group/{id}/participant/{participantId}")
    fun deleteGroup(@PathVariable("id") id: Long, @PathVariable("participantId") participantId: Long) {
        return participantService.deleteParticipant(id, participantId)
    }
}