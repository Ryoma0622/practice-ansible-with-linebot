package com.practice.linebot;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {

    User findByUserName(String userName);
}
