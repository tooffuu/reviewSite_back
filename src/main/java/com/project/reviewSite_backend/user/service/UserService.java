package com.project.reviewSite_backend.user.service;

import com.amazonaws.services.s3.AmazonS3;
import com.project.reviewSite_backend.answer.AWS.AwsService;
import com.project.reviewSite_backend.bookmark.dao.BookmarkNameRepository;
import com.project.reviewSite_backend.bookmark.domain.BookmarkName;
import com.project.reviewSite_backend.exception.PasswordNotMatchException;
import com.project.reviewSite_backend.exception.UserNotFoundException;
import com.project.reviewSite_backend.user.UserRole;
import com.project.reviewSite_backend.user.dao.UserRepository;
import com.project.reviewSite_backend.user.domain.User;
import com.project.reviewSite_backend.user.dto.CreateForm;
import com.project.reviewSite_backend.user.dto.UpdatePasswordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AwsService awsService;
    private final PasswordEncoder passwordEncoder;
    private final BookmarkNameRepository bookmarkNameRepository;
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void joinUser(CreateForm createForm) {
        User user = User.builder()
                .username(createForm.getUsername())
                .nickname(createForm.getNickname())
                .userid(createForm.getUserid())
                .userImgUrl("https://file-upload-ktw.s3.ap-northeast-2.amazonaws.com/user.png")
                .password(passwordEncoder.encode(createForm.getPassword1()))
                .email(createForm.getEmail())
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);

        // 유저 회원가입시 북마크 폴더 자동 생성
        BookmarkName bookmarkName = BookmarkName.builder()
                .bookmarkName("기본 폴더")
                .user(user)
                .build();

        bookmarkNameRepository.save(bookmarkName);
    }

    public CreateForm login(User user) {
        Optional<User> opUser = userRepository.findByUserid(user.getUserid());

        if (opUser.isPresent()) {
            User loginedUser = opUser.get();
            if (passwordEncoder.matches(user.getPassword(), loginedUser.getPassword())) {
                CreateForm createForm = new CreateForm(loginedUser);
                return createForm;
            }
            throw new PasswordNotMatchException(String.format("password do not match"));
        }
        throw new UserNotFoundException(String.format("%s not found", user.getUserid()));
    }

    public boolean checkUseridDuplicate(String userid) {
        return userRepository.existsByUserid(userid);
    }

    public User getUser(String username) {
        Optional<User> user = userRepository.findByUserid(username);
        return user.orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }


    //마이페이지 진입 전 회원 확인
    public CreateForm confirmPwd(User checkUser) {
        Optional<User> ou = userRepository.findById(checkUser.getId());

        if (ou.isPresent()) {
            User user = ou.get();
            if (passwordEncoder.matches(checkUser.getPassword(), user.getPassword())) {
                CreateForm createForm = new CreateForm(user);

                return createForm;
            }
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");

        }
        throw new UserNotFoundException("등록된 회원이 없습니다.");
    }

    //회원 정보 수정
    public CreateForm modifyUser(CreateForm user, List<MultipartFile> files) {
        Optional<User> ou = userRepository.findById(user.getId());

        if (ou.isPresent()) {
            User ru = ou.get();
            ru.setUserid(user.getUserid());
            ru.setUsername(user.getUsername());
            ru.setEmail(user.getEmail());
            ru.setNickname(user.getNickname());
            ru.setPassword(passwordEncoder.encode(user.getPassword1()));

            if (files != null) {
                files.stream()
                        .forEach(file -> {
                            try {
                                String image = ru.getUserImgUrl();
                                String profileImage = image.substring(image.lastIndexOf("/") + 1, image.length());
                                boolean existImage = amazonS3.doesObjectExist(bucket, profileImage);

                                if (existImage == true) {
                                    amazonS3.deleteObject(bucket, profileImage);
                                }
                                // s3 bucket 업로드 로직
                                String imgUrl = awsService.sendFileToS3Bucket(file);
                                // s3 bucket 업로드 후 imgUrl db 저장 로직
                                ru.setUserImgUrl(imgUrl);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            } else if (files == null) {
                ru.setUserImgUrl(user.getUserImgUrl());
                userRepository.save(ru);
            }

            User user1 = userRepository.save(ru);
            CreateForm createForm = new CreateForm(user1);
            return createForm;
        }
        throw new UserNotFoundException("등록된 회원이 없습니다.");
    }

    public CreateForm deleteById(Long id) {
        try {
            userRepository.deleteById(id);

            return deleteById(id);
        } catch (EmptyResultDataAccessException e) {

            return null;
        }

    }

    public String findId(String username, String email) {
        User user = userRepository.findByUsernameAndEmail(username, email);

        if (user == null) {
            return null;
        }

        return user.getUserid();
    }

    public CreateForm findPw(String username, String userid, String email) {
        User user = userRepository.findByUsernameAndUseridAndEmail(username, userid, email);

        if (user == null) {
            return null;
        }
        CreateForm createForm = new CreateForm(user);

        return createForm;
    }

    public Long updatePW(Long id, UpdatePasswordDto updatePasswordDto) {
        User modifyPw = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        if ((updatePasswordDto.getPassword1()).equals(updatePasswordDto.getPassword2())) {
            modifyPw.update(passwordEncoder.encode(updatePasswordDto.getPassword1()), updatePasswordDto.getPassword2());
            return userRepository.save(modifyPw).getId();
        }
        throw new PasswordNotMatchException(String.format("패스워드가 일치하지 않습니다."));
    }

    public User findUser(Long userId) {

        if (userId == null) {
            return null;
        }
        User user = userRepository.findById(userId).orElseThrow();

        return user;
    }


    public User findnickname(String nickname) {

        return userRepository.findBynickname(nickname);
    }
}