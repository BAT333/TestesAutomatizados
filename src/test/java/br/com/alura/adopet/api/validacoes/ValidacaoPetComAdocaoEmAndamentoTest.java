package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ValidacaoPetComAdocaoEmAndamentoTest {
    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento andamento;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    @DisplayName("SE ESSE PET JA ESTA EM PROCESSO DE ADOÇÃO TEM LANÇA ERRO")
    void cenario01(){
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
                        .willReturn(true);
        Assertions.assertThrows(ValidacaoException.class,()->andamento.validar(dto));
    }
    @Test
    @DisplayName("SE ESSE PET JA ESTA EM PROCESSO DE ADOÇÃO TEM NÃO LANÇA ERRO")
    void cenario02(){
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(false);
        Assertions.assertDoesNotThrow(()->andamento.validar(dto));
    }

}