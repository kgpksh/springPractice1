DROP DATABASE IF EXISTS practice1;

CREATE DATABASE practice1;

USE practice1;

# 게시물 데이터
CREATE TABLE article (
                         id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                         PRIMARY KEY(id),
                         createDate DATETIME NOT NULL,
                         modifyDate DATETIME NOT NULL,
                         `subject` CHAR(100) NOT NULL,
                         `content` LONGTEXT NOT NULL
);

INSERT INTO article
SET createDate = NOW(),
    modifyDate = NOW(),
    `subject` = '제목1',
    `content` = '내용1';


# 회원 데이터
CREATE TABLE `member` (
                          id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          PRIMARY KEY(id),
                          createDate DATETIME NOT NULL,
                          modifyDate DATETIME NOT NULL,
                          `username` CHAR(100) NOT NULL UNIQUE,
                          `password` CHAR(100) NOT NULL,
                          `name` CHAR(100) NOT NULL,
                          email CHAR(100) NOT NULL
);

INSERT INTO `member`
SET createDate = NOW(),
    modifyDate = NOW(),
    `username` = 'user1',
    `password` = 'password1',
    `name` = '유저1',
    email = 'user1@test.com';

# 게시물에 작성자 정보 추가
ALTER TABLE article
ADD COLUMN  member_id BIGINT UNSIGNED NOT NULL AFTER modifyDate;

# 기존 게시물 작성자를 user2로 지정
UPDATE article
SET member_id = 2;

# 게시물에 작성자 정보 camelCase로 변경
ALTER TABLE article
    CHANGE member_id memberId BIGINT UNSIGNED NOT NULL AFTER modifyDate;