package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.UserDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.UserProfileUpdateReqDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.User;
import org.jspecify.annotations.Nullable;

public interface UserService {
    User getUserById(Long id);

    void updateProfile(UserProfileUpdateReqDTO updateReqDTO);

    @Nullable UserDTO getMyProfile();
}
