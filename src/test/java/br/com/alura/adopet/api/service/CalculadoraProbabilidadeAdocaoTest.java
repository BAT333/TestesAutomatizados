package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {
    @Test
    @DisplayName("Teste De Probabilida de Adocao 02 ALTA ")
    void cenario01(){
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                4,
                "Cinza",
                4.0f
        ), abrigo);
        CalculadoraProbabilidadeAdocao calcular = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao adocao = calcular.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA,adocao);

    }
    @Test
    @DisplayName("Teste De Probabilida de Adocao 02 MEDIA ")
    void cenario02(){
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                10,
                "Cinza",
                4.0f
        ), abrigo);
        CalculadoraProbabilidadeAdocao calcular = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao adocao = calcular.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA,adocao);

    }
    @Test
    @DisplayName("Teste De Probabilida de Adocao 03 BAIXA ")
    void cenario03(){
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                15,
                "Cinza",
                15.0f
        ), abrigo);
        CalculadoraProbabilidadeAdocao calcular = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao adocao = calcular.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA,adocao);

    }


}