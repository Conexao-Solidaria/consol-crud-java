package com.consol.api.dto.doacao;

import com.consol.api.entity.Doacao;
import com.consol.api.entity.Donatario;
import com.consol.api.entity.Instituicao;

import java.util.List;

public class DoacaoMapper {

    public static DoacaoConsultaDto toDto(Doacao doacao) {
        if (doacao == null) return null;

        DoacaoConsultaDto dto = new DoacaoConsultaDto();

        dto.setId(doacao.getId());
        dto.setPeso(doacao.getPeso());
        dto.setDescricao(doacao.getDescricao());
        dto.setDataDoacao(doacao.getDataDoacao());

        dto.setInstituicao(toInstituicaoDto(doacao.getInstituicao()));

        dto.setDonatario(toDonatarioDto(doacao.getDonatario()));

        return dto;
    }

    public static Doacao toEntity(DoacaoCadastroDto dto) {
        if (dto == null) return null;

        Doacao doacao = new Doacao();

        doacao.setPeso(dto.getPeso());
        doacao.setDescricao(dto.getDescricao());
        doacao.setDataDoacao(dto.getDataDoacao());

        return doacao;
    }

    public static List<DoacaoConsultaDto> toDto(List<Doacao> doacoes) {
        return doacoes.stream().map(DoacaoMapper::toDto).toList();
    }

    private static DoacaoConsultaDto.InstituicaoDto toInstituicaoDto(Instituicao instituicao) {
        if (instituicao == null) return null;

        DoacaoConsultaDto.InstituicaoDto instituicaoDto = new DoacaoConsultaDto.InstituicaoDto();
        instituicaoDto.setId(instituicao.getId());
        instituicaoDto.setNome(instituicao.getNome());
        instituicaoDto.setDescricao(instituicao.getDescricao());
        instituicaoDto.setCep(instituicao.getCep());
        instituicaoDto.setNumeroImovel(instituicao.getNumeroImovel());

        return instituicaoDto;
    }

    private static DoacaoConsultaDto.DonatarioDto toDonatarioDto(Donatario donatario) {
        if (donatario == null) return null;

        DoacaoConsultaDto.DonatarioDto donatarioDto = new DoacaoConsultaDto.DonatarioDto();
        donatarioDto.setId(donatario.getId());
        donatarioDto.setDataCadastro(donatario.getDataCadastro());
        donatarioDto.setNome(donatario.getNome());
        donatarioDto.setRg(donatario.getRg());
        donatarioDto.setCpf(donatario.getCpf());
        donatarioDto.setDataNascimento(donatario.getDataNascimento());
        donatarioDto.setTelefone1(donatario.getTelefone1());
        donatarioDto.setTelefone2(donatario.getTelefone2());

        return donatarioDto;
    }
}
