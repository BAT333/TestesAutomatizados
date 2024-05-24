package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {
    @InjectMocks
    private TutorService service;
    @Mock
    private TutorRepository repository;
    @Mock
    private CadastroTutorDto dto;
    @Captor
    private ArgumentCaptor<Tutor> argumentCaptor;

    @Test
    void cadastrar() {
        BDDMockito.given(dto.nome()).willReturn("qualquer");
        BDDMockito.given(dto.telefone()).willReturn("12364585456");
        BDDMockito.given(dto.email()).willReturn("tutor@gmail.com");
        service.cadastrar(dto);

        then(repository).should().save(argumentCaptor.capture());
        var catu = argumentCaptor.getValue();
        Assertions.assertEquals("qualquer",catu.getNome());
        Assertions.assertEquals("tutor@gmail.com",catu.getEmail());
        Assertions.assertEquals("12364585456",catu.getTelefone());


    }
}