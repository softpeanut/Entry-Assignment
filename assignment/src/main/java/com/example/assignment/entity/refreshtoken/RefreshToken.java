package com.example.assignment.entity.refreshtoken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@RedisHash
public class RefreshToken implements Serializable {

    @Id
    private String username;

    @Indexed // refreshToken으로 데이터 조회
    private String refreshToken;

    @TimeToLive // 데이터의 유효기간 설정
    private Long expiration;

    public RefreshToken updateExpiration(Long expiration) {
        this.expiration = expiration;
        return this;
    }

}
