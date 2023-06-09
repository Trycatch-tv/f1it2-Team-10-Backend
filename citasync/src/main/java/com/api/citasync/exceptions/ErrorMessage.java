package com.api.citasync.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * Clase que representa el mensaje de error.
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorMessage (String campo, String mensaje){

}
