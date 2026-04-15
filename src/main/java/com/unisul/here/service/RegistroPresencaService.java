package com.unisul.here.service;

import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.model.RegistroPresenca;
import com.unisul.here.repository.CadastroUsuarioRepository;
import com.unisul.here.repository.RegistroPresencaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Service
public class RegistroPresencaService {

    private final RegistroPresencaRepository presencaRepository;
    private final CadastroUsuarioRepository usuarioRepository;

    // Coordenadas da R. Antônio Dib Mussi, 366
    private static final double LAT_ALVO = -27.5935;
    private static final double LON_ALVO = -48.5528;
    private static final double TOLERANCIA_DISTANCIA = 100.0;

    public RegistroPresencaService(RegistroPresencaRepository presencaRepository,
                                   CadastroUsuarioRepository usuarioRepository) {
        this.presencaRepository = presencaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public RegistroPresenca registrarPresenca(Long usuarioId, LocalDateTime dataHoraRegistro, double latUser, double lonUser) {
        ValidarHoraRegistro(dataHoraRegistro);

        ValidarLocalizacaoUsuario(latUser, lonUser);

        CadastroUsuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        RegistroPresenca registro = new RegistroPresenca();
        registro.setHoraRegistro(dataHoraRegistro);
        registro.setUsuario(usuario);

        return presencaRepository.save(registro);
    }

    public List<RegistroPresenca> listarPorUsuario(Long usuarioId) {
        return presencaRepository.findByUsuarioId(usuarioId);
    }

    private void ValidarHoraRegistro(LocalDateTime dataHoraRegistro){
        LocalTime agora = dataHoraRegistro.toLocalTime();

        LocalTime inicioPermitido = LocalTime.of(19, 50);
        LocalTime limiteRegistro = LocalTime.of(21, 0);

        if (agora.isBefore(inicioPermitido)) {
            throw new RuntimeException("Registro ainda não liberado. Aguarde até 19:50.");
        }

        if (agora.isAfter(limiteRegistro)) {
            throw new RuntimeException("Tempo de registro encerrado.");
        }
    }

    private void ValidarLocalizacaoUsuario(double latUser, double lonUser){
        double distancia = CalcularDistancia(latUser, lonUser);

        if (distancia > TOLERANCIA_DISTANCIA) {
            throw new RuntimeException("Usuário fora do local permitido para registro.");
        }
    }

    private double CalcularDistancia(double lat1, double lon1) {
        double raioTerra = 6371e3;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(RegistroPresencaService.LAT_ALVO);
        double deltaPhi = Math.toRadians(RegistroPresencaService.LAT_ALVO - lat1);
        double deltaLambda = Math.toRadians(RegistroPresencaService.LON_ALVO - lon1);

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return raioTerra * c;
    }
}