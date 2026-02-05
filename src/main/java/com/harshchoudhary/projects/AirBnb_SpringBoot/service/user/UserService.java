package com.harshchoudhary.projects.AirBnb_SpringBoot.service.user;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.User.UserDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.User.UserProfileUpdateReqDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.Users.User;
import org.jspecify.annotations.Nullable;

public interface UserService {
    User getUserById(Long id);

    void updateProfile(UserProfileUpdateReqDTO updateReqDTO);

    @Nullable UserDTO getMyProfile();
}
