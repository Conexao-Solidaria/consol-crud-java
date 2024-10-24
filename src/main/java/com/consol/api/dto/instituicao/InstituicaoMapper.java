//package com.consol.api.dto.instituicao;
//
//import com.consol.api.entity.Instituicao;
//
//import java.util.List;
//
//public class InstituicaoMapper {
//
//    public static InstituicaoConsultaDto toDto(Instituicao instituicao) {
//        if (instituicao == null) return null;
//
//        InstituicaoConsultaDto dto = new InstituicaoConsultaDto();
//
//        dto.setId(instituicao.getId());
//        dto.setNome(instituicao.getNome());
//        dto.setCep(instituicao.getCep());
//        dto.setNumeroImovel(instituicao.getNumeroImovel());
//        dto.setDescricao(instituicao.getDescricao());
//
//        return dto;
//    }
//
//    public static Instituicao toEntity(InstituicaoCadastroDto dto) {
//        if (dto == null) return null;
//
//        Instituicao instituicao = new Instituicao();
//
//        instituicao.setNome(dto.getNome());
//        instituicao.setCep(dto.getCep());
//        instituicao.setNumeroImovel(dto.getNumeroImovel());
//        instituicao.setDescricao(dto.getDescricao());
//
//        return instituicao;
//    }
//    public static Instituicao toEntity(InstituicaoAtualizarDto dto) {
//        if (dto == null) return null;
//
//        Instituicao instituicao = new Instituicao();
//
//        instituicao.setNome(dto.getNome());
//        instituicao.setCep(dto.getCep());
//        instituicao.setNumeroImovel(dto.getNumeroImovel());
//        instituicao.setDescricao(dto.getDescricao());
//
//        return instituicao;
//    }
//
//    public static List<InstituicaoConsultaDto> toDto(List<Instituicao> instituicaos){
//        return instituicaos.stream().map(InstituicaoMapper::toDto).toList();
//    }
//}
//
