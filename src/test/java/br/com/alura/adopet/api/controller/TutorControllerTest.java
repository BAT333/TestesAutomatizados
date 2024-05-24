package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TutorControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TutorService service;

    @Autowired
    private JacksonTester<CadastroTutorDto> jsonDto;
    @Autowired
    private JacksonTester<AtualizacaoTutorDto> jsonDtoA;
    @Test
    @DisplayName("TESTA QUE TUTOR ESTA RETORNADO 200")
    void cenario01() throws Exception {
        CadastroTutorDto cadastroTutorDto = new CadastroTutorDto("QUALQUER NOME","12364585456","tutor@gmail.com");
        var responde = mvc.perform(
                post("/tutores")
                        .content(jsonDto.write(cadastroTutorDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();
        Assertions.assertEquals(200,responde.getStatus());
    }
    @Test
    @DisplayName("TESTA QUE TUTOR ESTA RETORNADO 400")
    void cenario02() throws Exception {
        CadastroTutorDto cadastroTutorDto = new CadastroTutorDto(null,null,null);
        var responde = mvc.perform(
                post("/tutores")
                        .content(jsonDto.write(cadastroTutorDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();
        Assertions.assertEquals(400,responde.getStatus());
    }
    @Test
    @DisplayName("ATUALIZAR TESTA QUE TUTOR ESTA RETORNADO 400")
    void cenario03() throws Exception {
        AtualizacaoTutorDto  atualizacaoTutorDto = new AtualizacaoTutorDto(null,null,null,null);
        var responde = mvc.perform(
                put("/tutores")
                        .content(jsonDtoA.write(atualizacaoTutorDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();
        Assertions.assertEquals(400,responde.getStatus());
    }
    @Test
    @DisplayName("ATUALIZAR TESTA QUE TUTOR ESTA RETORNADO 200")
    void cenario04() throws Exception {
        AtualizacaoTutorDto  atualizacaoTutorDto = new AtualizacaoTutorDto(1L,null,"12364585456","tutor@gmail.com");
        var responde = mvc.perform(
                put("/tutores")
                        .content(jsonDtoA.write(atualizacaoTutorDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();
        Assertions.assertEquals(200,responde.getStatus());
    }


}