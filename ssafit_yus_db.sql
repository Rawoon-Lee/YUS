DROP DATABASE IF EXISTS ssafit_yus;
CREATE DATABASE ssafit_yus DEFAULT CHARACTER SET utf8mb4;

USE ssafit_yus;

DROP TABLE IF EXISTS exercise_info;
CREATE TABLE exercise_info (
    exercise_name VARCHAR(40) NOT NULL PRIMARY KEY,
	exercise_part VARCHAR(40) NOT NULL
);

CREATE TABLE user_info (
    user_id VARCHAR(20) NOT NULL PRIMARY KEY,
	user_password TEXT NOT NULL,
	weight FLOAT DEFAULT 0,
    height FLOAT DEFAULT 0,
    age INT DEFAULT 0,
    gender INT DEFAULT 0,
    gym_name VARCHAR(40) DEFAULT 'unknown',
    filepath VARCHAR(40),
    purpose INT DEFAULT 0,
    group_no INT DEFAULT 0,
    user_point INT DEFAULT 0
);

CREATE TABLE group_info (
	group_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(40) NOT NULL,
    group_point INT DEFAULT 0,
    maximum_people INT NOT NULL,
    current_people INT DEFAULT 1 NOT NULL,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE
);

CREATE TABLE youtube_info (
	video_id VARCHAR(40) NOT NULL PRIMARY KEY,
    url VARCHAR(200) NOT NULL,
    title VARCHAR(200) NOT NULL,
    channel_name VARCHAR(40) NOT NULL,
    exercise_name VARCHAR(40) NOT NULL,
    FOREIGN KEY (exercise_name) REFERENCES exercise_info (exercise_name) ON DELETE CASCADE,
    view_cnt INT DEFAULT 0
);

CREATE TABLE routine_info (
	routine_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
	user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now(),
    content TEXT NOT NULL
);

CREATE TABLE meal_board (
	post_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    content TEXT NOT NULL,
    filepath VARCHAR(40),
	carb INT DEFAULT 0,
    protein INT DEFAULT 0,
    fat INT DEFAULT 0,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
    view_cnt INT DEFAULT 0,
    reg_date TIMESTAMP DEFAULT now()
);

CREATE TABLE exercise_per_routine (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no) ON DELETE CASCADE,
	exercise_name VARCHAR(40) NOT NULL,
    FOREIGN KEY (exercise_name) REFERENCES exercise_info (exercise_name) ON DELETE CASCADE,
    routine_set INT NOT NULL,
    routine_rep INT NOT NULL,
    order_no INT NOT NULL
);

CREATE TABLE routine_per_day (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    days INT NOT NULL,
	group_no INT,
    FOREIGN KEY (group_no) REFERENCES group_info (group_no) ON DELETE CASCADE,
    routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no) ON DELETE CASCADE
);

DROP TABLE IF EXISTS success;
CREATE TABLE success (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    is_done TIMESTAMP DEFAULT now(),
	user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE
);

CREATE TABLE youtube_liked (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
	video_id VARCHAR(40) NOT NULL,
    FOREIGN KEY (video_id) REFERENCES youtube_info (video_id) ON DELETE CASCADE
);

CREATE TABLE routine_liked (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
	routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no) ON DELETE CASCADE
);

CREATE TABLE meal_liked (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
	post_no INT,
    FOREIGN KEY (post_no) REFERENCES meal_board (post_no) ON DELETE CASCADE
);

CREATE TABLE youtube_comm (
	comm_index INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_no INT DEFAULT 0,
    comm TEXT NOT NULL,
    order_no INT DEFAULT 0,
    comm_group INT DEFAULT 0,
	video_id VARCHAR(40) NOT NULL,
    FOREIGN KEY (video_id) REFERENCES youtube_info (video_id) ON DELETE CASCADE,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
    reg_date TIMESTAMP DEFAULT now()
);

CREATE TABLE routine_comm (
	comm_index INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_no INT DEFAULT 0,
    comm TEXT NOT NULL,
    order_no INT DEFAULT 0,
    comm_group INT DEFAULT 0,
	routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no) ON DELETE CASCADE,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
    reg_date TIMESTAMP DEFAULT now()
);

CREATE TABLE meal_comm (
	comm_index INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class_no INT DEFAULT 0,
    comm TEXT NOT NULL,
    order_no INT DEFAULT 0,
    comm_group INT DEFAULT 0,
	post_no INT,
    FOREIGN KEY (post_no) REFERENCES meal_board (post_no) ON DELETE CASCADE,
    user_id VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE,
    reg_date TIMESTAMP DEFAULT now()
);

INSERT INTO exercise_info (exercise_name, exercise_part)
VALUES ("사이드 레터럴 레이즈", "어깨"),
("밀리터리 프레스", "어깨"),
("페이스 풀", "어깨"),
("벤치 프레스", "가슴"),
("인클라인 벤치프레스", "가슴"),
("딥스", "가슴"),
("체스트 프레스", "가슴"),
("렛풀다운", "등"),
("시티드 케이블 로우", "등"),
("바벨 로우", "등"),
('스쿼트', '하체'),
("레그 프레스", "하체"),
("레그 익스텐션", "하체");
SELECT * FROM exercise_info;

INSERT INTO user_info(user_id, user_password, weight, height, age, gender, gym_name, purpose, group_no)
VALUES ("JunhoLee", "1e9c98989e7afb35e311334e79aad5ba5895182db8aa8d0ec28cb675fc8a4d0f", 67, 178, 32, 0, "강남비싼헬스장", 1, 1),
("Hwasa", "af3fce2230d8b03e584f253b1ad38a70c0142ca88488a073a8eb69f7af19ff34", 44, 160, 28, 1, "이태원비싼헬스장", 1, 2),
("hyunklee", "7d824ad37e366f330ef3d3bafb8dc8b18a5b07622e2830eac5966339d98a94b0", 90, 186, 27, 0, "테리온휘트니스", 0, 3),
("rawoon", "96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e", 90, 186, 27, 0, "매탄동휘트니스", 0, 1);
-- iamhandsome, iamsexy, 121314, ssafityus
INSERT INTO user_info(user_id, user_password)
VALUES ("deletedcomm", "2c4a12c037cc5893af1c594156c339fe39963354ae4b37ef43e217ce4bb44d68");
SELECT * FROM user_info;

INSERT INTO group_info (group_name, maximum_people, user_id)
VALUES ("놀기위해 운동한다", 20, "junhoLee"),
("체력이 국력", 10, "Hwasa"),
("오늘은 내가 핫바디", 15, "hyunklee");
SELECT * FROM group_info;

INSERT INTO youtube_info(video_id, url, title, channel_name, exercise_name)
VALUES ("gMaB-fG4u4g", "www.youtube.com/embed/gMaB-fG4u4g", "전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]", "Thankyou BUBU", "레그 익스텐션"),
("CTojmKLkWTo", "www.youtube.com/embed/CTojmKLkWTo", "전신 다이어트 최고의 운동 [칼소폭 핵매운맛]", "Thankyou BUBU", "페이스 풀"),
("zRTEwkIM_eE", "www.youtube.com/embed/zRTEwkIM_eE", "레그 프레스 머신 - 헬스 머신 사용법 하체 운동 끝판왕(헬린이 탈출)", "바디체크 - BODY CHECK", "레그 프레스"),
("Ry_qhN5vx6I", "www.youtube.com/embed/Ry_qhN5vx6I", "뒷벅지와 힙라인을 한번에! 레그프레스 운동법 How to leg press", "힙으뜸", "레그 프레스"),
("FQ_A97PMrcQ", "www.youtube.com/embed/FQ_A97PMrcQ", "레그프레스 기본설명 & 실수하는자세 & 꿀팁", "고러쉬", "레그 프레스"),
("UwQ3alQfVNI", "www.youtube.com/embed/UwQ3alQfVNI", "레그프레스 머신사용법 (많이하는 실수, 잘못된자세)ㅣ고민수", "Minsoo Go", "레그 프레스"),
("mS9iwXhycJI", "www.youtube.com/embed/mS9iwXhycJI", "가장 기본이 되는 필수 하체운동 l 레그 익스텐션", "강경원", "레그 익스텐션"),
("rIoPAhtMdvw", "www.youtube.com/embed/rIoPAhtMdvw", "하체운동-레그익스텐션(Leg Extension)의 모든것!", "양선수의 온라인PT", "레그 익스텐션"),
("Zi-ksO-dHeU", "www.youtube.com/embed/Zi-ksO-dHeU", "레그익스텐션! ㅡ 김명섭관장이 말하는 투조인트로 절대 하면 안되는 사람 꼭 보세요!", "김명섭의 헬스교실", "레그 익스텐션"),
("CVUd2FLP8JI", "www.youtube.com/embed/CVUd2FLP8JI", "다리운동 레그익스텐션 백날해도 당신의 하체가 얇은 이유", "한조바디", "레그 익스텐션"),
("q6hBSSfokzY", "www.youtube.com/embed/q6hBSSfokzY", "ENG)하루10분 스쿼트로 기초 체력 기르기!", "힙으뜸", "스쿼트"),
("MWjKQGoNW0U", "www.youtube.com/embed/MWjKQGoNW0U", "스쿼트 기초 l 강경원", "강경원", "스쿼트"),
("JOpYCTNQaoA", "www.youtube.com/embed/JOpYCTNQaoA", "허리통증! 다치기 싫으면 꼭 보세요! 스쿼트 데드 부상 원인! [허리 디스크]", "트리거15초", "스쿼트"),
("Fk9j6pQ6ej8", "www.youtube.com/embed/Fk9j6pQ6ej8", "'완벽한 스쿼트' 제대로 하는 방법", "피지컬갤러리", "스쿼트"),
("a_fJKv0KzBo", "www.youtube.com/embed/a_fJKv0KzBo", "딥스 효과는 자세와 방법만 알면 최강의 상체와 가슴 근육을 만듭니다", "헬마드 (헬스 건강 정보)", "딥스"),
("pqn9koa_wrw", "www.youtube.com/embed/pqn9koa_wrw", "딥스 '이렇게' 제발 하지마세요 (어깨 박살)", "피지컬갤러리", "딥스"),
("EviXT4MEYkc", "www.youtube.com/embed/EviXT4MEYkc", "딥스 가슴 자극보다 중요시해야 하는 것", "세계적으로 유명한 운동 정보", "딥스"),
("5BYBC73VrDk", "www.youtube.com/embed/5BYBC73VrDk", "어깨가 안 아픈 딥스?! 레그포지션의 중요성", "말왕TV", "딥스");
SELECT * FROM youtube_info;

INSERT INTO routine_info (user_id, title, content)
VALUES ("JunhoLee", "아무나 따라하셈", "이대로 하면 3개월만에 몸짱가능 ㅋ"),
("Hwasa", "저장용", "저만 볼거니까 보지마세염 ㅋㅋ");
SELECT * FROM routine_info;

INSERT INTO meal_board (title, filepath, content, carb, protein, fat, user_id)
VALUES ("오늘의 식단 인증", "static/profile/me.jpg", "치팅데이 다음 날에는 꼭 이렇게 먹어줘야 마음이 편해요", 90, 100, 40, "JunhoLee");

INSERT INTO exercise_per_routine (routine_no, exercise_name, routine_set, routine_rep, order_no)
VALUES (1, "스쿼트", 5, 10, 1),
(1, "렛풀다운", 5, 12, 2), 
(1, "벤치 프레스", 5, 10, 3),
(1, "바벨 로우", 4, 8, 4),
(2, "스쿼트", 5, 10, 1),
(2, "밀리터리 프레스", 5, 12, 2);

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

INSERT INTO success (user_id)
VALUES ("JunhoLee");

INSERT INTO success (user_id)
VALUES ("Hwasa");

INSERT INTO youtube_liked (user_id, video_id)
VALUES ("JunhoLee", "5BYBC73VrDk"), 
("Hwasa", "5BYBC73VrDk"),
("Hwasa", "5BYBC73VrDk");

INSERT INTO routine_liked (routine_no, user_id)
VALUES (1, "JunhoLee"), (2, "JunhoLee");

INSERT INTO meal_liked (user_id, post_no)
VALUES ("JunhoLee", 1), ("Hwasa", 1);

INSERT INTO meal_comm (class_no, comm, order_no, comm_group, post_no, user_id)
VALUES (0, "오 보기만해도 건강해질 거 같네요", 0, 1, 1, "Hwasa"),
(1, "감사합니다. 가까이 사시는거면 제가 만들어 드릴까요?", 1, 1, 1, "JunhoLee"),
(1, "신고할게요", 2, 1, 1, "Hwasa");

INSERT INTO youtube_comm (class_no, comm, order_no, comm_group, video_id, user_id)
VALUES (0, "운동 가즈아!", 0, 1, "5BYBC73VrDk", "Hwasa"),
(1, "화이팅!", 1, 1, "5BYBC73VrDk", "JunhoLee"),
(0, "공짜로 이런 수업을 듣다니 감사합니다", 0, 3, "5BYBC73VrDk", "Hwasa");

INSERT INTO routine_comm (class_no, comm, order_no, comm_group, routine_no, user_id)
VALUES (0, "내 동생이 이거 효과좋다네요", 0, 1, 1, "JunhoLee"),
(0, "공짜로 이런 수업을 듣다니 감사합니다", 0, 2, 1, "Hwasa");