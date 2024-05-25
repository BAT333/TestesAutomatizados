package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComAdocaoEmAndamentoTest {
    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento andamento;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Spy
    private List<Adocao> adocaos = new ArrayList<>();
    @Mock
    private Adocao adocaos2;
    @Mock
    private Adocao adocaos3;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;
    @Mock
    private Tutor tutor;

    @Test
    @DisplayName("SE ESSE PET JA ESTA EM PROCESSO DE ADOÇÃO TEM LANÇA ERRO")
    void cenario01(){
        BDDMockito.given(adocaoRepository.findAll()).willReturn(adocaos);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(adocaos2.getTutor()).willReturn(tutor);
        BDDMockito.given(adocaos2.getStatus()).willReturn(StatusAdocao.AGUARDANDO_AVALIACAO);
        adocaos.add(adocaos2);
        adocaos.stream().filter(adocao -> adocao.getTutor()==tutor)
                .filter(adocao -> adocao.getStatus()==StatusAdocao.AGUARDANDO_AVALIACAO);
        Assertions.assertThrows(ValidacaoException.class,()->andamento.validar(dto));
    }
    @Test
    @DisplayName("SE ESSE PET JA ESTA EM PROCESSO DE ADOÇÃO TEM NÃO LANÇA ERRO")
    void cenario02(){
        BDDMockito.given(adocaoRepository.findAll()).willReturn(adocaos);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(adocaos3.getTutor()).willReturn(tutor);
        BDDMockito.given(adocaos3.getStatus()).willReturn(StatusAdocao.REPROVADO);
        adocaos.add(adocaos3);
        adocaos.stream().filter(adocao -> adocao.getTutor()==tutor)
                .filter(adocao -> adocao.getStatus()==StatusAdocao.AGUARDANDO_AVALIACAO);
        Assertions.assertDoesNotThrow(()->andamento.validar(dto));

    }

}