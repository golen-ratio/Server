package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
