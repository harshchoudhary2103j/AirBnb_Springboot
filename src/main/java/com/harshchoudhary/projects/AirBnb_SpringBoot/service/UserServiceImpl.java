package com.harshchoudhary.projects.AirBnb_SpringBoot.service;

import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.UserDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.dto.UserProfileUpdateReqDTO;
import com.harshchoudhary.projects.AirBnb_SpringBoot.entity.User;
import com.harshchoudhary.projects.AirBnb_SpringBoot.exception.ResourceNotFoundException;
import com.harshchoudhary.projects.AirBnb_SpringBoot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.harshchoudhary.projects.AirBnb_SpringBoot.util.Apputils.getCurrentUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not found with id: "+id));
    }

    @Override
    public void updateProfile(UserProfileUpdateReqDTO updateReqDTO) {
        User user = getCurrentUser();
        if(updateReqDTO.getDateOfBirth()!=null){
            user.setDateOfBirth(updateReqDTO.getDateOfBirth());
        }
        if(updateReqDTO.getGender()!=null){
            user.setGender(updateReqDTO.getGender());
        }
        if(updateReqDTO.getName()!=null){
            user.setName(updateReqDTO.getName());
        }

        userRepository.save(user);
    }

    @Override
    public  UserDTO getMyProfile() {
        User user = getCurrentUser();
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }
}
