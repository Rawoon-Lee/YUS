DROP DATABASE IF EXISTS ssafit_yus;
CREATE DATABASE ssafit_yus DEFAULT CHARACTER SET utf8mb4;

USE ssafit_yus;

DROP TABLE IF EXISTS exercise_info;
CREATE TABLE exercise_info (
	exercise_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    exercise_name VARCHAR(40) NOT NULL,
	exercise_part VARCHAR(40) NOT NULL
);

CREATE TABLE group_info (
	group_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(20) NOT NULL,
	exercise_type INT DEFAULT 0,
    group_point INT DEFAULT 0,
    maximum_people INT DEFAULT 0,
    current_people INT DEFAULT 0
);

CREATE TABLE user_info (
	user_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
	user_password TEXT NOT NULL,
	weight FLOAT DEFAULT 0,
    height FLOAT DEFAULT 0,
    age INT DEFAULT 0,
    gender INT DEFAULT 0,
    gym_name VARCHAR(40) DEFAULT 'unknown',
    filepath VARCHAR(40),
    purpose INT DEFAULT 0,
    group_no INT,
    FOREIGN KEY (group_no) REFERENCES group_info (group_no),
    user_point INT DEFAULT 0
);

CREATE TABLE youtube_info (
	video_id VARCHAR(40) NOT NULL PRIMARY KEY,
    url VARCHAR(200) NOT NULL,
    title VARCHAR(200) NOT NULL,
    channel_name VARCHAR(40) NOT NULL,
    exercise_no INT,
    FOREIGN KEY (exercise_no) REFERENCES exercise_info (exercise_no),
    view_cnt INT DEFAULT 0
);

CREATE TABLE routine_info (
	routine_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    exercise_type INT DEFAULT 0,
	user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now()
);

CREATE TABLE meal_board (
	post_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    content TEXT NOT NULL,
    filepath VARCHAR(40),
	carb INT DEFAULT 0,
    protein INT DEFAULT 0,
    fat INT DEFAULT 0,
    user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now()
);

CREATE TABLE exercise_per_routine (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no),
	exercise_no INT,
    FOREIGN KEY (exercise_no) REFERENCES exercise_info (exercise_no)
);

CREATE TABLE routine_per_day (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    days INT NOT NULL,
	group_no INT,
    FOREIGN KEY (group_no) REFERENCES group_info (group_no),
    routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no)
);

DROP TABLE IF EXISTS success;
CREATE TABLE success (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    is_done TIMESTAMP DEFAULT now(),
	user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no)
);

CREATE TABLE youtube_liked (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
	video_id VARCHAR(40) NOT NULL,
    FOREIGN KEY (video_id) REFERENCES youtube_info (video_id)
);

CREATE TABLE routine_liked (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
	routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no)
);

CREATE TABLE meal_liked (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
	post_no INT,
    FOREIGN KEY (post_no) REFERENCES meal_board (post_no)
);

CREATE TABLE youtube_comm (
	comm_index INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_no INT DEFAULT 0,
    comm TEXT NOT NULL,
    order_no INT DEFAULT 0,
    comm_group INT DEFAULT 0,
	video_id VARCHAR(40) NOT NULL,
    FOREIGN KEY (video_id) REFERENCES youtube_info (video_id),
    user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
    reg_date TIMESTAMP DEFAULT now()
);

CREATE TABLE routine_comm (
	comm_index INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_no INT DEFAULT 0,
    comm TEXT NOT NULL,
    order_no INT DEFAULT 0,
    comm_group INT DEFAULT 0,
	routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no),
    user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
    reg_date TIMESTAMP DEFAULT now()
);

CREATE TABLE meal_comm (
	comm_index INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_no INT DEFAULT 0,
    comm TEXT NOT NULL,
    order_no INT DEFAULT 0,
    comm_group INT DEFAULT 0,
	post_no INT,
    FOREIGN KEY (post_no) REFERENCES meal_board (post_no),
    user_no INT,
    FOREIGN KEY (user_no) REFERENCES user_info (user_no),
    reg_date TIMESTAMP DEFAULT now()
);

INSERT INTO exercise_info (exercise_name, exercise_part)
VALUES ('스쿼트', '하체'),
("레그 프레스", "하체"),
("레그 익스텐션", "하체"),
("숄더 프레스", "어깨"),
("벤치 프레스", "가슴");
SELECT * FROM exercise_info;

INSERT INTO group_info (group_name, exercise_type, maximum_people)
VALUES ("놀기위해 운동한다", 0, 20),
("체력이 국력", 1, 10),
("오늘은 내가 핫바디", 1, 15);
SELECT * FROM group_info;

INSERT INTO user_info(user_id, user_password, weight, height, gym_name, purpose, group_no)
VALUES ("JunhoLee", "iamhandsome", 67, 178, "강남비싼헬스장", 1, 3),
("Hwasa", "iamsexy", 44, 160, "이태원비싼헬스장", 1, 1);
SELECT * FROM user_info;

INSERT INTO youtube_info(video_id, url, title, channel_name, exercise_no)
VALUES ("temp", "www.youtube.com/embed/temp", "뱃살빼는 최고의 운동", "땅끄부부", 1),
("trmp01", "www.youtube.com/embed/temp01", "가슴운동 다 알려줄게", "계란 형", 5);
SELECT * FROM youtube_info;

INSERT INTO routine_info (exercise_type, user_no)
VALUES (0, 1),
(1, 2);

SELECT * FROM routine_info; 

INSERT INTO meal_board (title, filepath, content, carb, protein, fat, user_no)
VALUES ("오늘의 식단 인증", "static/profile/me.jpg", "치팅데이 다음 날에는 꼭 이렇게 먹어줘야 마음이 편해요", 90, 100, 40, 1);

INSERT INTO exercise_per_routine (routine_no, exercise_no)
VALUES (1, 1),
(1, 2), 
(1, 3),
(1, 4),
(2, 1),
(2, 5);

INSERT INTO routine_per_day (days, group_no, routine_no)
VALUES (0, 1, 1),
(1, 1, 1),
(2, 1, 1),
(3, 1, 1),
(4, 1, 1),
(5, 1, 1),
(6, 1, 2),
(0, 2, 2),
(1, 2, 1),
(2, 2, 2),
(3, 2, 1),
(4, 2, 1),
(5, 2, 1),
(6, 2, 2);

INSERT INTO success (user_no)
VALUES (1);

INSERT INTO success (user_no)
VALUES (2);

INSERT INTO youtube_liked (user_no, video_id)
VALUES (1, "temp"), 
(2, "trmp01"),
(2, "temp");

INSERT INTO routine_liked (routine_no, user_no)
VALUES (1, 1), (2, 1);

INSERT INTO meal_liked (user_no, post_no)
VALUES (1, 1), (2, 1);

INSERT INTO meal_comm (class_no, comm, order_no, comm_group, post_no, user_no)
VALUES (0, "오 보기만해도 건강해질 거 같네요", 0, 1, 1, 2),
(1, "감사합니다. 가까이 사시는거면 제가 만들어 드릴까요?", 1, 1, 1, 1),
(1, "신고할게요", 2, 1, 1, 2);

INSERT INTO youtube_comm (class_no, comm, order_no, comm_group, video_id, user_no)
VALUES (0, "운동 가즈아!", 0, 1, "temp", 2),
(1, "화이팅!", 1, 1, "temp", 1),
(0, "공짜로 이런 수업을 듣다니 감사합니다", 0, 3, "temp", 2);

INSERT INTO routine_comm (class_no, comm, order_no, comm_group, routine_no, user_no)
VALUES (0, "내 동생이 이거 효과좋다네요", 0, 1, 1, 1),
(0, "공짜로 이런 수업을 듣다니 감사합니다", 0, 2, 1, 2);