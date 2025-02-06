package com.assignment.management.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    @JsonProperty("usid")
    private String usid;

    @JsonProperty("studentClass")
    @NotNull(message = "Class cannot be null or blank")
    private int studentClass;

    @JsonProperty("contact")
    private String contact;

    @JsonProperty("age")
    @NotNull(message = "Age cannot be null or blank")
    private int age;
}
