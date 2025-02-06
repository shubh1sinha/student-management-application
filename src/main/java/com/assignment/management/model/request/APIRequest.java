package com.assignment.management.model.request;

import com.assignment.management.utility.apiModel.BaseApiRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIRequest<T> implements BaseApiRequest<T> {

    @Getter(onMethod_ = {@Override})
    private T data;

}
