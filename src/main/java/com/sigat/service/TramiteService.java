package com.sigat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sigat.dto.ResolverTramiteRequestDTO;
import com.sigat.dto.TramiteRequestDTO;
import com.sigat.dto.VoBoRequestDTO;
import com.sigat.model.Contrato;
import com.sigat.model.EstadoContrato;
import com.sigat.model.EstadoTramite;
import com.sigat.model.Tramite;
import com.sigat.model.Usuario;
import com.sigat.repository.ContratoRepository;
import com.sigat.repository.EstadoContratoRepository;
import com.sigat.repository.EstadoTramiteRepository;
import com.sigat.repository.TipoTramiteRepository;
import com.sigat.repository.TramiteRepository;
import com.sigat.repository.UsuarioRepository;

@Service
public class TramiteService {

    private static final Long ESTADO_PENDIENTE    = 1L;
    private static final Long ESTADO_VOBO         = 2L;
    private static final Long ESTADO_RECHAZADO    = 3L;
    private static final Long ESTADO_RESUELTO     = 4L;

    private static final String TIPO_NUEVO_CONTRATO = "Nuevo Contrato";

    private static final String ROL_ADMIN    = "ADMIN";
    private static final String ROL_DIRECTOR = "DIRECTOR";
    private static final String ROL_TECNICO  = "TECNICO";

    private final TramiteRepository tramiteRepository;
    private final EstadoTramiteRepository estadoTramiteRepository;
    private final ContratoRepository contratoRepository;
    private final TipoTramiteRepository tipoTramiteRepository;
    private final UsuarioRepository usuarioRepository;
    private final EstadoContratoRepository estadoContratoRepository;

    public TramiteService(TramiteRepository tramiteRepository,
                          EstadoTramiteRepository estadoTramiteRepository,
                          ContratoRepository contratoRepository,
                          TipoTramiteRepository tipoTramiteRepository,
                          UsuarioRepository usuarioRepository,
                          EstadoContratoRepository estadoContratoRepository) {
        this.tramiteRepository = tramiteRepository;
        this.estadoTramiteRepository = estadoTramiteRepository;
        this.contratoRepository = contratoRepository;
        this.tipoTramiteRepository = tipoTramiteRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoContratoRepository = estadoContratoRepository;
    }

    public List<Tramite> getAll() {
        return tramiteRepository.findAll();
    }

    public Tramite getById(Long id) {
        return tramiteRepository.findById(id).orElseThrow(()-> new RuntimeException("Tramite no encontrado: " + id));
    }

    public List<Tramite> getByContratoId(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato).orElseThrow(()-> new RuntimeException("Contrato no encontrado: " + idContrato));
        return tramiteRepository.findByContrato(contrato);
    }

    public List<Tramite> getPendientes() {
        EstadoTramite estadoPendiente = estadoTramiteRepository.findById(ESTADO_PENDIENTE).orElseThrow(()-> new RuntimeException("Estado 'Pendiente' no encontrado"));
        return tramiteRepository.findByEstadoTramite(estadoPendiente);
    }

    public List<Tramite> getPendientesVoBo() {
        EstadoTramite estadoVoBo = estadoTramiteRepository.findById(ESTADO_VOBO).orElseThrow(()-> new RuntimeException("Estado 'VoBo Aprobado' no encontrado"));
        return tramiteRepository.findByEstadoTramite(estadoVoBo);
    }

    public Tramite crear(TramiteRequestDTO dto, String username) {
        EstadoTramite estadoPendiente = estadoTramiteRepository.findById(ESTADO_PENDIENTE).orElseThrow(()-> new RuntimeException("Estado 'Pendiente' no encontrado"));

        Tramite tramite = new Tramite();
        tramite.setDatosPropuestos(dto.getDatosPropuestos());
        tramite.setObservacionesSolicitud(dto.getObservacionesSolicitud());
        tramite.setFechaSolicitud(LocalDateTime.now());
        tramite.setEstadoTramite(estadoPendiente);
        tramite.setUsuarioResolutor(null);
        tramite.setFechaResolucion(null);
        tramite.setObservacionesResolucion(null);

        tramite.setContrato(contratoRepository.findById(dto.getContratoId()).orElseThrow(()-> new RuntimeException("Contrato no encontrado: " + dto.getContratoId())));
        tramite.setTipoTramite(tipoTramiteRepository.findById(dto.getTipoTramiteId()).orElseThrow(()-> new RuntimeException("Tipo de tramite no encontrado: " + dto.getTipoTramiteId())));
        tramite.setUsuarioSolicitante(usuarioRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Usuario no encontrado: " + username)));

        return tramiteRepository.save(tramite);
    }

    // Solo el DIRECTOR puede ejecutar este metodo (controlado ademas por Spring Security en el endpoint)
    public Tramite vobo(Long idTramite, VoBoRequestDTO dto, String username) {
        Tramite tramite = getById(idTramite);

        // Solo se puede dar VoBo a tramites en estado Pendiente
        if (!tramite.getEstadoTramite().getIdestado_tramite().equals(ESTADO_PENDIENTE)) {
            throw new RuntimeException("Solo se puede dar VoBo a tramites en estado Pendiente");
        }

        // El estado destino debe ser VoBo Aprobado (2) o Rechazado (3)
        if (!dto.getIdEstadoNuevo().equals(ESTADO_VOBO) && !dto.getIdEstadoNuevo().equals(ESTADO_RECHAZADO)) {
            throw new RuntimeException("Estado invalido para VoBo. Use 2 (VoBo Aprobado) o 3 (Rechazado)");
        }

        Usuario director = usuarioRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Usuario no encontrado: " + username));

        String rolDirector = director.getRol().getNombre();
        if (!rolDirector.equals(ROL_DIRECTOR) && !rolDirector.equals(ROL_ADMIN)) {
            throw new RuntimeException("Solo el Director puede dar VoBo");
        }

        EstadoTramite estadoNuevo = estadoTramiteRepository.findById(dto.getIdEstadoNuevo()).orElseThrow(()-> new RuntimeException("Estado no encontrado: " + dto.getIdEstadoNuevo()));

        tramite.setEstadoTramite(estadoNuevo);

        // Si rechaza en VoBo, registra quien rechazó y las observaciones
        if (dto.getIdEstadoNuevo().equals(ESTADO_RECHAZADO)) {
            tramite.setUsuarioResolutor(director);
            tramite.setFechaResolucion(LocalDateTime.now());
            tramite.setObservacionesResolucion(dto.getObservaciones());
        }

        return tramiteRepository.save(tramite);
    }

    // OPERADOR y TECNICO pueden ejecutar este metodo (controlado por Spring Security en el endpoint)
    public Tramite resolver(Long idTramite, ResolverTramiteRequestDTO dto, String username) {
        Tramite tramite = getById(idTramite);

        Long estadoActual = tramite.getEstadoTramite().getIdestado_tramite();
        boolean requiereVobo = tramite.getTipoTramite().getRequiereVobo() != null
                && tramite.getTipoTramite().getRequiereVobo() == 1;

        // Validar que el estado actual permite resolver
        if (requiereVobo && estadoActual.equals(ESTADO_PENDIENTE)) {
            throw new RuntimeException("Este tramite requiere VoBo del Director antes de ser resuelto");
        }
        if (!estadoActual.equals(ESTADO_PENDIENTE) && !estadoActual.equals(ESTADO_VOBO)) {
            throw new RuntimeException("El tramite ya fue resuelto o rechazado anteriormente");
        }

        // El estado destino debe ser Resuelto (4) o Rechazado (3)
        if (!dto.getIdEstadoNuevo().equals(ESTADO_RESUELTO) && !dto.getIdEstadoNuevo().equals(ESTADO_RECHAZADO)) {
            throw new RuntimeException("Estado invalido para resolver. Use 4 (Resuelto) o 3 (Rechazado)");
        }

        Usuario resolutor = usuarioRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Usuario no encontrado: " + username));

        String rolResolutor = resolutor.getRol().getNombre();
        boolean esNuevoContrato = TIPO_NUEVO_CONTRATO.equalsIgnoreCase(tramite.getTipoTramite().getNombre());

        // Los tramites de tipo "Nuevo Contrato" solo los puede resolver el TECNICO
        if (esNuevoContrato && !rolResolutor.equals(ROL_TECNICO) && !rolResolutor.equals(ROL_ADMIN)) {
            throw new RuntimeException("Solo el Tecnico puede resolver tramites de tipo Nuevo Contrato");
        }

        // El DIRECTOR no puede resolver (ya sea que llegue por aqui o por error de configuracion)
        if (rolResolutor.equals(ROL_DIRECTOR)) {
            throw new RuntimeException("El Director no puede resolver tramites, solo dar VoBo");
        }

        EstadoTramite estadoNuevo = estadoTramiteRepository.findById(dto.getIdEstadoNuevo()).orElseThrow(()-> new RuntimeException("Estado no encontrado: " + dto.getIdEstadoNuevo()));

        tramite.setEstadoTramite(estadoNuevo);
        tramite.setUsuarioResolutor(resolutor);
        tramite.setFechaResolucion(LocalDateTime.now());
        tramite.setObservacionesResolucion(dto.getObservacionesResolucion());
        tramiteRepository.save(tramite);

        // Si es Nuevo Contrato y se resuelve favorablemente, activar el contrato
        if (esNuevoContrato && dto.getIdEstadoNuevo().equals(ESTADO_RESUELTO)) {
            EstadoContrato estadoActivo = estadoContratoRepository.findByNombre("Activo")
                    .orElseThrow(() -> new RuntimeException("Estado 'Activo' no encontrado en estado_contrato"));
            Contrato contrato = tramite.getContrato();
            contrato.setEstadoContrato(estadoActivo);
            contratoRepository.save(contrato);
        }

        return tramite;
    }
}
