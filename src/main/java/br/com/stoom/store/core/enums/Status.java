package br.com.stoom.store.core.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@AllArgsConstructor
@Getter
public enum Status {

	ACTIVE("ACTIVE", "Ativo"), INACTIVE("INACTIVE", "Inativo");

	private final String value;

	private final String label;

	public static boolean exists(String str) {
		return Arrays.stream(Status.values())
				.anyMatch(status -> status.value.equalsIgnoreCase(str));
	}

}
