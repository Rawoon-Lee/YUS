package com.ssafy.board.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.board.model.dto.Board;

public interface BoardDao {
	//게시글 목록
	List<Board> selectList(HashMap<String, String> params);
	//게시글 조회
	Board selectOne(int id);
	//게시글 등록
	void insertBoard(Board board);
	//게시글 삭제
	int deleteBoard(int id);
	//게시글 수정
	int updateBoard(Board board);
}