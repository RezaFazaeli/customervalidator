package com.adclear.customervalidator.dto;

import com.adclear.customervalidator.utils.IpDeserializer;
import com.adclear.customervalidator.utils.TimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Long tagId;

    @NotNull
    private String userId;

    @NotNull
    @JsonDeserialize(using = IpDeserializer.class)
    @ApiModelProperty(dataType = "java.lang.String")
    private Long remoteIP;

    @NotNull
    @JsonDeserialize(using = TimestampDeserializer.class)
    @ApiModelProperty(dataType = "java.lang.Long")
    private LocalDateTime timestamp;

    private boolean isValid;

    @JsonIgnore
    public boolean isValid() {
        return isValid;
    }
}
