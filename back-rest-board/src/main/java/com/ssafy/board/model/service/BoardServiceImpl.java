package com.ssafy.board.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDao boardDao;
	
	
	@Override
	public List<Board> getBoardList(HashMap<String, String> params) {
		return boardDao.selectList(params);
	}

	@Override
	public Board getBoard(int id) {
		this.updateCnt(id);
		return boardDao.selectOne(id);
	}

	@Override
	public void writeBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public boolean modifyBoard(Board board) {
		Board originBoard = boardDao.selectOne(board.getId());
		originBoard.setTitle(board.getTitle());
		originBoard.setContent(board.getContent());
		return boardDao.updateBoard(originBoard) == 1;
	}

	@Override
	public boolean removeBoard(int id) {
		return boardDao.deleteBoard(id) == 1;
	}

	@Override
	public void updateCnt(int id) {
		Board board = boardDao.selectOne(id);
		board.setViewCnt(board.getViewCnt()+1);
		boardDao.updateBoard(board);
	}
	
}