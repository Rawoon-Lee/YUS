package com.ssafy.board.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.board.model.dto.Board;

public interface BoardService {
	//모든 게시글 조회
	List<Board> getBoardList(HashMap<String, String> params);
	//게시글 하나 조회
	Board getBoard(int id);
	//게시글 등록
	void writeBoard(Board board);
	//게시글 수정
	boolean modifyBoard(Board board);
	//게시글 삭제
	boolean removeBoard(int id);
	//뷰 카운트 하나 증가
	void updateCnt(int id);
}