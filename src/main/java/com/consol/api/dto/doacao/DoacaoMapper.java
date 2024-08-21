package com.consol.api.dto.doacao;

import com.consol.api.entity.Doacao;
import com.consol.api.entity.Titular;
import com.consol.api.entity.Instituicao;

import java.util.List;

public class DoacaoMapper {

    public static DoacaoConsultaDto toDto(Doacao doacao) {
        if (doacao == null) return null;

        DoacaoConsultaDto dto = new DoacaoConsultaDto();

        dto.setId(doacao.getId());
        dto.setStatus(doacao.getStatusDoacao());
        dto.setDescricao(doacao.getDescricao());
        dto.setDataDoacao(doacao.getDataDoacao());
        dto.setFlagDoacaoEntregue(dto.getFlagDoacaoEntregue());

        dto.setInstituicao(toInstituicaoDto(doacao.getInstituicao()));

        dto.setDonatario(toDonatarioDto(doacao.getTitular()));

        return dto;
    }

    public static Doacao toEntity(DoacaoCadastroDto dto) {
        if (dto == null) return null;

        Doacao doacao = new Doacao();

        doacao.setStatusDoacao(dto.getStatusDoacao());
        doacao.setDescricao(dto.getDescricao());
        doacao.setDataDoacao(dto.getDataDoacao());
        doacao.setFlagDoacaoEntregue(dto.getFlagDoacaoEntregue());

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

    private static DoacaoConsultaDto.DonatarioDto toDonatarioDto(Titular titular) {
        if (titular == null) return null;

        DoacaoConsultaDto.DonatarioDto donatarioDto = new DoacaoConsultaDto.DonatarioDto();
        donatarioDto.setId(titular.getId());
        donatarioDto.setDataCadastro(titular.getDataCadastro());
        donatarioDto.setNome(titular.getNome());
        donatarioDto.setRg(titular.getRg());
        donatarioDto.setCpf(titular.getCpf());
        donatarioDto.setDataNascimento(titular.getDataNascimento());
        donatarioDto.setTelefone1(titular.getTelefone1());
        donatarioDto.setTelefone2(titular.getTelefone2());

        return donatarioDto;
    }

    public static Doacao toEntity(DoacaoAtualizarFlagDto dto){
        if (dto == null) return null;

        Doacao entity = new Doacao();
        entity.setFlagDoacaoEntregue(dto.getFlagDoacaoEntregue());

        return entity;
    }

    public static Doacao toEntity(DoacaoAtualizarStatusDto dto){
        if (dto == null) return null;

        Doacao entity = new Doacao();
        entity.setStatusDoacao(dto.getStatus());

        return entity;
    }
}
