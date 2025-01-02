package com.example.myteam.enums;

import lombok.Getter;

@Getter
public enum Position {
	POINT_GUARD("POINT_GUARD"),
    SHOOTING_GUARD("SHOOTING_GUARD"),
    SMALL_FORWARD("SMALL_FORWARD"),
    POWER_FORWARD("POWER_FORWARD"),
    CENTER("CENTER");
	
	private final String positionName;
	
	Position(String positionName) {
		this.positionName = positionName;
	}
	
	public static Position fromPositionName(String positionName) {
        for (Position position : values()) {
            if (position.positionName.equals(positionName)) {
                return position;
            }
        }
        throw new IllegalArgumentException("해당 이름의 포지션은 존재하지 않습니다: " + positionName);
    }
}
