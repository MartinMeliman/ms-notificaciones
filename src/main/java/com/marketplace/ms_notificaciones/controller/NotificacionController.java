package com.marketplace.ms_notificaciones.controller;

import com.marketplace.ms_notificaciones.dto.NotificacionDTO;
import com.marketplace.ms_notificaciones.model.Notificacion;
import com.marketplace.ms_notificaciones.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Notificaciones", description = "Notificaciones transversales del marketplace EcoTrade")
@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @Operation(summary = "Obtener notificaciones de un usuario",
               description = "Retorna todas las notificaciones enviadas a un usuario específico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida correctamente")
    })
    @GetMapping("/usuario/{uid}")
    public List<Notificacion> porUsuario(
            @Parameter(description = "ID del usuario") @PathVariable Long uid) {
        return notificacionService.obtenerPorUsuario(uid);
    }

    @Operation(summary = "Enviar notificación de bienvenida",
               description = "Envía una notificación de bienvenida al usuario al registrarse")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Notificación enviada correctamente")
    })
    @PostMapping("/bienvenida")
    public ResponseEntity<Notificacion> bienvenida(@RequestBody NotificacionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.enviarBienvenida(dto));
    }

    @Operation(summary = "Enviar notificación de pago aprobado",
               description = "Notifica al usuario que su pago fue aprobado")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Notificación enviada correctamente")
    })
    @PostMapping("/pago")
    public ResponseEntity<Notificacion> pago(@RequestBody NotificacionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.enviarPago(dto));
    }

    @Operation(summary = "Enviar notificación de envío actualizado",
               description = "Notifica al usuario sobre el estado de su envío")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Notificación enviada correctamente")
    })
    @PostMapping("/envio")
    public ResponseEntity<Notificacion> envio(@RequestBody NotificacionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.enviarEnvio(dto));
    }

    @Operation(summary = "Enviar notificación de pedido confirmado",
               description = "Notifica al usuario que su pedido fue confirmado")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Notificación enviada correctamente")
    })
    @PostMapping("/pedido")
    public ResponseEntity<Notificacion> pedido(@RequestBody NotificacionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.enviarPedido(dto));
    }
}