package com.ssafit.yus.controller;

import java.security.acl.Group;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.yus.model.dto.GroupInfo;
import com.ssafit.yus.model.dto.ListForGroup;
import com.ssafit.yus.model.dto.Success;
import com.ssafit.yus.model.service.GroupInfoService;
import com.ssafit.yus.model.service.SuccessService;

@RestController
@RequestMapping("/group")
public class GroupRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private GroupInfoService groupInfoService;
	@Autowired
	private SuccessService successService;
	
	@GetMapping("/info")
	public ResponseEntity<List<GroupInfo>> infolist() {
		return new ResponseEntity<List<GroupInfo>>(groupInfoService.selectAll(), HttpStatus.OK);
	}
	@GetMapping("/info/{groupNo}")
	public ResponseEntity<Map<String, Object>> detail(@PathVariable int groupNo){
		return new ResponseEntity<Map<String, Object>>(groupInfoService.selectByGroupNo(groupNo), HttpStatus.OK);
	}
	@PostMapping("/info")
	public ResponseEntity<Map<String, String>> insert(@RequestBody ListForGroup lfg){
		Map<String, String> ret = new HashMap<String, String>();
		groupInfoService.InsertGroupInfo(lfg);
		ret.put("msg", SUCCESS);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	@PutMapping("/info/{mode}")
	public ResponseEntity<Map<String, String>> update(@PathVariable int mode, @RequestBody GroupInfo groupInfo){
		Map<String, String> ret = new HashMap<String, String>();
		groupInfoService.updateGroupInfo(mode, groupInfo);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	@Delete("/info")
	public ResponseEntity<Map<String, String>> delete(@RequestBody GroupInfo groupInfo){
		Map<String, String> ret = new HashMap<String, String>();
		groupInfoService.deleteGroup(groupInfo);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/success/{userId}")
	public ResponseEntity<List<Success>> successlist(@PathVariable String userId){
		return new ResponseEntity<List<Success>>(successService.selectByUserId(userId), HttpStatus.OK);
	}
	
	@PostMapping("/success")
	public ResponseEntity<Map<String, String>> insert(@RequestBody Success success){
		Map<String, String> ret = new HashMap<String, String>();
		successService.InsertSuccess(success);
		return new ResponseEntity<Map<String, String>>(ret, HttpStatus.OK);
	}
}
