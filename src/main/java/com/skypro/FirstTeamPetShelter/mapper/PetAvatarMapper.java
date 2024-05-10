package com.skypro.FirstTeamPetShelter.mapper;

import com.skypro.FirstTeamPetShelter.DTO.PetAvatarDTO;
import com.skypro.FirstTeamPetShelter.model.PetAvatar;
import org.springframework.stereotype.Component;

@Component
public class PetAvatarMapper {
    public PetAvatarDTO matToDTO(PetAvatar petAvatar) {
        PetAvatarDTO avatarDTO = new PetAvatarDTO();
        avatarDTO.setId(petAvatar.getId());
        avatarDTO.setMediaType(petAvatar.getMediaType());
        avatarDTO.setFileSize(petAvatar.getFileSize());
        avatarDTO.setPetId(petAvatar.getPet().getId());
        return avatarDTO;
    }
}
