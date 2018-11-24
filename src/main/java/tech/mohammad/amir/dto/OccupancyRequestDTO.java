package tech.mohammad.amir.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Represents information related to Occupancy Optimization Request")
public class OccupancyRequestDTO {
    @ApiModelProperty("Total number of premium class room available")
    private Integer freePremiumRooms;

    @ApiModelProperty("Total number of economy class room available")
    private Integer freeEconomyRooms;
}
