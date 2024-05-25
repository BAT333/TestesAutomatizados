package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {
    @InjectMocks
    private AbrigoService abrigoService;
    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;
    @Mock
    private Abrigo abrigo;



    @Test
    @DisplayName("Testando o listar")
    void cenario01(){
        abrigoService.listar();
        then(abrigoRepository).should().findAll();
    }
    @Test
    @DisplayName("Testando validção")
    void cenario02(){
        Assertions.assertThrows(ValidacaoException.class,()->abrigoService.carregarAbrigo(any()));
    }
}
