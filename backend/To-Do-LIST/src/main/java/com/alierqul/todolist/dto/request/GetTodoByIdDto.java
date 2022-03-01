package com.alierqul.todolist.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetTodoByIdDto {
    @NotNull
    long todoID;
}
