package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    boolean existsByPetIdAndStatus(Long idPet, StatusAdocao status);

    Adocao findByIdAndStatus(Long aLong, StatusAdocao statusAdocao);

    boolean existsByTutorIdAndStatus(Long aLong, StatusAdocao statusAdocao);

    int countByTutorIdAndStatus( Long aLong, StatusAdocao aprovado);

    Adocao findByPetIdAndStatus(Long aLong, StatusAdocao statusAdocao);
}
