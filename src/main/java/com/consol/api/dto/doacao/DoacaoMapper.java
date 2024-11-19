package com.consol.api.dto.doacao;

import com.consol.api.entity.Doacao;
import com.consol.api.entity.Familia;
import com.consol.api.entity.Titular;

import java.util.ArrayList;
import java.util.List;

public class DoacaoMapper {

    public static Doacao toEntity(DoacaoCadastroDto dto) {
        if (dto == null) return null;

        Doacao doacao = new Doacao();
        doacao.setDescricao(dto.getDescricao());
        doacao.setDataDoacao(dto.getDataDoacao());
        doacao.setFlagDoacaoEntregue((byte) 0);
        return doacao;
    }

    public static DoacaoConsultaDto toDto(Doacao doacao) {
        if (doacao == null) return null;

        DoacaoConsultaDto dto = new DoacaoConsultaDto();

        dto.setId(doacao.getId());

        dto.setDescricao(doacao.getDescricao());
        dto.setDataDoacao(doacao.getDataDoacao());
        dto.setFlagDoacaoEntregue(doacao.getFlagDoacaoEntregue());

        dto.setDonatario(toDonatarioDto(doacao.getTitular()));

        return dto;
    }

    public static List<DoacaoConsultaDto> toDto(List<Doacao> doacoes) {
        return doacoes.stream().map(DoacaoMapper::toDto).toList();
    }

//    private static DoacaoConsultaDto.InstituicaoDto toInstituicaoDto(Instituicao instituicao) {
//        if (instituicao == null) return null;
//
//        DoacaoConsultaDto.InstituicaoDto instituicaoDto = new DoacaoConsultaDto.InstituicaoDto();
//        instituicaoDto.setId(instituicao.getId());
//        instituicaoDto.setNome(instituicao.getNome());
//        instituicaoDto.setDescricao(instituicao.getDescricao());
//        instituicaoDto.setCep(instituicao.getCep());
//        instituicaoDto.setNumeroImovel(instituicao.getNumeroImovel());
//
//        return instituicaoDto;
//    }

    private static DoacaoConsultaDto.TitularDto toDonatarioDto(Titular titular) {
        if (titular == null) return null;

        DoacaoConsultaDto.TitularDto donatarioDto = new DoacaoConsultaDto.TitularDto();
        donatarioDto.setId(titular.getId());
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
        entity.setFlagDoacaoEntregue( dto.getFlagDoacaoEntregue());

        return entity;
    }

    public static Doacao toEntity(DoacaoAtualizarDescricaoDto dto){
        if (dto == null) return null;

        Doacao entity = new Doacao();
        entity.setDescricao(dto.getDescricao());

        return entity;
    }

    public static Doacao toEntity(DoacaoConfirmacaoDto dto){
        if (dto == null) return null;

        Doacao entity = new Doacao();
        entity.setDataDoacao(dto.getDataDoacao());

        return entity;

    }

    public static List<DoacaoConsultaComFamiliaDto> toDtoFamilia(List<Doacao> entity){
        List<DoacaoConsultaComFamiliaDto> dtos = new ArrayList <>();

        for (int i = 0; i < entity.size(); i++) {
        DoacaoConsultaComFamiliaDto dto = new DoacaoConsultaComFamiliaDto();
        dto.setId(entity.get(i).getId());
        dto.setDescricao(entity.get(i).getDescricao());
        dto.setDataDoacao(entity.get(i).getDataDoacao());
        dto.setFlagDoacaoEntregue(entity.get(i).getFlagDoacaoEntregue());
        dto.setTitular(toDtoDonaratio(entity.get(i).getTitular()));
        dto.getTitular().setFamilia(toDtoFamilia(entity.get(i).getTitular().getFamilia()));
        dtos.add(dto);
        }

        return dtos;
    }


    public static DoacaoConsultaComFamiliaDto.TitularDto toDtoDonaratio(Titular entity){
        DoacaoConsultaComFamiliaDto.TitularDto dto = new DoacaoConsultaComFamiliaDto.TitularDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setRg(entity.getRg());
        dto.setCpf(entity.getCpf());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setTelefone1(entity.getTelefone1());
        dto.setTelefone2(entity.getTelefone2());

        return dto;
    }

    public static DoacaoConsultaComFamiliaDto.TitularDto.FamiliaDto toDtoFamilia(Familia entity){
        DoacaoConsultaComFamiliaDto.TitularDto.FamiliaDto dto = new DoacaoConsultaComFamiliaDto.TitularDto.FamiliaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCep(entity.getCep());
        dto.setNumeroCasa(entity.getNumeroCasa());
        dto.setRenda(entity.getRenda());
        dto.setFlagRetirada(entity.getFlagRetirada());
        dto.setDataCadastro(entity.getDataCadastro());

        return dto;
    }

}
