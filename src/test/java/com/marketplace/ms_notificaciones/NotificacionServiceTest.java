package com.marketplace.ms_notificaciones;

import com.marketplace.ms_notificaciones.dto.NotificacionDTO;
import com.marketplace.ms_notificaciones.model.Notificacion;
import com.marketplace.ms_notificaciones.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.marketplace.ms_notificaciones.service.NotificacionService;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para NotificacionService (servicio transversal).
 * Patrón Given/When/Then con Mockito (sin BD real).
 */
@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock private NotificacionRepository notificacionRepository;
    @InjectMocks private NotificacionService notificacionService;

    private NotificacionDTO dto;

    @BeforeEach
    void setUp() {
        dto = new NotificacionDTO();
        dto.setUsuarioId(1L);
        dto.setEmail("martin@duoc.cl");
        dto.setNombre("Martin");
        dto.setExtra("12345");
    }

    @Test
    @DisplayName("obtenerPorUsuario: debería retornar notificaciones del usuario")
    void shouldReturnNotificacionesByUsuario() {
        // GIVEN
        Notificacion n = new Notificacion();
        n.setUsuarioId(1L);
        when(notificacionRepository.findByUsuarioId(1L)).thenReturn(List.of(n));
        // WHEN
        List<Notificacion> resultado = notificacionService.obtenerPorUsuario(1L);
        // THEN
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("enviarBienvenida: debería crear notificación tipo BIENVENIDA")
    void shouldSendWelcomeNotification() {
        // GIVEN
        when(notificacionRepository.save(any(Notificacion.class))).thenAnswer(i -> i.getArgument(0));
        // WHEN
        Notificacion resultado = notificacionService.enviarBienvenida(dto);
        // THEN
        assertNotNull(resultado);
        assertEquals("BIENVENIDA", resultado.getTipo());
        assertTrue(resultado.isEnviado());
        assertEquals("martin@duoc.cl", resultado.getDestinatario());
    }

    @Test
    @DisplayName("enviarPago: debería crear notificación tipo PAGO_APROBADO")
    void shouldSendPaymentNotification() {
        // GIVEN
        when(notificacionRepository.save(any(Notificacion.class))).thenAnswer(i -> i.getArgument(0));
        // WHEN
        Notificacion resultado = notificacionService.enviarPago(dto);
        // THEN
        assertEquals("PAGO_APROBADO", resultado.getTipo());
        assertTrue(resultado.isEnviado());
    }

    @Test
    @DisplayName("enviarEnvio: debería crear notificación tipo ENVIO_ACTUALIZADO")
    void shouldSendShippingNotification() {
        // GIVEN
        when(notificacionRepository.save(any(Notificacion.class))).thenAnswer(i -> i.getArgument(0));
        // WHEN
        Notificacion resultado = notificacionService.enviarEnvio(dto);
        // THEN
        assertEquals("ENVIO_ACTUALIZADO", resultado.getTipo());
    }

    @Test
    @DisplayName("enviarPedido: debería crear notificación tipo PEDIDO_CONFIRMADO")
    void shouldSendOrderNotification() {
        // GIVEN
        when(notificacionRepository.save(any(Notificacion.class))).thenAnswer(i -> i.getArgument(0));
        // WHEN
        Notificacion resultado = notificacionService.enviarPedido(dto);
        // THEN
        assertEquals("PEDIDO_CONFIRMADO", resultado.getTipo());
        assertTrue(resultado.isEnviado());
        verify(notificacionRepository, times(1)).save(any(Notificacion.class));
    }
}
