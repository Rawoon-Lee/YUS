package com.ssafit.yus.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafit.yus.model.dao.YoutubeCommDao;
import com.ssafit.yus.model.dto.YoutubeComm;

@Service
public class YoutubeCommServiceImpl implements YoutubeCommService{
	@Autowired
	YoutubeCommDao youtubeCommDao;

	@Override
	public List<YoutubeComm> selectByVideoId(String videoId) {
		return youtubeCommDao.selectByVideoId(videoId);
	}

	@Override
	public YoutubeComm selectByCommindex(int commIndex) {
		return youtubeCommDao.selectByCommindex(commIndex);
	}

	@Override
	public void InsertYoutubeComm(YoutubeComm youtubeComm) {
		if (youtubeComm.getClassNo() == 0) {
			int maxCommIndex = youtubeCommDao.selectMaxCommIndex();
			youtubeComm.setCommGroup(maxCommIndex + 1);
		}
		else {
			int max = youtubeCommDao.selectMaxOrderNo(youtubeComm) + 1;
			youtubeComm.setOrderNo(max);
		}
		youtubeCommDao.InsertYoutubeComm(youtubeComm);
	}

	@Override
	public void deleteByCommIndex(int commIndex) {
		youtubeCommDao.deleteByCommIndex(commIndex);
	}

	@Override
	public void updateForDelete(YoutubeComm youtubeComm) {
		youtubeComm.setUserId("deletedcomm");
		youtubeComm.setComm("작성자에 의해 삭제된 댓글입니다.");
		youtubeCommDao.updateForDelete(youtubeComm);
	}

	@Override
	public void updateByCommIndex(YoutubeComm youtubeComm) {
		youtubeCommDao.updateByCommIndex(youtubeComm);
	}
}
