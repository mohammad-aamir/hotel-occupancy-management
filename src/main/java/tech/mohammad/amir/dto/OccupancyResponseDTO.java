package tech.mohammad.amir.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@ApiModel(description = "Represents information related to Occupancy Optimization Response")
public class OccupancyResponseDTO {
    @ApiModelProperty("Total number of premium class rooms occupied")
    private Integer usagePremium;

    @ApiModelProperty("Total number of economy class rooms occupied")
    private Integer usageEconomy;

    @ApiModelProperty("Total earning from premium class rooms")
    private Integer totalEarningPremium;

    @ApiModelProperty("Total earning from economy class rooms")
    private Integer totalEarningEconomy;
}
