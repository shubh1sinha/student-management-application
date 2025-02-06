package com.assignment.management.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateStudentDTO {

    @NotNull(message = "Student cannot be null or blank")
    @JsonProperty("student")
    private StudentDTO student;

    @NotBlank(message = "Fields to be updated cannot be blank")
    @JsonProperty("fieldsToBeUpdated")
    private Set<String> fieldsToBeUpdated;
}
