package com.example.santa.controller

import com.example.santa.model.Grouppa
import com.example.santa.model.GroupResponse
import com.example.santa.model.Participant
import com.example.santa.model.ParticipantResponse
import com.example.santa.service.GroupService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class GroupController(
    val groupService: GroupService
) {
    @Operation(summary = "Добавить новую группу")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Новая группа успешно добавлена")])
    @PostMapping("/group")
    fun postGroup(@RequestBody grouppa: Grouppa): Long {
        return groupService.postGroup(grouppa)
    }

    @Operation(summary = "Получить краткую информацию о группах")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Получение информации о группах успешно")])
    @GetMapping("/groups")
    fun getGroups(): List<GroupResponse> {
        return groupService.getGroups()
    }

    @Operation(summary = "Получить полную информацию о группе")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Получение информации о группе успешно")])
    @GetMapping("/group/{id}")
    fun getGroup(@PathVariable("id") id: Long): Grouppa {
        return groupService.getGroup(id)
    }

    @Operation(summary = "Изменить информацию о группе")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Изменение информации о группе успешно")])
    @PutMapping("/group/{id}")
    fun putGroup(@PathVariable("id") id: Long, @RequestBody grouppa: Grouppa): Grouppa {
        return groupService.putGroup(id, grouppa)
    }

    @Operation(summary = "Удалить информацию о группе")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Удаление информации о группе успешно")])
    @DeleteMapping("/group/{id}")
    fun deleteGroup(@PathVariable("id") id: Long) {
        return groupService.deleteGroup(id)
    }

    @Operation(summary = "Провести жеребьёвку")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Жеребьёвка успешно проведена"),
        ApiResponse(responseCode = "409", description = "Проведение жеребьёвки невозможно")])
    @PostMapping("/group/{id}/toss")
    fun tossGroup(@PathVariable("id") id: Long): ResponseEntity<List<Participant>> {
        val result = groupService.tossGroup(id)
        return if (result!=null) ResponseEntity.ok(result)
        else ResponseEntity(HttpStatus.CONFLICT)
    }

    @Operation(summary = "Получить информацию для конкретного участника группы, кому он дарит подарок")
    @ApiResponses(value= [ApiResponse(responseCode = "200", description = "Получение информации для конкретного участника группы, кому он дарит подарок успешно")])
    @GetMapping("/group/{groupId}/participant/{participantId}/recipient")
    fun getRecipient(@PathVariable("groupId") groupId: Long, @PathVariable("participantId") participantId: Long): ParticipantResponse? {
        return groupService.getRecipient(groupId, participantId)
    }
}