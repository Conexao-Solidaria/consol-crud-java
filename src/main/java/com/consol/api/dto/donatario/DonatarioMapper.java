package com.consol.api.dto.donatario;

import com.consol.api.entity.Donatario;

import java.util.List;

public class DonatarioMapper {

    public static DonatarioConsultaDto donatarioToListagemDto(Donatario donatario) {

        DonatarioConsultaDto dto = new DonatarioConsultaDto();
        dto.setId(donatario.getId());
        dto.setDataCadastro(donatario.getDataCadastro());
        dto.setNome(donatario.getNome());
        dto.setRg(donatario.getRg());
        dto.setCpf(donatario.getCpf());
        dto.setDataNascimento(donatario.getDataNascimento());
        dto.setTelefone1(donatario.getTelefone1());
        dto.setTelefone2(donatario.getTelefone2());
        dto.setEstadoCivil(donatario.getEstadoCivil());
        dto.setEscolaridade(donatario.getEscolaridade());
        dto.setTrabalhando(donatario.getTrabalhando());
        dto.setOcupacao(donatario.getOcupacao());
        return dto;
    }

    public static Donatario cadastroDtoToDonatario(DonatarioCadastroDto dto) {
        Donatario donatario = new Donatario();
        donatario.setDataCadastro(dto.getDataCadastro());
        donatario.setNome(dto.getNome());
        donatario.setRg(dto.getRg());
        donatario.setCpf(dto.getCpf());
        donatario.setDataNascimento(dto.getDataNascimento());
        donatario.setTelefone1(dto.getTelefone1());
        donatario.setTelefone2(dto.getTelefone2());
        donatario.setEstadoCivil(dto.getEstadoCivil());
        donatario.setEscolaridade(dto.getEscolaridade());
        donatario.setTrabalhando(dto.getTrabalhando());
        donatario.setOcupacao(dto.getOcupacao());
        return donatario;
    }

    public static Donatario atualizacaoDtoToDonatario(DonatarioAtualizarDto dto) {
        Donatario donatario = new Donatario();
        donatario.setNome(dto.getNome());
        donatario.setTelefone1(dto.getTelefone1());
        donatario.setTelefone2(dto.getTelefone2());
        donatario.setEstadoCivil(dto.getEstadoCivil());
        donatario.setEscolaridade(dto.getEscolaridade());
        donatario.setTrabalhando(dto.getTrabalhando());
        donatario.setOcupacao(dto.getOcupacao());
        return donatario;
    }

    public static List<DonatarioConsultaDto> listagemDtoToDonatario(List<Donatario> donatarios) {
        return donatarios.stream().map(DonatarioMapper::donatarioToListagemDto).toList();
    }
}
