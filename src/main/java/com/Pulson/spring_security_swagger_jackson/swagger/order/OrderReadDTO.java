package com.Pulson.spring_security_swagger_jackson.swagger.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OrderReadDTO", description = "DTO of Order object")
public class OrderReadDTO {

    Long id;
    @ApiModelProperty(value = "Name of customer which placed the order", example = "Joe", dataType = "java.lang.String")
    String customerName;

    @ApiModelProperty(value = "Map with all ordered products. Product name is key and product price is value", example = "i5 12600KF: 1450")
    Map<String, BigDecimal> productNameToPrice;

    @ApiModelProperty(value = "Timestamp of orders creation date", example = "creationDate: 12:15:22 24-11-2021", dataType = "java.time.LocalDateTime")

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd-MM-yyyy")
    //@JsonSerialize(using = JsonLocalDateTimeSerializer.class)//same effect
    //Brak potrzeby stosowania tych adnotacji - skonfigurowany ObjectMapper w JSONConfig załatwia sprawę <3
    LocalDateTime creationDate;
}
