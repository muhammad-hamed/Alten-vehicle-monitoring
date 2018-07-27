package com.alten.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
public class VehicleStatsDTO {
	
	@ApiModelProperty(notes = "The number of Online vehicle.")
	private long onlineCount;
	
	@ApiModelProperty(notes = "The number of Offline vehicle.")
	private long offlineCount;
	
	@ApiModelProperty(notes = "The total number of vehicles.")
	private long total;

}
