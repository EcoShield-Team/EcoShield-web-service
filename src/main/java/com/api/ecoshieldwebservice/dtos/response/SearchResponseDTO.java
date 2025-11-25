package com.api.ecoshieldwebservice.dtos.response;

import com.api.ecoshieldwebservice.dtos.user.UsuarioResponseForoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDTO {
    private List<PostResponseDTO> posts;
    private List<UsuarioResponseForoDTO> usuarios;
}
