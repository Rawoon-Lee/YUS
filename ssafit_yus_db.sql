DROP DATABASE IF EXISTS ssafit_yus;
CREATE DATABASE ssafit_yus DEFAULT CHARACTER SET utf8mb4;

USE ssafit_yus;

CREATE TABLE exercise_info (
	exercise_no INT NOT NULL PRIMARY KEY,
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
	user_password VARCHAR(20) NOT NULL,
	weight FLOAT DEFAULT 0,
    height FLOAT DEFAULT 0,
    gym_name VARCHAR(40),
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
    filepath VARCHAR(40) NOT NULL,
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
    days DATE NOT NULL,
	group_no INT,
    FOREIGN KEY (group_no) REFERENCES group_info (group_no),
    routine_no INT,
    FOREIGN KEY (routine_no) REFERENCES routine_info (routine_no)
);

CREATE TABLE success (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    isDone DATE NOT NULL,
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